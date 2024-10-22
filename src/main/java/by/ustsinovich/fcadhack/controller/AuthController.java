package by.ustsinovich.fcadhack.controller;

import by.ustsinovich.fcadhack.dto.UserDto;
import by.ustsinovich.fcadhack.dto.request.LoginRequest;
import by.ustsinovich.fcadhack.dto.request.RefreshRequest;
import by.ustsinovich.fcadhack.dto.request.RegisterRequest;
import by.ustsinovich.fcadhack.dto.response.AuthResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Tag(name = "Authentication API")
public interface AuthController {

    /**
     * Register a new user
     *
     * @param registerRequest The registration request
     * @return The registered user
     */
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Register a new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User registered successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request")
    })
    UserDto register(@Valid @RequestBody RegisterRequest registerRequest);

    /**
     * Login a user
     *
     * @param loginRequest The login request
     * @return The authentication response
     */
    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Login a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User logged in successfully"),
            @ApiResponse(responseCode = "401", description = "Invalid credentials")
    })
    AuthResponse login(@Valid @RequestBody LoginRequest loginRequest);

    /**
     * Refresh the authentication token
     *
     * @param refreshRequest The refresh request
     * @return The authentication response
     */
    @PostMapping("/refresh")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Refresh the authentication token")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Token refreshed successfully"),
            @ApiResponse(responseCode = "401", description = "Invalid token")
    })
    @SecurityRequirement(name = "JWT")
    AuthResponse refresh(@Valid @RequestBody RefreshRequest refreshRequest);

}