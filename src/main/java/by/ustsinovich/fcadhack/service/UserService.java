package by.ustsinovich.fcadhack.service;

import by.ustsinovich.fcadhack.dto.UserDto;
import by.ustsinovich.fcadhack.dto.request.RegisterRequest;
import by.ustsinovich.fcadhack.entity.User;
import by.ustsinovich.fcadhack.enums.UserSort;
import by.ustsinovich.fcadhack.filter.UserFilter;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;

/**
 * Interface for user services.
 */
public interface UserService {

    /**
     * Creates a new user.
     *
     * @param registerRequest registration request
     * @return created user
     */
    User createUser(@Valid RegisterRequest registerRequest);

    /**
     * Gets all users.
     *
     * @param page     page number
     * @param size    page size
     * @param sort    user sort
     * @param filter  user filter
     * @return page of users
     */
    Page<User> getAllUsers(
            Integer page,
            Integer size,
            UserSort sort,
            UserFilter filter
    );

    /**
     * Gets a user by ID.
     *
     * @param id user ID
     * @return user
     */
    User getUserById(Long id);

    /**
     * Deletes a user.
     *
     * @param id user ID
     */
    void deleteUserById(Long id);

    /**
     * Updates a user.
     *
     * @param id      user ID
     * @param userDto user data
     * @return updated user
     */
    User updateUser(Long id, @Valid UserDto userDto);


    /**
     * Gets a user by email.
     *
     * @param email user email
     * @return user
     */
    User getUserByEmail(String email);

}
