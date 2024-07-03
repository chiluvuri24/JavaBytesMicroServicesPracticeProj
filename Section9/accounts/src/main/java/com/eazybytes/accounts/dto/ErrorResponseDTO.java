package com.eazybytes.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data@AllArgsConstructor
@Schema(
        name="ErrorResponses",
        description = "Schema used to display the errors and exceptions"
)
public class ErrorResponseDTO {


    @Schema(
            description = "GIves you an API Path", example = "/api/v1/accounts/create"
    )
    private String apiPath;

    @Schema(
            description = "Shows error Code", example = "404,501"
    )
    private HttpStatus errorCode;

    @Schema(
            description = "Desc about error message", example = "raised due to so and so reason"
    )
    private String errorMsg;
    @Schema(
            description = "Exception raised Time", example = "14:56 AM"
    )
    private LocalDateTime errorTime;


}
