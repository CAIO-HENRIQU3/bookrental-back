package com.caiohenrique.bookrental.io.books;

import com.caiohenrique.bookrental.entities.BooksEntity;
import com.caiohenrique.bookrental.services.books.BooksService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {
    @Autowired
    private BooksService booksService;

    public BooksResponseRequest toBookDto(BooksEntity booksEntity) {
        BooksResponseRequest booksResponseRequest = new BooksResponseRequest();
        booksResponseRequest.setName(booksResponseRequest.getName());
        booksResponseRequest.setAuthor(booksResponseRequest.getAuthor());
        booksResponseRequest.setPublisherName(booksEntity.getPublisher().getName());
        booksResponseRequest.setReleaseYear(booksResponseRequest.getReleaseYear());
        booksResponseRequest.setAmount(booksResponseRequest.getAmount());
        booksResponseRequest.setPendingQuantity(booksResponseRequest.getPendingQuantity());
        booksResponseRequest.setTotalRented(booksResponseRequest.getTotalRented());
        return booksResponseRequest;
    }

    public BooksEntity toBookEntity(BooksUpdateRequest request) {
        BooksEntity entity = booksService.findById(request.getId());
        BeanUtils.copyProperties(request, entity);
        return entity;
    }
}