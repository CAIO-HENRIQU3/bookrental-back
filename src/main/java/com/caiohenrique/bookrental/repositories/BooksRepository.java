package com.caiohenrique.bookrental.repositories;

import com.caiohenrique.bookrental.entities.BooksEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BooksRepository extends JpaRepository<BooksEntity, Integer> {
    List<BooksEntity> findByPublisherId(Integer id);

    boolean existsByName(String name);

    boolean existsByNameAndIdNot(String name, Integer id);

    boolean existsByNameAndPublisherId(String bookName, Integer publisherId);

    List<BooksEntity> findTop4ByOrderByTotalRentedDesc();
}
