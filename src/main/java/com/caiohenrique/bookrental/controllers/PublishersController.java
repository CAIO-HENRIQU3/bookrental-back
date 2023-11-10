package com.caiohenrique.bookrental.controllers;

import com.caiohenrique.bookrental.entities.PublishersEntity;
import com.caiohenrique.bookrental.io.publishers.PublisherMapper;
import com.caiohenrique.bookrental.io.publishers.PublishersCreateRequest;
import com.caiohenrique.bookrental.io.publishers.PublishersUpdateRequest;
import com.caiohenrique.bookrental.open_api.PublishersControllerOpenApi;
import com.caiohenrique.bookrental.services.publishers.PublishersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/publishers")
public class PublishersController implements PublishersControllerOpenApi {

    @Autowired
    private PublishersService publishersService;

    @Autowired
    private PublisherMapper publisherMapper;

    @PostMapping
    @Override
    public ResponseEntity<Void> create(@RequestBody @Valid PublishersCreateRequest publishersCreateRequest) {
        publishersService.create(publishersCreateRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    @Override
    public List<PublishersUpdateRequest> getAll() {
        return publishersService.getAll();
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<PublishersUpdateRequest> getPublisherById(@PathVariable Integer id) {
        PublishersEntity publishersEntity = publishersService.findById(id);
        PublishersUpdateRequest response = publisherMapper.toPublisherDto(publishersEntity);
        return ResponseEntity.ok(response);
    }

    @PutMapping
    @Override
    public ResponseEntity<Void> update(@RequestBody @Valid PublishersUpdateRequest publishersUpdateRequest) {
        publishersService.update(publishersUpdateRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        publishersService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}