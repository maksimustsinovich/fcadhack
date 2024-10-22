package by.ustsinovich.fcadhack.service;

import by.ustsinovich.fcadhack.entity.User;

/**
 * Interface for token services.
 */
public interface TokenService {

    /**
     * Saves a token.
     *
     * @param accessToken  access token
     * @param refreshToken refresh token
     * @param user         user
     */
    void saveToken(String accessToken, String refreshToken, User user);

    /**
     * Revokes tokens for a user.
     *
     * @param user user
     */
    void revokeTokens(User user);

    /**
     * Logs out a user.
     *
     * @param token token
     */
    void logout(String token);

    /**
     * Checks if a refresh token is valid.
     *
     * @param token token
     * @return true if token is valid, false otherwise
     */
    boolean isValidRefreshToken(String token);

    /**
     * Checks if an access token is valid.
     *
     * @param token token
     * @return true if token is valid, false otherwise
     */
    boolean isValidAccessToken(String token);

}