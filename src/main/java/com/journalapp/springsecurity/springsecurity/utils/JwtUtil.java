package com.journalapp.springsecurity.springsecurity.utils;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

// In this class we will use the frequent used methods.
@Component
public class JwtUtil {

    // Take below 32 Bytes Key
    private String SECRET_KEY = "84896d8879b6ba0712a482a6352fa98d5f1b428b53a5511632a12d269dd8e592";

    // SecretKey is a inbuilt class - provided by JWT dependencies
    private SecretKey getSignKey()
    {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }
    public String generateToken(String username) {
        Map<String , Object> claims = new HashMap<>();
        return createToken(claims,username);
    }
        // username = subject OR claims = payload
    private String createToken(Map<String, Object> claims, String username) {
        return Jwts.builder()
                .claims(claims)
                .subject(username)
                .header().empty().add("typ", "JWT")
                .and()
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 3600 * 1000 * 50))
                .signWith(getSignKey())
                .compact();
    }

    // JWT Filter requirement
    private Claims extractClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSignKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String extractUsername(String token)
    {
        return extractClaims(token).getSubject(); // We got the username here
    }

    public Date getExpiration(String token)
    {
        return extractClaims(token).getExpiration();
    }

    public boolean validateToken(String token)
    {
        return !getExpiration(token).before(new Date()); // Validate the token
    }


}
