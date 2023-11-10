package com.caiohenrique.bookrental.open_api;

import com.caiohenrique.bookrental.io.rents.RentTotalByStatusResponseRequest;
import com.caiohenrique.bookrental.io.rents.RentsCreateRequest;
import com.caiohenrique.bookrental.io.rents.RentsResponseRequest;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

public interface RentsControllerOpenApi {

    @ApiOperation(value = "Create Rents")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Method Returned Successfully"),
            @ApiResponse(code = 400, message = "Missing Data, Check and Try Again.")
    })
    @PostMapping
    ResponseEntity<Void> create(@RequestBody @Valid RentsCreateRequest rentsCreateRequest);

    @ApiOperation(value = "Get All Rents")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Method Returned Successfully"),
            @ApiResponse(code = 400, message = "Missing Data, Check and Try Again.")
    })
    @GetMapping
    List<RentsResponseRequest> getAll();

    @ApiOperation(value = "Get TotalByStatus Rents")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Method Returned Successfully"),
            @ApiResponse(code = 400, message = "Missing Data, Check and Try Again.")
    })
    @GetMapping("/totalByStatus")
    List<RentTotalByStatusResponseRequest> getTotalByStatus();

    @ApiOperation(value = "Return Rents")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Method Returned Successfully"),
            @ApiResponse(code = 400, message = "Missing Data, Check and Try Again.")
    })
    @PutMapping("giveback/{id}")
    ResponseEntity<Void> returnRent(@PathVariable Integer id);
}
