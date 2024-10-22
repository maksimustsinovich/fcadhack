package by.ustsinovich.fcadhack.repository;

import by.ustsinovich.fcadhack.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Page<User> findAll(Specification<User> specification, Pageable pageable);

    Optional<User> findByEmail(String email);

}
