package com.caiohenrique.bookrental.open_api;

import com.caiohenrique.bookrental.io.books.BookMostRentResponseRequest;
import com.caiohenrique.bookrental.io.books.BooksCreateRequest;
import com.caiohenrique.bookrental.io.books.BooksResponseRequest;
import com.caiohenrique.bookrental.io.books.BooksUpdateRequest;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

public interface BooksControllerOpenApi {

    @ApiOperation(value = "Create Books")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Method Returned Successfully"),
            @ApiResponse(code = 400, message = "Missing Data, Check and Try Again.")
    })
    @PostMapping
    ResponseEntity<Void> create(@RequestBody @Valid BooksCreateRequest booksCreateRequest);

    @ApiOperation(value = "Get All Books")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Book Created Successfully"),
            @ApiResponse(code = 400, message = "Missing Data, Check and Try Again.")
    })
    @GetMapping
    List<BooksResponseRequest> getAll();

    @ApiOperation(value = "Get MostRented Books")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Method Returned Successfully"),
            @ApiResponse(code = 400, message = "Missing Data, Check and Try Again.")
    })
    @GetMapping("/mostRentBooks")
    List<BookMostRentResponseRequest> getMostRentBooks();

    @ApiOperation(value = "Update Books")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Method Returned Successfully"),
            @ApiResponse(code = 400, message = "Missing Data, Check and Try Again.")
    })
    @PutMapping
    ResponseEntity<Void> update(@RequestBody @Valid BooksUpdateRequest booksUpdateRequest);

    @ApiOperation(value = "Delete Books")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Method Returned Successfully"),
            @ApiResponse(code = 400, message = "Missing Data, Check and Try Again.")
    })
    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable Integer id);
}
