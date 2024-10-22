package by.ustsinovich.fcadhack.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "User data transfer object")
public class UserDto {

    @Schema(description = "Unique identifier of the user", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Schema(description = "Date and time when the user was created", accessMode = Schema.AccessMode.READ_ONLY)
    private LocalDateTime createdAt;

    @Schema(description = "Date and time when the user was last updated", accessMode = Schema.AccessMode.READ_ONLY)
    private LocalDateTime updatedAt;

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

    @Schema(description = "Role of the user", accessMode = Schema.AccessMode.READ_ONLY)
    private String role;

}