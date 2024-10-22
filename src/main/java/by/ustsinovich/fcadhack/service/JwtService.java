package by.ustsinovich.fcadhack.service;


import by.ustsinovich.fcadhack.entity.User;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Interface for JWT token services.
 */
public interface JwtService {

    /**
     * Generates an access token.
     *
     * @param user user
     * @return access token
     */
    String generateAccessToken(User user);

    /**
     * Generates a refresh token.
     *
     * @param user user
     * @return refresh token
     */
    String generateRefreshToken(User user);

    /**
     * Checks if a refresh token is valid.
     *
     * @param token  token
     * @param user   user
     * @return true if token is valid, false otherwise
     */
    boolean isValidRefreshToken(String token, User user);

    /**
     * Extracts the email from a token.
     *
     * @param token token
     * @return email
     */
    String extractEmail(String token);

    /**
     * Checks if a token is valid.
     *
     * @param token      token
     * @param userDetails user details
     * @return true if token is valid, false otherwise
     */
    boolean isValid(String token, UserDetails userDetails);

}