package by.ustsinovich.fcadhack.service;

import by.ustsinovich.fcadhack.entity.User;

/**
 * Interface for security services.
 */
public interface SecurityService {

    /**
     * Checks if the current user is the same as the provided user.
     *
     * @param principal current user
     * @param id        user ID
     * @return true if users are the same, false otherwise
     */
    boolean isSameUser(User principal, Long id);

}