package com.caiohenrique.bookrental.repositories;

import com.caiohenrique.bookrental.entities.RentsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RentsRepository extends JpaRepository<RentsEntity, Integer> {

    List<RentsEntity> findByUserId(Integer id);

    List<RentsEntity> findAllByBookId(Integer id);

    Optional<RentsEntity> findByIdAndReturnDateNull(Integer id);

}
