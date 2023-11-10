package com.caiohenrique.bookrental.validations.users.impl;

import com.caiohenrique.bookrental.entities.RentsEntity;
import com.caiohenrique.bookrental.entities.UsersEntity;
import com.caiohenrique.bookrental.exceptions.BusinessExceptions;
import com.caiohenrique.bookrental.io.users.UsersUpdateRequest;
import com.caiohenrique.bookrental.repositories.RentsRepository;
import com.caiohenrique.bookrental.repositories.UsersRepository;
import com.caiohenrique.bookrental.validations.users.UsersEntityValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UsersEntityValidatorImpl implements UsersEntityValidator {

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    private RentsRepository rentsRepository;

    @Override
    public void validateForCreateUsers(UsersEntity entity) {
        List<String> errors = new ArrayList<>();
        validationsCreate(entity, errors);
        if (!errors.isEmpty()) {
            throw new BusinessExceptions(String.join(" ", errors));
        }
    }

    @Override
    public void validateForDeleteUsers(Integer id) {
        List<String> errors = new ArrayList<>();
        validationsDelete(id, errors);
        if (!errors.isEmpty()) {
            throw new BusinessExceptions(String.join(" ", errors));
        }
    }

    @Override
    public void validateForUpdateUsers(UsersEntity entity, UsersUpdateRequest updateRequest) {
        List<String> errors = new ArrayList<>();
        validationsUpdate(updateRequest, entity, errors);
        if (!errors.isEmpty()) {
            throw new BusinessExceptions(String.join(" ", errors));
        }
    }

    private void validationsCreate(UsersEntity entity, List<String> errors) {
        if (entity.getName() == null || entity.getName().trim().isEmpty()) {
            errors.add("O Campo Nome, não pode estar em branco!");
        }
        if (entity.getAddress() == null || entity.getAddress().trim().isEmpty()){
            errors.add("O Campo Endereço, não pode estar em branco!");
        }
        if (entity.getCity() == null || entity.getCity().trim().isEmpty()){
            errors.add("O Campo Cidade, não pode estar em branco!");
        }
        if (entity.getEmail() == null || entity.getEmail().trim().isEmpty()){
            errors.add("O Campo Email, não pode estar em branco!");
        }
        if (!isValidEmail(entity.getEmail())) {
            errors.add("Insira um e-mail válido.");
        }
        if (usersRepository.existsByEmail(entity.getEmail())) {
            errors.add("O email informado já está cadastrado!");
        }
    }

    private void validationsDelete(Integer id, List<String> errors) {
        List<RentsEntity> rentsEntity = rentsRepository.findByUserId(id);

        if (!rentsEntity.isEmpty()) {
            errors.add("Não é possivel deletar esse usuário pois possui associação a Aluguéis!");
        }
    }

    private void validationsUpdate(UsersUpdateRequest usersUpdateRequest, UsersEntity entity, List<String> errors) {
        if (entity.getName() == null || entity.getName().trim().isEmpty()) {
            errors.add("O Campo Nome, não pode estar em branco!");
        }
        if (entity.getAddress() == null || entity.getAddress().trim().isEmpty()){
            errors.add("O Campo Endereço, não pode estar em branco!");
        }
        if (entity.getCity() == null || entity.getCity().trim().isEmpty()){
            errors.add("O Campo Cidade, não pode estar em branco!");
        }
        if (entity.getEmail() == null || entity.getEmail().trim().isEmpty()){
            errors.add("O Campo Email, não pode estar em branco!");
        }
        if (!isValidEmail(entity.getEmail())) {
            errors.add("Insira um e-mail válido.");
        }
        if (usersRepository.existsByEmailAndIdNot(usersUpdateRequest.getEmail().trim(), entity.getId())) {
            errors.add("O E-mail informado já está cadastrado!");
        }
    }

    private boolean isValidEmail(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        return email.matches(regex);
    }
}

