package com.caiohenrique.bookrental.controllers;

import com.caiohenrique.bookrental.io.books.*;
import com.caiohenrique.bookrental.open_api.BooksControllerOpenApi;
import com.caiohenrique.bookrental.services.books.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/books")
public class BooksController implements BooksControllerOpenApi {

    @Autowired
    private BooksService booksService;

    @Autowired
    private BookMapper bookMapper;

    @PostMapping
    @Override
    public ResponseEntity<Void> create(@RequestBody @Valid BooksCreateRequest booksCreateRequest) {
        booksService.create(booksCreateRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    @Override
    public List<BooksResponseRequest> getAll() {
        return booksService.getAll();
    }

    @GetMapping("/mostRentBooks")
    @Override
    public List<BookMostRentResponseRequest> getMostRentBooks() {
        return booksService.getMostRentBooks();
    }


    @PutMapping
    @Override
    public ResponseEntity<Void> update(@RequestBody @Valid BooksUpdateRequest booksUpdateRequest) {
        booksService.update(booksUpdateRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        booksService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}