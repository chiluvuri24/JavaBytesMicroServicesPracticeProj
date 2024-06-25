package com.eazybytes.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(
        name="Account",
        description = "Schema to hold Account Data"
)
public class AccountsDTO {
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Account Number must be 10 digits")
    @Schema(
            description = "Account Number of Account", example = "6767435434"
    )
    private Long accountNumber;

    @Schema(
            description = "Account Type", example = "Savings"
    )
    @NotEmpty(message = "Account Type should not be null or empty")
    private String accountType;

    @Schema(
            description = "Branch Address", example = "Easteren Street, Kingstown, Delhi"
    )
    @NotEmpty(message = "Branch Address should not be null or empty")
    private String branchAddress;
}
