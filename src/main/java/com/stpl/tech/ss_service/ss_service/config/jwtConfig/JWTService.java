package com.stpl.tech.ss_service.ss_service.config.jwtConfig;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class JWTService {

    @Value("${security.jwt.expiration-time}")
    private long expirationTime;

    @Value("${security.jwt.secret}")
    private String secureKey;

    private SecretKey getKey() {
        byte[] bytes = Decoders.BASE64.decode(secureKey);
        return Keys.hmacShaKeyFor(bytes);
    }

    public String generateToken(String username, Integer userId) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", userId);
        return Jwts.builder()
                .claims(claims)
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(getKey())
                .compact();
    }

    public String getUserNameFromToken(String token) {
        Claims claims = getAllClaims(token);
        return claims.getSubject();
    }

    private Claims getAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public Integer getUserIdFromToken(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return (Integer) claims.get("userId");
    }

    public Boolean isTokenValid(String token, String userName) {
        try {
            Claims claims = getAllClaims(token);
            return (
                    claims.getSubject().equals(userName)
                            && claims.getExpiration().after(new Date(System.currentTimeMillis()))
            );
        } catch (MalformedJwtException e) {
            log.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            log.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            log.error("JWT claims string is empty: {}", e.getMessage());
        }
        return false;
    }

}
