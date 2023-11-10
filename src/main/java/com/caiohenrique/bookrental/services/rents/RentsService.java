package com.caiohenrique.bookrental.services.rents;

import com.caiohenrique.bookrental.entities.RentsEntity;
import com.caiohenrique.bookrental.io.rents.RentTotalByStatusResponseRequest;
import com.caiohenrique.bookrental.io.rents.RentsCreateRequest;
import com.caiohenrique.bookrental.io.rents.RentsResponseRequest;

import java.util.List;

public interface RentsService {

    RentsEntity findById(Integer id);

    List<RentsResponseRequest> getAll();

    void create(RentsCreateRequest rentsCreateRequest);

    List<RentTotalByStatusResponseRequest> getTotalByStatus();

    void returnRent(Integer id);
}
