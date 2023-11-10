package com.caiohenrique.bookrental.validations.books;

import com.caiohenrique.bookrental.entities.BooksEntity;

public interface BooksEntityValidator {
    void validateForCreateBooks(BooksEntity entity);

    void validateForDeleteBook(Integer id);

    void validateForUpdateBooks(BooksEntity entity);
}
