package com.caiohenrique.bookrental.services.users;

import com.caiohenrique.bookrental.entities.UsersEntity;
import com.caiohenrique.bookrental.io.users.UsersCreateRequest;
import com.caiohenrique.bookrental.io.users.UsersUpdateRequest;

import java.util.List;

public interface UsersService {
    void create(UsersCreateRequest usersCreateRequest);

    UsersEntity findById(Integer id);

    List<UsersUpdateRequest> getAll();

    void delete(Integer id);

    void update(UsersUpdateRequest usersUpdateRequest);
}
