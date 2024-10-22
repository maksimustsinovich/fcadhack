package by.ustsinovich.fcadhack.controller;

import by.ustsinovich.fcadhack.dto.UserDto;
import by.ustsinovich.fcadhack.enums.UserSort;
import by.ustsinovich.fcadhack.filter.UserFilter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Tag(name = "User API")
@SecurityRequirement(name = "JWT")
public interface UserController {

    /**
     * Retrieve all users
     *
     * @param page  The page number
     * @param size  The page size
     * @param sort  The sort order
     * @param filter The filter criteria
     * @return The users
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Retrieve all users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Users retrieved successfully")
    })
    @Parameter(name = "number", description = "The page number")
    @Parameter(name = "size", description = "The page size")
    @Parameter(name = "sort", description = "The sort order")
    @Parameter(name = "filter", description = "The filter criteria")
    @SecurityRequirement(name = "JWT")
    Page<UserDto> retrieveAllUsers(
            @RequestParam(required = false, defaultValue = "0") @Min(0) Integer page,
            @RequestParam(required = false, defaultValue = "20") @Min(1) Integer size,
            @RequestParam(required = false, defaultValue = "ID_ASC") UserSort sort,
            UserFilter filter
    );

    /**
     * Retrieve a user by ID
     *
     * @param id The user ID
     * @return The user
     */
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Retrieve a user by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @Parameter(name = "id", description = "The user ID")
    @SecurityRequirement(name = "JWT")
    UserDto retrieveUserById(@PathVariable @Min(1) Long id);

    /**
     * Update a user
     *
     * @param id      The user ID
     * @param userDto The updated user
     * @return The updated user
     */
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Update a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User updated successfully"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @Parameter(name = "id", description = "The user ID")
    @SecurityRequirement(name = "JWT")
    @PreAuthorize("hasRole('ADMIN') || @securityService.isSameUser(authentication.principal, #id)")
    UserDto updateUser(@PathVariable @Min(1) Long id, @Valid @RequestBody UserDto userDto);

    /**
     * Delete a user
     *
     * @param id The user ID
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "User deleted successfully"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @Parameter(name = "id", description = "The user ID")
    @SecurityRequirement(name = "JWT")
    @PreAuthorize("hasRole('ADMIN')")
    void deleteUserById(@PathVariable @Min(1) Long id);

}