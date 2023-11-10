package com.caiohenrique.bookrental.services.books.impl;

import com.caiohenrique.bookrental.entities.BooksEntity;
import com.caiohenrique.bookrental.exceptions.BusinessExceptions;
import com.caiohenrique.bookrental.exceptions.NotFoundExceptions;
import com.caiohenrique.bookrental.io.books.BookMostRentResponseRequest;
import com.caiohenrique.bookrental.io.books.BooksCreateRequest;
import com.caiohenrique.bookrental.io.books.BooksResponseRequest;
import com.caiohenrique.bookrental.io.books.BooksUpdateRequest;
import com.caiohenrique.bookrental.repositories.BooksRepository;
import com.caiohenrique.bookrental.services.books.BooksService;
import com.caiohenrique.bookrental.services.publishers.PublishersService;
import com.caiohenrique.bookrental.validations.books.BooksEntityValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BooksServiceImpl implements BooksService {

    @Autowired
    private BooksRepository booksRepository;

    @Autowired
    private BooksEntityValidator booksEntityValidator;

    @Autowired
    private PublishersService publishersService;

    @Override
    public void create(BooksCreateRequest booksCreateRequest) {
        if (booksCreateRequest.getPublisherId() == null) {
            throw new BusinessExceptions("O Campo PublisherId, não pode estar em branco!");
        }

        BooksEntity entity = new BooksEntity();

        if (booksCreateRequest.getAmount() == null) {
            throw new BusinessExceptions("O Campo Quantidade, não pode estar em branco!");
        }
        if (booksCreateRequest.getReleaseYear() == null) {
            throw new BusinessExceptions("O Campo Lançamento, não pode estar em branco!");
        }

        entity.setName(booksCreateRequest.getName().trim());
        entity.setAuthor(booksCreateRequest.getAuthor().trim());
        entity.setPublisher(publishersService.findById(booksCreateRequest.getPublisherId()));
        entity.setReleaseYear(booksCreateRequest.getReleaseYear());
        entity.setAmount(booksCreateRequest.getAmount());
        booksEntityValidator.validateForCreateBooks(entity);
        booksRepository.save(entity);
    }

    @Override
    public List<BooksResponseRequest> getAll() {
        List<BooksEntity> entity = booksRepository.findAll();
        return entity.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<BookMostRentResponseRequest> getMostRentBooks() {
        List<BooksEntity> entity = booksRepository.findTop4ByOrderByTotalRentedDesc();
        return entity.stream()
                .map(this::convertToMostRentDto)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        BooksEntity entity = findById(id);
        booksEntityValidator.validateForDeleteBook(id);
        booksRepository.delete(entity);
    }

    @Override
    public void update(BooksUpdateRequest booksUpdateRequest) {
        if (booksUpdateRequest.getPublisherId() == null) {
            throw new BusinessExceptions("O Campo PublisherId, não pode estar em branco!");
        }

        BooksEntity entity = findById(booksUpdateRequest.getId());

        if (booksUpdateRequest.getAmount() == null) {
            throw new BusinessExceptions("O Campo Quantidade, não pode estar em branco!");
        }
        if (booksUpdateRequest.getReleaseYear() == null) {
            throw new BusinessExceptions("O Campo Lançamento, não pode estar em branco!");
        }

        entity.setName(booksUpdateRequest.getName().trim());
        entity.setAuthor(booksUpdateRequest.getAuthor().trim());
        entity.setPublisher(publishersService.findById(booksUpdateRequest.getPublisherId()));
        entity.setReleaseYear(booksUpdateRequest.getReleaseYear());
        entity.setAmount(booksUpdateRequest.getAmount());
        booksEntityValidator.validateForUpdateBooks(entity);
        booksRepository.save(entity);
    }

    private BooksResponseRequest convertToDto(BooksEntity booksEntity) {
        BooksResponseRequest booksResponseRequest = new BooksResponseRequest();
        booksResponseRequest.setId(booksEntity.getId());
        booksResponseRequest.setName(booksEntity.getName().trim());
        booksResponseRequest.setAuthor(booksEntity.getAuthor().trim());
        booksResponseRequest.setPublisherId(booksEntity.getPublisher().getId());
        booksResponseRequest.setPublisherName(booksEntity.getPublisher().getName().trim());
        booksResponseRequest.setReleaseYear(booksEntity.getReleaseYear());
        booksResponseRequest.setAmount(booksEntity.getAmount());
        booksResponseRequest.setRentedQuantity(booksEntity.getRentedQuantity());
        booksResponseRequest.setTotalRented(booksEntity.getTotalRented());
        return booksResponseRequest;
    }

    private BookMostRentResponseRequest convertToMostRentDto(BooksEntity booksEntity) {
        BookMostRentResponseRequest bookMostRentResponseRequest = new BookMostRentResponseRequest();
        bookMostRentResponseRequest.setId(booksEntity.getId());
        bookMostRentResponseRequest.setName(booksEntity.getName().trim());
        bookMostRentResponseRequest.setTotalRented(booksEntity.getTotalRented());
        return bookMostRentResponseRequest;
    }

    @Override
    public BooksEntity findById(Integer id) {
        if (id == null || id == 0) {
            throw new NotFoundExceptions("Livro", id);
        }
        return booksRepository.findById(id)
                .orElseThrow(() -> new NotFoundExceptions("Livro",id));
    }

}