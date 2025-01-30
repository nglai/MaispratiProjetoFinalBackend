package com.br.maisAcademiaPrati.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long expirationTime;

    public String generateToken(String email) {
        var token = Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
        System.out.println(token);
        return token;
    }

    public String getEmailFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJwt(token)
                .getBody()
                .getSubject();
    }

    public boolean isTokenValid(String token) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJwt((token));
            return true;
        } catch (Exception error) {
            return false;
        }
    }
}
