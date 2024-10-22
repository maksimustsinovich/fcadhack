package by.ustsinovich.fcadhack.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegisterRequest {

    @NotBlank(message = "Password is required")
    @Size(min = 8, max = 255, message = "Password size must be between 8 and 255 chars")
    @Schema(description = "Password")
    private String password;

    @NotBlank(message = "First name is required")
    @Size(max = 255, message = "First name not exceed 255 chars")
    @Schema(description = "First name of the user")
    private String firstName;

    @NotBlank(message = "Patronymic is required")
    @Size(max = 255, message = "Patronymic not exceed 255 chars")
    @Schema(description = "Patronymic of the user")
    private String patronymic;

    @NotBlank(message = "Last name is required")
    @Size(max = 255, message = "Last name not exceed 255 chars")
    @Schema(description = "Last name of the user")
    private String lastName;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email")
    @Schema(description = "Email address of the user")
    private String email;

}