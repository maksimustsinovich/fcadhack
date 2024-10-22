package by.ustsinovich.fcadhack.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

/**
 * Enum for user roles.
 */
@Getter
@RequiredArgsConstructor
public enum UserRole implements GrantedAuthority {

    /**
     * Admin role.
     */
    ROLE_ADMIN("ROLE_ADMIN"),

    /**
     * User role.
     */
    ROLE_USER("ROLE_USER");

    private final String authority;

}
