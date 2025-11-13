package com.gora.contractmanagerapi.infra.authorization.component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Date;

@Component
public class JWTTokenProvider {

    private final SecretKey secretKey;
    private final long expirationMs;

    public JWTTokenProvider(@Value("${jwt.secret}")String secretKey,
                            @Value("${jwt.expiration-ms}")long expirationMs) {
        this.secretKey =  Keys.hmacShaKeyFor(secretKey.getBytes());
        this.expirationMs = expirationMs;
    }

    public String generateToken(UserDetails userDetails){
        Instant now = Instant.now();
        Instant expiry = now.plusMillis(expirationMs);

        return Jwts
                .builder()
                .setSubject(userDetails.getUsername())
                .claim("userId", ((CustomUserDetails) userDetails).getUserId())
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(expiry))
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean validateToken(String token){
        try {
                Jwts
                    .parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException ex) {
            return false;
        }
    }

    public Claims getClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token).getBody();
    }
}
