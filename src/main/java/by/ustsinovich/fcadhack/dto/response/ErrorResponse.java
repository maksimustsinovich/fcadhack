package by.ustsinovich.fcadhack.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResponse {

    @Schema(description = "Error message")
    private String message;

    @Schema(description = "HTTP status code")
    private int statusCode;

}