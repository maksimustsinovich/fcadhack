package by.ustsinovich.fcadhack.filter;

import by.ustsinovich.fcadhack.enums.UserRole;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Filter for users")
public class UserFilter {

    @Schema(description = "User email")
    private String email;

    @Schema(description = "User first name")
    private String firstName;

    @Schema(description = "User patronymic")
    private String patronymic;

    @Schema(description = "User last name")
    private String lastName;

    @Schema(description = "User role")
    private UserRole role;

}