package com.eazybytes.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(
        name = "customer",
        description = "Schema to hold Customer and Account Information"
)
public class CustomerDTO {

    @NotEmpty(message = "Name should not be null or empty")
    @Size(min=3, max = 30, message = "Name size must be in between 3 and 30")
    @Schema(
            description = "Name of the Customer",example = "Eric"
    )
    private String name;

    @NotEmpty(message = "Email should not be null")
    @Email(message = "Email Address should be a valid value")
    @Schema(
            description = "Mail of the Customer",example = "Eric@rrr.com"
    )
    private String email;

    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile Number must be 10 digits")
    @Schema(
            description = "Mobile Number of the Customer",example = "9XXXXXXXX9"
    )
    private String mobileNumber;

    private AccountsDTO accountsDTO;

}
