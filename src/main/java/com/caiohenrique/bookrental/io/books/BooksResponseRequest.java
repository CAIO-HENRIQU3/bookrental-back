package com.caiohenrique.bookrental.io.books;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BooksResponseRequest {

    private Integer id;

    private String name;

    private String author;

    private Integer publisherId;

    private String publisherName;

    private Integer releaseYear;

    private Integer amount;

    private Integer rentedQuantity;

    private Integer totalRented;
}
