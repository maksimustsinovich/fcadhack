package by.ustsinovich.fcadhack.service;

import by.ustsinovich.fcadhack.dto.request.LoginRequest;
import by.ustsinovich.fcadhack.dto.request.RefreshRequest;
import by.ustsinovich.fcadhack.dto.request.RegisterRequest;
import by.ustsinovich.fcadhack.dto.response.AuthResponse;
import by.ustsinovich.fcadhack.entity.User;
import jakarta.validation.Valid;

/**
 * Interface for authentication and authorization services.
 */
public interface AuthService {

    /**
     * Registers a new user.
     *
     * @param registerRequest registration request
     * @return registered user
     */
    User register(@Valid RegisterRequest registerRequest);

    /**
     * Logs in a user.
     *
     * @param loginRequest login request
     * @return authentication response
     */
    AuthResponse login(@Valid LoginRequest loginRequest);

    /**
     * Refreshes an access token.
     *
     * @param refreshRequest refresh request
     * @return authentication response
     */
    AuthResponse refresh(@Valid RefreshRequest refreshRequest);

}