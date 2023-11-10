package com.caiohenrique.bookrental.io.rents;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReturnedRentsResponseRequest {

    private Integer id;

    private String userName;

    private String bookName;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate forecastDate;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate rentalDate;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate returnDate;

    private Status status;
}