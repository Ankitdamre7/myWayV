package com.example.demo.config;

import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

    // 🔐 Secret key (keep safe in real apps)
    private final String SECRET = "mysecretkeymysecretkeymysecretkey123";

    private final Key key = Keys.hmacShaKeyFor(SECRET.getBytes());

    // ⏱ Token validity (1 hour)
    private final long EXPIRATION_TIME = 1000 * 60 * 60;

    // ✅ Generate Token
    public String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email) // store email
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    // ✅ Extract Email from Token
    public String extractEmail(String token) {
        return getClaims(token).getSubject();
    }

    // ✅ Validate Token
    public boolean validateToken(String token, String email) {
        final String extractedEmail = extractEmail(token);
        return (extractedEmail.equals(email) && !isTokenExpired(token));
    }

    // 🔍 Check Expiry
    private boolean isTokenExpired(String token) {
        return getClaims(token).getExpiration().before(new Date());
    }

    // 🔍 Get Claims
    private Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}


