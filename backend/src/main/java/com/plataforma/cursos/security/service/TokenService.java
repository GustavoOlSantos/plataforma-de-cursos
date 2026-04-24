package com.plataforma.cursos.security.service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import javax.crypto.spec.SecretKeySpec;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {

    private final Key key;

    public TokenService(@Value("${jwt.secret}") String secret) {
        this.key = new SecretKeySpec(secret.getBytes(), "HmacSHA384");
    }

    public String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1h
                .signWith(key, SignatureAlgorithm.HS384)
                .compact();
    }

    public String getEmail(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean isValid(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}