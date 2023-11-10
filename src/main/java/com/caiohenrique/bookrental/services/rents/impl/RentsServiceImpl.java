package com.caiohenrique.bookrental.services.rents.impl;

import com.caiohenrique.bookrental.entities.BooksEntity;
import com.caiohenrique.bookrental.entities.RentsEntity;
import com.caiohenrique.bookrental.exceptions.BusinessExceptions;
import com.caiohenrique.bookrental.exceptions.NotFoundExceptions;
import com.caiohenrique.bookrental.io.rents.RentTotalByStatusResponseRequest;
import com.caiohenrique.bookrental.io.rents.RentsCreateRequest;
import com.caiohenrique.bookrental.io.rents.RentsResponseRequest;
import com.caiohenrique.bookrental.io.rents.Status;
import com.caiohenrique.bookrental.repositories.RentsRepository;
import com.caiohenrique.bookrental.services.books.BooksService;
import com.caiohenrique.bookrental.services.rents.RentsService;
import com.caiohenrique.bookrental.services.users.UsersService;
import com.caiohenrique.bookrental.validations.rents.RentsEntityValidator;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RentsServiceImpl implements RentsService {

    @Autowired
    private RentsRepository rentsRepository;

    @Autowired
    private RentsEntityValidator rentsEntityValidator;

    @Autowired
    private UsersService usersService;

    @Autowired
    private BooksService booksService;

    @Override
    public void create(RentsCreateRequest rentsCreateRequest) {
        RentsEntity entity = new RentsEntity();
        if (rentsCreateRequest.getForecastDate() == null) {
            throw new BusinessExceptions("O Campo Previsão de Devolução, não pode estar em branco!");
        }
        entity.setUser(usersService.findById(rentsCreateRequest.getUserId() == null ? 0 : rentsCreateRequest.getUserId()));
        entity.setBook(booksService.findById(rentsCreateRequest.getBookId() == null ? 0 : rentsCreateRequest.getBookId()));
        entity.setForecastDate(rentsCreateRequest.getForecastDate());
        entity.setStatus(Status.PENDING_RENT);
        BooksEntity books = entity.getBook();
        BooksEntity tempBooks = new BooksEntity();
        BeanUtils.copyProperties(books, tempBooks);
        rentsEntityValidator.validateForCreate(entity);
        books.setTotalRented(books.getTotalRented() + 1);
        books.setPendingQuantity(books.getPendingQuantity() + 1);
        books.setAmount(books.getAmount() - 1);
        rentsRepository.save(entity);
    }

    @Override
    public List<RentsResponseRequest> getAll() {
        List<RentsEntity> entity = rentsRepository.findAll();
        return entity.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<RentTotalByStatusResponseRequest> getTotalByStatus() {
        List<RentsEntity> rents = rentsRepository.findAll();

        long totalPending = rents.stream()
                .filter(rent -> getStatusReturned(rent) == Status.PENDING_RENT)
                .count();

        long totalLate = rents.stream()
                .filter(rent -> getStatusReturned(rent) == Status.LATE_RENT)
                .count();

        long totalReturnedOnTime = rents.stream()
                .filter(rent -> getStatusReturned(rent) == Status.RETURNED_ON_TIME)
                .count();

        long totalReturnedDelay = rents.stream()
                .filter(rent -> getStatusReturned(rent) == Status.RETURNED_DELAY)
                .count();

        RentTotalByStatusResponseRequest rentTotal = new RentTotalByStatusResponseRequest();
        rentTotal.setTotal_pending(totalPending);
        rentTotal.setTotal_late(totalLate);
        rentTotal.setTotal_returned_on_time(totalReturnedOnTime);
        rentTotal.setTotal_returned_delay(totalReturnedDelay);

        List<RentTotalByStatusResponseRequest> resultList = new ArrayList<>();
        resultList.add(rentTotal);

        return resultList;
    }


    @Override
    public void returnRent(Integer id) {
        RentsEntity entity = rentsRepository.findById(id)
                .orElseThrow(() -> new BusinessExceptions("Aluguel não encontrado!"));
        LocalDate currentDate = LocalDate.now();
        entity.setReturnDate(currentDate);
        Status status = getStatusReturned(entity);
        entity.setStatus(status);
        BooksEntity books = entity.getBook();
        rentsEntityValidator.validateForGiveback(id);
        books.setPendingQuantity(books.getPendingQuantity() - 1);
        books.setAmount(books.getAmount() + 1);
        rentsRepository.save(entity);
    }

    private RentsResponseRequest convertToDto(RentsEntity rentsEntity) {
        RentsResponseRequest rentsResponseRequest = new RentsResponseRequest();
        rentsResponseRequest.setId(rentsEntity.getId());
        rentsResponseRequest.setUserName(rentsEntity.getUser().getName());
        rentsResponseRequest.setBookName(rentsEntity.getBook().getName());
        rentsResponseRequest.setForecastDate(rentsEntity.getForecastDate());
        rentsResponseRequest.setRentalDate(rentsEntity.getRentalDate());
        rentsResponseRequest.setReturnDate(rentsEntity.getReturnDate());
        Status status = getStatusReturned(rentsEntity);
        rentsResponseRequest.setStatus(status);
        return rentsResponseRequest;
    }

    @Override
    public RentsEntity findById(Integer id) {
        return rentsRepository.findById(id)
                .orElseThrow(() -> new NotFoundExceptions("Aluguel",id));
    }

    private Status getStatusReturned(RentsEntity rent) {
        LocalDate forecastDate = rent.getForecastDate();
        LocalDate returnDate = rent.getReturnDate();

        LocalDate currentDate = LocalDate.now();

        if (returnDate != null) {
            if (returnDate.isAfter(forecastDate)) {
                return Status.RETURNED_DELAY;
            } else {
                return Status.RETURNED_ON_TIME;
            }
        } else if (forecastDate.isBefore(currentDate)) {
            return Status.LATE_RENT;
        } else {
            return Status.PENDING_RENT;
        }
    }
}
