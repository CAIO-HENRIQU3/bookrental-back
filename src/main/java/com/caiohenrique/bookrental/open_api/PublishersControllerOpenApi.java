package com.caiohenrique.bookrental.open_api;

import com.caiohenrique.bookrental.io.publishers.PublishersCreateRequest;
import com.caiohenrique.bookrental.io.publishers.PublishersUpdateRequest;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

public interface PublishersControllerOpenApi {

    @ApiOperation(value = "Create Publishers")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Method Returned Successfully"),
            @ApiResponse(code = 400, message = "Missing Data, Check and Try Again.")
    })
    @PostMapping
    ResponseEntity<Void> create(@RequestBody @Valid PublishersCreateRequest publishersCreateRequest);

    @ApiOperation(value = "Get All Publishers")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Method Returned Successfully"),
            @ApiResponse(code = 400, message = "Missing Data, Check and Try Again.")
    })
    @GetMapping
    List<PublishersUpdateRequest> getAll();

    @ApiOperation(value = "Get PublishersById")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Method Returned Successfully"),
            @ApiResponse(code = 400, message = "Missing Data, Check and Try Again.")
    })
    @GetMapping("/{id}")
    ResponseEntity<PublishersUpdateRequest> getPublisherById(@PathVariable Integer id);

    @ApiOperation(value = "Update Publishers")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Method Returned Successfully"),
            @ApiResponse(code = 400, message = "Missing Data, Check and Try Again.")
    })
    @PutMapping
    ResponseEntity<Void> update(@RequestBody @Valid PublishersUpdateRequest publishersUpdateRequest);

    @ApiOperation(value = "Delete Publishers")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Method Returned Successfully"),
            @ApiResponse(code = 400, message = "Missing Data, Check and Try Again.")
    })
    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable Integer id);
}
