package by.ustsinovich.fcadhack.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RefreshRequest {

    @NotBlank(message = "Refresh token is required")
    @Schema(name = "Refresh token")
    private String refreshToken;

}
