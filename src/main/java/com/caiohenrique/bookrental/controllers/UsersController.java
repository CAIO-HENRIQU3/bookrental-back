package com.caiohenrique.bookrental.controllers;

import com.caiohenrique.bookrental.entities.UsersEntity;
import com.caiohenrique.bookrental.io.users.UserMapper;
import com.caiohenrique.bookrental.io.users.UsersCreateRequest;
import com.caiohenrique.bookrental.io.users.UsersUpdateRequest;
import com.caiohenrique.bookrental.open_api.UsersControllerOpenApi;
import com.caiohenrique.bookrental.services.users.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/users")
public class UsersController implements UsersControllerOpenApi {
    @Autowired
    private UsersService usersService;

    @Autowired
    private UserMapper userMapper;

    @PostMapping
    @Override
    public ResponseEntity<Void> create(@RequestBody @Valid UsersCreateRequest usersCreateRequest) {
        usersService.create(usersCreateRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    @Override
    public List<UsersUpdateRequest> getAllUsers() {
        return usersService.getAll();
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<UsersUpdateRequest> getUserById(@PathVariable Integer id) {
        UsersEntity usersEntity = usersService.findById(id);
        UsersUpdateRequest response = userMapper.toUsersDto(usersEntity);
        return ResponseEntity.ok(response);
    }

    @PutMapping
    @Override
    public ResponseEntity<Void> updateUser(@RequestBody @Valid UsersUpdateRequest usersUpdateRequest) {
        usersService.update(usersUpdateRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        usersService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
