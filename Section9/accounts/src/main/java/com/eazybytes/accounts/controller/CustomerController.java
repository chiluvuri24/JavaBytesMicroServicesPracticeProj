package com.eazybytes.accounts.controller;

import com.eazybytes.accounts.dto.CustomerDetailsDTO;
import com.eazybytes.accounts.dto.ErrorResponseDTO;
import com.eazybytes.accounts.service.ICustomerDetailsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api",produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
@Tag(
        name = "CURD Rest API for Customer in eazybank",
        description = "CRUD APIs to get Customer Details"
)
@AllArgsConstructor
public class CustomerController {


    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    private ICustomerDetailsService customerDetailsService;

    @GetMapping("/fetchCustomerDetails")
    @Operation(
            summary = "Fetch Customer Details REST API",
            description = "REST API to fetch Customer, Account, Loans, Cards details based on a mobile number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema= @Schema(implementation = ErrorResponseDTO.class)
                    )
            )
    }
    )
    public ResponseEntity<CustomerDetailsDTO> fetchCustomerDetails(@RequestHeader("eazybank-correlation-id") String corelationId
                                                                   ,@RequestParam
                                                                   @Pattern(regexp="(^$|[0-9]{10})",
                                                                            message = "Mobile number must be 10 digits")
                                                                   String mobileNumber){

           logger.debug("eazyBank-corelation-id found {}",corelationId);
           CustomerDetailsDTO customerDetailsDTO = customerDetailsService.fetchCustomerDetailsDTO(mobileNumber,corelationId);

           return ResponseEntity.status(HttpStatus.OK).body(customerDetailsDTO);
    }


}
