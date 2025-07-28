package com.pet.management.auth;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.ejb.Singleton;

import java.util.Date;

import static io.jsonwebtoken.SignatureAlgorithm.HS256;

@Singleton
public class JwtUtil {
    private static final String SECRET = System.getProperty("app.jwt.secret");
    private static final Integer SESSION_TIMEOUT = Integer.parseInt(System.getProperty("app.session.timeout.seconds"));

    public static String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + (SESSION_TIMEOUT * 1000)))
                .signWith(Keys.hmacShaKeyFor(SECRET.getBytes()), HS256)
                .compact();
    }

    public static String validateToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET.getBytes())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
