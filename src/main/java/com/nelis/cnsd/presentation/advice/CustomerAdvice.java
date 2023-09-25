package com.nelis.cnsd.presentation.advice;

import com.nelis.cnsd.presentation.exceptions.CustomerNotFoundResponse;
import com.nelis.cnsd.service.exceptions.CustomerNotFound;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomerAdvice {

    @ExceptionHandler(CustomerNotFound.class)
    @ApiResponse(responseCode = "404", description = "Customer not found error",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = CustomerNotFoundResponse.class)))
    protected ResponseEntity<CustomerNotFoundResponse> handleCustomerNotFound(CustomerNotFound exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new CustomerNotFoundResponse(exception.getMessage()));
    }
}
