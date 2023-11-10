package com.caiohenrique.bookrental.entities;

import com.caiohenrique.bookrental.io.rents.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "rental_tb")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RentsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UsersEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", nullable = false)
    private BooksEntity book;

    @Column(name = "rental_date", nullable = false)
    private LocalDate rentalDate = LocalDate.now();

    @Column(name = "forecast_date", nullable = false)
    private LocalDate forecastDate;

    @Column(name = "return_date")
    private LocalDate returnDate;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;
}
