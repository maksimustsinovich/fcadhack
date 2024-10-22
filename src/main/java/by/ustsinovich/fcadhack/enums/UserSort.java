package by.ustsinovich.fcadhack.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;

/**
 * Enum for sorting users.
 */
@Getter
@RequiredArgsConstructor
public enum UserSort {

    /**
     * Sort by ID in ascending order.
     */
    ID_ASC(Sort.by(Sort.Direction.ASC, "id")),

    /**
     * Sort by ID in descending order.
     */
    ID_DESC(Sort.by(Sort.Direction.DESC, "id")),

    /**
     * Sort by creation date in ascending order.
     */
    CREATED_ASC(Sort.by(Sort.Direction.ASC, "createdAt")),

    /**
     * Sort by creation date in descending order.
     */
    CREATED_DESC(Sort.by(Sort.Direction.DESC, "createdAt")),

    /**
     * Sort by update date in ascending order.
     */
    UPDATED_ASC(Sort.by(Sort.Direction.ASC, "updatedAt")),

    /**
     * Sort by update date in descending order.
     */
    UPDATED_DESC(Sort.by(Sort.Direction.DESC, "updatedAt")),

    /**
     * Sort by email in ascending order.
     */
    EMAIL_ASC(Sort.by(Sort.Direction.ASC, "email")),

    /**
     * Sort by email in descending order.
     */
    EMAIL_DESC(Sort.by(Sort.Direction.DESC, "email")),

    /**
     * Sort by first name in ascending order.
     */
    FIRST_NAME_ASC(Sort.by(Sort.Direction.ASC, "firstName")),

    /**
     * Sort by first name in descending order.
     */
    FIRST_NAME_DESC(Sort.by(Sort.Direction.DESC, "firstName")),

    /**
     * Sort by last name in ascending order.
     */
    LAST_NAME_ASC(Sort.by(Sort.Direction.ASC, "lastName")),

    /**
     * Sort by last name in descending order.
     */
    LAST_NAME_DESC(Sort.by(Sort.Direction.DESC, "lastName")),

    /**
     * Sort by patronymic in ascending order.
     */
    PATRONYMIC_ASC(Sort.by(Sort.Direction.ASC, "patronymic")),

    /**
     * Sort by patronymic in descending order.
     */
    PATRONYMIC_DESC(Sort.by(Sort.Direction.DESC, "patronymic"));

    private final Sort sort;

}