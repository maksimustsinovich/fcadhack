package by.ustsinovich.fcadhack.repository;

import by.ustsinovich.fcadhack.entity.Token;
import by.ustsinovich.fcadhack.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Long> {

    List<Token> findAllAccessTokensByUser(User user);

    Optional<Token> findByAccessToken(String token);

    Optional<Token> findByRefreshToken(String token);

}
