package com.pi.healsync.security;
import org.springframework.stereotype.Component;
import java.util.*;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;

import java.security.Key;

@Component
public class JwtUtil {
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    private Key key;

    @PostConstruct
    public void init() {
        byte[] secretBytes = java.util.Base64.getDecoder().decode(secret);
        this.key = Keys.hmacShaKeyFor(secretBytes);
    }

    public String generateToken(String username, UUID id) {
        return Jwts.builder()
                .setSubject(username)
                .claim("id", id.toString())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public String validateToken(String token) {
        try {
            return Jwts.parserBuilder().setSigningKey(key).build()
                    .parseClaimsJws(token).getBody().getSubject();
        } catch (JwtException e) {
            return null;
        }
    }
}
