package com.caiohenrique.bookrental.services.users.impl;

import com.caiohenrique.bookrental.entities.UsersEntity;
import com.caiohenrique.bookrental.exceptions.NotFoundExceptions;
import com.caiohenrique.bookrental.io.users.UsersCreateRequest;
import com.caiohenrique.bookrental.io.users.UsersUpdateRequest;
import com.caiohenrique.bookrental.repositories.UsersRepository;
import com.caiohenrique.bookrental.services.users.UsersService;
import com.caiohenrique.bookrental.validations.users.UsersEntityValidator;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private UsersEntityValidator usersEntityValidator;

    @Override
    public void create(UsersCreateRequest usersCreateRequest) {
        UsersEntity entity = new UsersEntity();
        entity.setName(usersCreateRequest.getName().trim());
        entity.setAddress(usersCreateRequest.getAddress().trim());
        entity.setCity(usersCreateRequest.getCity().trim());
        entity.setEmail(usersCreateRequest.getEmail().trim());
        usersEntityValidator.validateForCreateUsers(entity);
        usersRepository.save(entity);
    }

    @Override
    public List<UsersUpdateRequest> getAll() {
        List<UsersEntity> users = usersRepository.findAll();
        return users.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private UsersUpdateRequest convertToDto(UsersEntity usersEntity) {
        UsersUpdateRequest usersUpdateRequest = new UsersUpdateRequest();
        BeanUtils.copyProperties(usersEntity, usersUpdateRequest);
        return usersUpdateRequest;
    }

    @Override
    public void delete(Integer id) {
        UsersEntity entity = findById(id);
        usersEntityValidator.validateForDeleteUsers(id);
        usersRepository.delete(entity);
    }

    @Override
    public void update(UsersUpdateRequest usersUpdateRequest) {
        UsersEntity entity = findById(usersUpdateRequest.getId() == null ? 0 : usersUpdateRequest.getId());
        entity.setName(usersUpdateRequest.getName().trim());
        entity.setAddress(usersUpdateRequest.getAddress().trim());
        entity.setCity(usersUpdateRequest.getCity().trim());
        entity.setEmail(usersUpdateRequest.getEmail().trim());
        usersEntityValidator.validateForUpdateUsers(entity, usersUpdateRequest);
        usersRepository.save(entity);
    }

    @Override
    public UsersEntity findById(Integer id) {
        return usersRepository.findById(id)
                .orElseThrow(() -> new NotFoundExceptions("Usuário",id));
    }


}