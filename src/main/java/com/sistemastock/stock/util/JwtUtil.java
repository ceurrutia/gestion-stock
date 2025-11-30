package com.sistemastock.stock.util;

import io.github.cdimascio.dotenv.Dotenv;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    private final Key signingKey; // Usamos un objeto Key binario
    private final String SECRET_KEY_STRING;

    //constructor inicializa la clave de forma segura
    public JwtUtil() {
        Dotenv dotenv = Dotenv.load();

        //carga la clave del .env
        String secretKeyString = dotenv.get("JWT_SECRET");

        //asigna el String original
        this.SECRET_KEY_STRING = secretKeyString;

        //convierte la clave String a bytes usando UTF-8.
        byte[] secretBytes = secretKeyString.getBytes(StandardCharsets.UTF_8);

        this.signingKey = Keys.hmacShaKeyFor(secretBytes);
    }

    public String generateToken(UserDetails userDetails) {
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .claim("roles", userDetails.getAuthorities())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                //firma con el objeto Key binario
                .signWith(signingKey, SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractUsername(String token) {
        //uso del parser Builder
        return Jwts.parserBuilder()
                .setSigningKey(signingKey)
                .build()
                .parseClaimsJws(token)
                .getBody().getSubject();
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return username.equals(userDetails.getUsername());
    }
}
