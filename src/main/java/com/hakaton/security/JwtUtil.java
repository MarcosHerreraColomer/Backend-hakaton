package com.hakaton.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    // Clave secreta segura (puedes moverla a application.properties si lo necesitas)
    private final Key secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    // Duración del token (1 hora)
    private final long EXPIRATION_TIME = 3600000;

    // 🔐 Generar token con el DNI como subject
    public String generateToken(String dni) {
        return Jwts.builder()
                .setSubject(dni) // Aquí guardamos el DNI
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(secretKey)
                .compact();
    }

    // ✅ Validar que el token no esté expirado ni corrupto
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // 📤 Obtener el DNI desde el token (el subject)
    public String getDniFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    // ✅ Alias por si quieres seguir la convención Spring
    public String extractUsername(String token) {
        return getDniFromToken(token);
    }
}
