package com.br.maisAcademiaPrati.security;

import com.br.maisAcademiaPrati.util.RsaKeyProvider;
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

    @Autowired
    private RsaKeyProvider rsaKeyProvider;

    public String generateToken(String email) {
        var token = Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(rsaKeyProvider.getPrivateKey(), SignatureAlgorithm.RS256)
                .compact();
        System.out.println(token);
        return token;
    }

    public String generateRefreshToken(String username) {
        return generateToken(username);
    }

    public String getEmailFromToken(String token) {
        try {
            if (!token.matches("^[A-Za-z0-9-_\\.]+\\.[A-Za-z0-9-_\\.]+\\.[A-Za-z0-9-_\\.]+$")) {
                System.out.println("Token JWT malformatado: " + token);
                throw new IllegalArgumentException("Token JWT malformatado.");
            }

            return extractClaim(token, Claims::getSubject); // Extrai o "subject" (nome do usuário) do token.
        } catch (Exception e) {
            System.out.println("Erro ao extrair o username do token: " + e.getMessage());
            throw e; // Propaga a exceção após registrar o erro.
        }
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
                .setSigningKey(rsaKeyProvider.getPublicKey()) // Configura a chave pública usada para validar o token.
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
