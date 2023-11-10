package com.caiohenrique.bookrental.validations.publishers.impl;

import com.caiohenrique.bookrental.entities.BooksEntity;
import com.caiohenrique.bookrental.entities.PublishersEntity;
import com.caiohenrique.bookrental.exceptions.BusinessExceptions;
import com.caiohenrique.bookrental.io.publishers.PublishersUpdateRequest;
import com.caiohenrique.bookrental.repositories.BooksRepository;
import com.caiohenrique.bookrental.repositories.PublishersRepository;
import com.caiohenrique.bookrental.validations.publishers.PublishersEntityValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PublisherEntityValidatorImpl implements PublishersEntityValidator {

    @Autowired
    private PublishersRepository publishersRepository;

    @Autowired
    private BooksRepository booksRepository;

    @Override
    public void validateForCreatePublishers(PublishersEntity publishersEntity) {
        List<String> errors = new ArrayList<>();
        validationsCreate(publishersEntity, errors);
        if (!errors.isEmpty()) {
            throw new BusinessExceptions(String.join(" ", errors));
        }
    }

    @Override
    public void validateForDeletePublishers(Integer id) {
        List<String> errors = new ArrayList<>();
        validationsDelete(id, errors);
        if (!errors.isEmpty()) {
            throw new BusinessExceptions(String.join(" ", errors));
        }
    }

    @Override
    public void validateForUpdatePublishers(PublishersEntity entity, PublishersUpdateRequest updateRequest) {
        List<String> errors = new ArrayList<>();
        validationsUpdate(updateRequest, entity, errors);
        if (!errors.isEmpty()) {
            throw new BusinessExceptions(String.join(" ", errors));
        }
    }

    private void validationsCreate(PublishersEntity publishersEntity, List<String> errors) {
        if (publishersEntity.getName() == null || publishersEntity.getName().trim().isEmpty()) {
            errors.add("O Campo Nome, não pode estar em branco!");
        }
        if (publishersEntity.getCity() == null || publishersEntity.getCity().trim().isEmpty()) {
            errors.add("O Campo Cidade, não pode estar em branco!");
        }
        if (publishersRepository.existsByName(publishersEntity.getName())) {
            errors.add("A Editora informada já está cadastrada!");
        }
    }

    private void validationsDelete(Integer id, List<String> errors) {
        List<BooksEntity> booksEntity = booksRepository.findByPublisherId(id);

        if (!booksEntity.isEmpty()) {
            errors.add("Não é possivel deletar essa editora pois possui associação a Livros!");
        }
    }

    private void validationsUpdate(PublishersUpdateRequest publishersUpdateRequest, PublishersEntity entity, List<String> errors) {
        if (entity.getName() == null || entity.getName().trim().isEmpty()) {
            errors.add("O Campo Nome, não pode estar em branco!");
        }
        if (entity.getCity() == null || entity.getCity().trim().isEmpty()) {
            errors.add("O Campo Cidade, não pode estar em branco!");
        }
        if (publishersRepository.existsByNameAndIdNot(publishersUpdateRequest.getName().trim(), entity.getId())) {
            errors.add("A Editora informada já está cadastrada!");
        }
    }
}
