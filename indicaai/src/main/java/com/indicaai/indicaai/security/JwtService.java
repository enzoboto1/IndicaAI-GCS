package com.indicaai.indicaai.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JwtService {

        // chave longa (>= 32 bytes para HS256)
        private static final String SECRET = "indicaai-secret-key-para-mvp-com-no-minimo-32-bytes";

        private static final long EXPIRATION = 1000 * 60 * 60 * 24; // 24h

        private final SecretKey key = Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));

        public String generateToken(String email) {
                return Jwts.builder()
                                .setSubject(email)
                                .setIssuedAt(new Date())
                                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                                .signWith(key, SignatureAlgorithm.HS256)
                                .compact();
        }

        public String extractEmail(String token) {
                return Jwts.parserBuilder()
                                .setSigningKey(key)
                                .build()
                                .parseClaimsJws(token)
                                .getBody()
                                .getSubject();
        }

}