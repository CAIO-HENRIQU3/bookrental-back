package com.caiohenrique.bookrental.services.books;

import com.caiohenrique.bookrental.entities.BooksEntity;
import com.caiohenrique.bookrental.io.books.BookMostRentResponseRequest;
import com.caiohenrique.bookrental.io.books.BooksCreateRequest;
import com.caiohenrique.bookrental.io.books.BooksResponseRequest;
import com.caiohenrique.bookrental.io.books.BooksUpdateRequest;

import java.util.List;

public interface BooksService {

    BooksEntity findById(Integer id);

    void create(BooksCreateRequest booksCreateRequest);

    List<BooksResponseRequest> getAll();

    List<BookMostRentResponseRequest> getMostRentBooks();

    void delete(Integer id);

    void update(BooksUpdateRequest booksUpdateRequest);
}
