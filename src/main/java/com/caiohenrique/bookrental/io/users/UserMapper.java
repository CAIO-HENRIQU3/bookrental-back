package com.caiohenrique.bookrental.io.users;

import com.caiohenrique.bookrental.entities.UsersEntity;
import com.caiohenrique.bookrental.services.users.UsersService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    @Autowired
    private UsersService usersService;

    public UsersUpdateRequest toUsersDto(UsersEntity usersEntity) {
        UsersUpdateRequest usersUpdateRequest = new UsersUpdateRequest();
        BeanUtils.copyProperties(usersEntity, usersUpdateRequest);
        return usersUpdateRequest;
    }

    public UsersEntity toUsersEntity(UsersUpdateRequest request) {
        UsersEntity entity = usersService.findById(request.getId());
        BeanUtils.copyProperties(request, entity);
        return entity;
    }
}
