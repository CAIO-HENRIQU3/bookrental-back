package com.caiohenrique.bookrental.validations.users;

import com.caiohenrique.bookrental.entities.UsersEntity;
import com.caiohenrique.bookrental.io.users.UsersUpdateRequest;

public interface UsersEntityValidator {
    void validateForCreateUsers(UsersEntity entity);

    void validateForDeleteUsers(Integer id);

    void validateForUpdateUsers(UsersEntity entity, UsersUpdateRequest usersUpdateRequest);
}
