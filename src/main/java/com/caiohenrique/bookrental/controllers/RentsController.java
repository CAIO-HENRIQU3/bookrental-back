package com.caiohenrique.bookrental.controllers;

import com.caiohenrique.bookrental.io.rents.RentTotalByStatusResponseRequest;
import com.caiohenrique.bookrental.io.rents.RentsCreateRequest;
import com.caiohenrique.bookrental.io.rents.RentsResponseRequest;
import com.caiohenrique.bookrental.open_api.RentsControllerOpenApi;
import com.caiohenrique.bookrental.services.rents.RentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/rentals")
public class RentsController implements RentsControllerOpenApi {

    @Autowired
    private RentsService rentsService;

    @PostMapping
    @Override
    public ResponseEntity<Void> create(@RequestBody @Valid RentsCreateRequest rentsCreateRequest) {
        rentsService.create(rentsCreateRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    @Override
    public List<RentsResponseRequest> getAll() {
        return rentsService.getAll();
    }

    @GetMapping("/totalByStatus")
    @Override
    public List<RentTotalByStatusResponseRequest> getTotalByStatus() {
        return rentsService.getTotalByStatus();
    }
    
    @PutMapping("giveback/{id}")
    @Override
    public ResponseEntity<Void> returnRent(@PathVariable Integer id) {
        rentsService.returnRent(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
