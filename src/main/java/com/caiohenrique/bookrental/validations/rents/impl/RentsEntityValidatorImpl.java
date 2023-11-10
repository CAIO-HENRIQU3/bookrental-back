package com.caiohenrique.bookrental.validations.rents.impl;

import com.caiohenrique.bookrental.entities.RentsEntity;
import com.caiohenrique.bookrental.exceptions.BusinessExceptions;
import com.caiohenrique.bookrental.repositories.RentsRepository;
import com.caiohenrique.bookrental.validations.rents.RentsEntityValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class RentsEntityValidatorImpl implements RentsEntityValidator {

    @Autowired
    private RentsRepository rentsRepository;

    @Override
    public void validateForCreate(RentsEntity entity) {
        List<String> errors = new ArrayList<>();
        validationsCreate(entity, errors);
        if (!errors.isEmpty()) {
            throw new BusinessExceptions(String.join(" ", errors));
        }
    }

    @Override
    public void validateForGiveback(Integer id) {
        List<String> errors = new ArrayList<>();
        validationsGiveback(id, errors);
        if (!errors.isEmpty()) {
            throw new BusinessExceptions(String.join(" ", errors));
        }
    }

    private void validationsCreate(RentsEntity entity, List<String> errors) {
        if (entity.getForecastDate() == null) {
            errors.add("O Campo Previsão de Devolução, não pode estar em branco!");
        }
        if (entity.getForecastDate() == null) {
            errors.add("O Campo Livro, não pode estar em branco!");
        }
        if (entity.getForecastDate().isBefore(entity.getRentalDate())) {
            errors.add("A data de previsão não deve ser inferior à data de aluguel!");
        }

        Integer availableAmount = entity.getBook().getAmount();

        if (availableAmount < 1) {
            errors.add("O Livro selecionado não possui copias disponiveis!");
        }

        LocalDate currentDate = LocalDate.now();
        LocalDate forecastDate = entity.getForecastDate();

        long daysBetween = ChronoUnit.DAYS.between(currentDate, forecastDate);

        if (daysBetween > 31) {
            errors.add("A data de previsão não pode ser superior a 31 dias após a data atual.");
        }

        int forecastDateYear = entity.getForecastDate().getYear();
        int currentYear = LocalDate.now().getYear();

        if (forecastDateYear > currentYear) {
            errors.add("O Ano inserido não pode ser superior ao atual!");
        }
    }

    private void validationsGiveback(Integer id, List<String> errors) {
        Optional<RentsEntity> entity = rentsRepository.findByIdAndReturnDateNull(id);
        if (entity.isEmpty()) {
            errors.add("Este Aluguel já foi devolvido");
        }
    }

}