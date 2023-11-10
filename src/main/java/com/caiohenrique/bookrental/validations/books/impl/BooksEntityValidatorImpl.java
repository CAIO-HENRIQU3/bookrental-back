package com.caiohenrique.bookrental.validations.books.impl;

import com.caiohenrique.bookrental.entities.BooksEntity;
import com.caiohenrique.bookrental.entities.RentsEntity;
import com.caiohenrique.bookrental.exceptions.BusinessExceptions;
import com.caiohenrique.bookrental.repositories.BooksRepository;
import com.caiohenrique.bookrental.repositories.RentsRepository;
import com.caiohenrique.bookrental.validations.books.BooksEntityValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class BooksEntityValidatorImpl implements BooksEntityValidator {

    @Autowired
    private BooksRepository booksRepository;

    @Autowired
    private RentsRepository rentsRepository;

    @Override
    public void validateForCreateBooks(BooksEntity entity) {
        List<String> errors = new ArrayList<>();
        validationsCreate(entity, errors);
        if (!errors.isEmpty()) {
            throw new BusinessExceptions(String.join(" ", errors));
        }
    }

    @Override
    public void validateForDeleteBook(Integer id) {
        List<String> errors = new ArrayList<>();
        validationsDelete(id, errors);
        if (!errors.isEmpty()) {
            throw new BusinessExceptions(String.join(" ", errors));
        }
    }

    @Override
    public void validateForUpdateBooks(BooksEntity entity) {
        List<String> errors = new ArrayList<>();
        validationsUpdate(entity, errors);
        if (!errors.isEmpty()) {
            throw new BusinessExceptions(String.join(" ", errors));
        }
    }

    private void validationsCreate(BooksEntity entity, List<String> errors) {
        if (entity.getName() == null || entity.getName().trim().isEmpty()) {
            errors.add("O Campo Nome, não pode estar em branco!");
        }
        if (entity.getAuthor() == null || entity.getAuthor().trim().isEmpty()) {
            errors.add("O Campo Autor, não pode estar em branco!");
        }
        if (entity.getPublisher() == null || entity.getPublisher().getId() == null) {
            errors.add("O Campo PublisherId, não pode estar em branco!");
        }
        if (entity.getReleaseYear() == null) {
            errors.add("O Campo Lançamento, não pode estar em branco!");
        }
        if (entity.getAmount() == null) {
            errors.add("O Campo Quantidade, não pode estar em branco!");
        }

        if (entity.getAmount() < 1) {
            errors.add("A quantidade de cópias do livro deve ser maior que 0!");
        }

        String bookName = entity.getName();
        Integer publisherId = entity.getPublisher().getId();

        if (booksRepository.existsByNameAndPublisherId(bookName, publisherId)) {
            errors.add("A Editora selecionada já possui um livro com o mesmo nome!");
        }

        int releaseDateYear = entity.getReleaseYear();
        int currentYear = LocalDate.now().getYear();

        if (releaseDateYear > currentYear) {
            errors.add("O Ano inserido não pode ser superior ao atual!");
        }
        if (releaseDateYear < 1) {
            errors.add("O Ano inserido não pode ser menor que 1!");
        }
    }

    private void validationsDelete(Integer id, List<String> errors) {

        List<RentsEntity> rentsEntities = rentsRepository.findAllByBookId(id);

        if (!rentsEntities.isEmpty()) {
            errors.add("Não é possível deletar esse livro pois possui associações a Aluguéis!");
        }
    }

    private void validationsUpdate(BooksEntity entity, List<String> errors) {
        if (entity.getId() == null) {
            errors.add("O Campo Id, não pode estar em branco!");
        }
        if (entity.getName() == null || entity.getName().trim().isEmpty()) {
            errors.add("O Campo Nome, não pode estar em branco!");
        }
        if (entity.getAuthor() == null || entity.getAuthor().trim().isEmpty()) {
            errors.add("O Campo Autor, não pode estar em branco!");
        }
        if (entity.getPublisher() == null || entity.getPublisher().getId() == null) {
            errors.add("O Campo PublisherId, não pode estar em branco!");
        }
        if (entity.getReleaseYear() == null) {
            errors.add("O Campo Lançamento, não pode estar em branco!");
        }
        if (entity.getAmount() == null) {
            errors.add("O Campo Quantidade, não pode estar em branco!");
        }
        if (booksRepository.existsByNameAndIdNot(entity.getName(), entity.getId())) {
            errors.add("O Livro informado já está cadastrado!");
        }
        if (entity.getAmount() < 0) {
            errors.add("A quantidade de cópias do livro deve ser maior ou igual à 0!");
        }

        int releaseDateYear = entity.getReleaseYear();
        int currentYear = LocalDate.now().getYear();

        if (releaseDateYear > currentYear) {
            errors.add("O Ano inserido não pode ser superior ao atual!");
        }
        if (releaseDateYear < 1) {
            errors.add("O Ano inserido não pode ser menor que 1!");
        }
    }
}