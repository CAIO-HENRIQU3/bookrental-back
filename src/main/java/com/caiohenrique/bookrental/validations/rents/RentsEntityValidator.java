package com.caiohenrique.bookrental.validations.rents;

import com.caiohenrique.bookrental.entities.RentsEntity;

public interface RentsEntityValidator {

    void validateForCreate(RentsEntity entity);

    void validateForGiveback(Integer id);
}
