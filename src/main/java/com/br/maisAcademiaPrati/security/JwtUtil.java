package com.br.maisAcademiaPrati.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.function.Function;

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

//    public String generateRefreshToken(String username) {
//        return generateToken(username);
//    }

    public String getEmailFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJwt(token)
                .getBody()
                .getSubject();
    }

    public String extractEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    // Extrai uma claim específica de um token usando uma função.
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token); // Obtém todas as claims do token.
        return claimsResolver.apply(claims); // Aplica a função fornecida para extrair a claim desejada.
    }

    // Extrai todas as claims de um token.
    private Claims extractAllClaims(String token) {
        return Jwts.parser()
//                .setSigningKey(rsaKeyProvider.getPublicKey()) // Configura o segredo usado para validar o token.
                .setSigningKey(secret)
                .parseClaimsJws(token) // Analisa o token e retorna suas informações.
                .getBody(); // Obtém o corpo do token (as claims).
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        try {
            final String userEmail = extractEmail(token);
            return (userEmail.equals(userDetails.getUsername()) && !isTokenExpired(token));
        } catch (Exception error) {
            return false;
        }
    }
}
