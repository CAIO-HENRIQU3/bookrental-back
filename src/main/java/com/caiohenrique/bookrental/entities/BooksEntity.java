package com.caiohenrique.bookrental.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Table(name = "book_tb")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BooksEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "author", nullable = false)
    private String author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "publisher_id")
    private PublishersEntity publisher;

    @Column(name = "release_year", nullable = false)
    private Integer releaseYear;

    @Column(name = "amount")
    private Integer amount = 0;

    @Column(name = "pending_quantity")
    private Integer pendingQuantity = 0;

    @Column(name = "total_rented")
    private Integer totalRented = 0;
}