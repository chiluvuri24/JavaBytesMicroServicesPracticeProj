package com.eazybytes.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(
        name="Response"
        ,description = "Schema used to show succeessful responses"
)
public class ResponseDTO {
    @Schema(
            description = "Shows Http Status Code", example = "201"
    )
    private String statusCode;
    @Schema(
            description = "Shows Status Message", example = "Created"
    )
    private String statusMsg;
}
