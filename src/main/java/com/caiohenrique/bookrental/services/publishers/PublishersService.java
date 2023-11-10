package com.caiohenrique.bookrental.services.publishers;

import com.caiohenrique.bookrental.entities.PublishersEntity;
import com.caiohenrique.bookrental.io.publishers.PublishersCreateRequest;
import com.caiohenrique.bookrental.io.publishers.PublishersUpdateRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PublishersService {

    void create(PublishersCreateRequest publishersCreateRequest);

    List<PublishersUpdateRequest> getAll();

    PublishersEntity findById(Integer id);

    void delete(Integer id);

    void update(PublishersUpdateRequest publishersUpdateRequest);
}