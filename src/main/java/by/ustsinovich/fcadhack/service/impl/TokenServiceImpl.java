package by.ustsinovich.fcadhack.service.impl;

import by.ustsinovich.fcadhack.entity.Token;
import by.ustsinovich.fcadhack.entity.User;
import by.ustsinovich.fcadhack.exception.InvalidJwtTokenException;
import by.ustsinovich.fcadhack.repository.TokenRepository;
import by.ustsinovich.fcadhack.service.TokenService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {

    private final TokenRepository tokenRepository;

    @Override
    @Transactional
    public void saveToken(String accessToken, String refreshToken, User user) {
        Token token = Token
                .builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .isLoggedOut(false)
                .user(user)
                .build();

        tokenRepository.save(token);
    }

    @Override
    @Transactional
    public void revokeTokens(User user) {
        List<Token> validTokens = tokenRepository.findAllAccessTokensByUser(user);

        if (!validTokens.isEmpty()) {
            validTokens.forEach(token -> token.setLoggedOut(true));

            tokenRepository.saveAll(validTokens);
        }
    }

    @Override
    @Transactional
    public void logout(String token) {
        Optional<Token> optionalToken = tokenRepository
                .findByAccessToken(token);

        if (optionalToken.isEmpty()) {
            throw new InvalidJwtTokenException();
        }

        Token stored = optionalToken.get();

        stored.setLoggedOut(true);
        tokenRepository.save(stored);
    }

    @Override
    public boolean isValidRefreshToken(String token) {
        return tokenRepository
                .findByRefreshToken(token)
                .map(stored -> !stored.isLoggedOut())
                .orElse(false);
    }

    @Override
    public boolean isValidAccessToken(String token) {
        return tokenRepository
                .findByAccessToken(token)
                .map(stored -> !stored.isLoggedOut())
                .orElse(false);
    }

}
