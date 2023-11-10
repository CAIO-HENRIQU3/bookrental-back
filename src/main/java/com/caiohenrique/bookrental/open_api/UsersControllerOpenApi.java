package com.caiohenrique.bookrental.open_api;

import com.caiohenrique.bookrental.io.users.UsersCreateRequest;
import com.caiohenrique.bookrental.io.users.UsersUpdateRequest;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

public interface UsersControllerOpenApi {

    @ApiOperation(value = "Create Users")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Method Returned Successfully"),
            @ApiResponse(code = 400, message = "Missing Data, Check and Try Again.")
    })
    @PostMapping
    ResponseEntity<Void> create(@RequestBody @Valid UsersCreateRequest usersCreateRequest);

    @ApiOperation(value = "Get All Users")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Method Returned Successfully"),
            @ApiResponse(code = 400, message = "Missing Data, Check and Try Again.")
    })
    @GetMapping
    List<UsersUpdateRequest> getAllUsers();

    @ApiOperation(value = "Get UsersById")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Method Returned Successfully"),
            @ApiResponse(code = 400, message = "Missing Data, Check and Try Again.")
    })
    @GetMapping("/{id}")
    ResponseEntity<UsersUpdateRequest> getUserById(@PathVariable Integer id);

    @ApiOperation(value = "Update Users")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Method Returned Successfully"),
            @ApiResponse(code = 400, message = "Missing Data, Check and Try Again.")
    })
    @PutMapping
    ResponseEntity<Void> updateUser(@RequestBody @Valid UsersUpdateRequest usersUpdateRequest);

    @ApiOperation(value = "Delete Users")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Method Returned Successfully"),
            @ApiResponse(code = 400, message = "Missing Data, Check and Try Again.")
    })
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteUser(@PathVariable Integer id);
}
