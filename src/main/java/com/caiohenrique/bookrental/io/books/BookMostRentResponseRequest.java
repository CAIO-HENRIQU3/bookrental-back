package com.caiohenrique.bookrental.io.books;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookMostRentResponseRequest {
    private Integer id;
    private String name;
    private Integer totalRented;
}
