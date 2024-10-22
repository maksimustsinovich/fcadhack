package by.ustsinovich.fcadhack.service.impl;

import by.ustsinovich.fcadhack.entity.User;
import by.ustsinovich.fcadhack.service.JwtService;
import by.ustsinovich.fcadhack.service.TokenService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class JwtServiceImpl implements JwtService {

    private final TokenService tokenService;

    @Value("${security.jwt.secret-key}")
    private String secretKey;

    @Value("${security.jwt.access-expiration-time}")
    private long accessExpirationTime;

    @Value("${security.jwt.refresh-expiration-time}")
    private long refreshExpirationTime;

    @Override
    public String generateAccessToken(User user) {
        return generateToken(user, accessExpirationTime);
    }

    @Override
    public String generateRefreshToken(User user) {
        return generateToken(user, refreshExpirationTime);
    }

    @Override
    public boolean isValidRefreshToken(String token, User user) {
        String email = extractEmail(token);

        boolean isValid = tokenService.isValidRefreshToken(token);

        return email.equals(user.getEmail()) && isValid && isTokenNotExpired(token);
    }

    @Override
    public String extractEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    @Override
    public boolean isValid(String token, UserDetails userDetails) {
        String email = extractEmail(token);

        boolean isValid = tokenService.isValidAccessToken(token);

        return email.equals(userDetails.getUsername()) && isValid && isTokenNotExpired(token);
    }

    private boolean isTokenNotExpired(String token) {
        return !extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        Claims claims = extractAllClaims(token);

        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private String generateToken(User user, long expirationTime) {
        return Jwts
                .builder()
                .subject(user.getUsername())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(getSecretKey())
                .compact();
    }

    private SecretKey getSecretKey() {
        byte[] keyBytes = Decoders.BASE64URL.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
