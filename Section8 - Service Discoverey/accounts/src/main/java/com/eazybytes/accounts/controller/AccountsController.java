package com.eazybytes.accounts.controller;

import com.eazybytes.accounts.constants.AccountsConstants;
import com.eazybytes.accounts.dto.AccountContactInfoDTO;
import com.eazybytes.accounts.dto.CustomerDTO;
import com.eazybytes.accounts.dto.ErrorResponseDTO;
import com.eazybytes.accounts.dto.ResponseDTO;
import com.eazybytes.accounts.service.impl.AccountsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/accounts",produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
@Tag(
        name = "CURD Rest API for Accounts in eazybank",
        description = "CRUD APIs to create, update, fetch, Delete Accounts"
)
public class AccountsController {

    private AccountsService accountsService;

    @Autowired
    private Environment environment;

    @Autowired
    private AccountContactInfoDTO accountContactInfoDTO;

    @Autowired
    public AccountsController(AccountsService accountsService){
        this.accountsService=accountsService;
    }

    @Value("${build.version}")
    private String buildVersion;

    @PostMapping("/create")
    @Operation(
            summary="API to create Accounts",
            description = "create API to create customer and Account"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HttpStatus Created"
    )
    public ResponseEntity<ResponseDTO> createAccount(@Valid @RequestBody CustomerDTO customerDTO){

        accountsService.createAccount(customerDTO);

        return ResponseEntity.status(HttpStatus.OK)
                             .body(new ResponseDTO(AccountsConstants.STATUS_201,AccountsConstants.MESSAGE_201));
    }


    @GetMapping("/fetch")
    @Operation(
            summary = "Fetch Account Details REST API",
            description = "REST API to fetch Customer &  Account details based on a mobile number"
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
    public ResponseEntity<CustomerDTO> fetchAccountDetails(@RequestParam @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile Number must be 10 digits") String mobileNumber){
        CustomerDTO customerDTO =  accountsService.fechAccountDetailsByMobileNumber(mobileNumber);

        return ResponseEntity.status(HttpStatus.OK).body(customerDTO);
    }

    @PutMapping("/update")
    @Operation(
            summary = "Update Account Details REST API",
            description = "REST API to update Customer &  Account details based on a account number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Expectation Failed"
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
    public ResponseEntity<ResponseDTO> updateAccounts(@Valid @RequestBody CustomerDTO customerDTO){

        boolean result = accountsService.updateAccount(customerDTO);

        return (result) ? ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(AccountsConstants.STATUS_200,AccountsConstants.MESSAGE_200))
                        : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDTO(AccountsConstants.STATUS_500,AccountsConstants.MESSAGE_500));
    }



    @Operation(
            summary = "Delete Account & Customer Details REST API",
            description = "REST API to delete Customer &  Account details based on a mobile number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Expectation Failed"
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
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDTO> deleteAccounts(@RequestParam
                                                          @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile Number must be 10 digits")
                                                          String mobileNumber){
        boolean result = accountsService.deleteAccount(mobileNumber);

        return (result) ? ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(AccountsConstants.STATUS_200,AccountsConstants.MESSAGE_200))
                : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDTO(AccountsConstants.STATUS_500,AccountsConstants.MESSAGE_500));
    }

    @Operation(
            summary = "Get  Build Number",
            description = "Get Build Version for this accounts service"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status OK"

    )
    @GetMapping("/build-version")
    public ResponseEntity<String> buildVersion(){
        return ResponseEntity.status(HttpStatus.OK).body(buildVersion);
    }

    @Operation(
            summary = "Get Java Version",
            description = "Get Java Version for this accounts service"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status OK"

    )
    @GetMapping("/java-version")
    public ResponseEntity<String> javaVersion(){
        return ResponseEntity.status(HttpStatus.OK).body(environment.getProperty("JAVA_HOME"));
    }

    @Operation(
            summary = "Get Contact Info",
            description = "Get Contact Info  for this accounts service"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status OK"

    )
    @GetMapping("/contact-info")
    public ResponseEntity<AccountContactInfoDTO> javaContactInfo(){
        return ResponseEntity.status(HttpStatus.OK).body(accountContactInfoDTO);
    }

}
