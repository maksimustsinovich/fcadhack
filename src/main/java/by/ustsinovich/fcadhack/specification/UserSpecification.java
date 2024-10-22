package by.ustsinovich.fcadhack.specification;

import by.ustsinovich.fcadhack.entity.User;
import by.ustsinovich.fcadhack.filter.UserFilter;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class UserSpecification {

    private UserSpecification() {
        throw new IllegalStateException("Utility class");
    }

    public static Specification<User> filterBy(UserFilter filter) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            // Filter by email
            if (filter.getEmail() != null && !filter.getEmail().isEmpty()) {
                predicates.add(criteriaBuilder.like(root.get("email"), "%" + filter.getEmail() + "%"));
            }

            // Filter by first name
            if (filter.getFirstName() != null && !filter.getFirstName().isEmpty()) {
                predicates.add(criteriaBuilder.like(root.get("firstName"), "%" + filter.getFirstName() + "%"));
            }

            // Filter by patronymic
            if (filter.getPatronymic() != null && !filter.getPatronymic().isEmpty()) {
                predicates.add(criteriaBuilder.like(root.get("patronymic"), "%" + filter.getPatronymic() + "%"));
            }

            // Filter by last name
            if (filter.getLastName() != null && !filter.getLastName().isEmpty()) {
                predicates.add(criteriaBuilder.like(root.get("lastName"), "%" + filter.getLastName() + "%"));
            }

            // Filter by role
            if (filter.getRole() != null) {
                predicates.add(criteriaBuilder.equal(root.get("role"), filter.getRole()));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

}
