package com.caiohenrique.bookrental.io.books;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BooksCreateRequest {

    private String name;

    private String author;

    private Integer publisherId;

    private Integer releaseYear;

    private Integer amount;

}
