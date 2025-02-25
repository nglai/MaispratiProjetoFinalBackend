package com.br.maisAcademiaPrati.refreshToken;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "refresh_token")
public class RefreshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id_refresh_token;

    @Column(nullable = false, unique = true)
    private String token;

    // Utilizamos o username para associar o refresh token ao usuário.
    @Column(nullable = false)
    private String username;

    // Data de expiração do token
    @Column(nullable = false)
    private Instant expiryDate;

    // Construtores, getters e setters

    public RefreshToken() {}

    public UUID getId_refresh_token() {
        return id_refresh_token;
    }

    public void setId_refresh_token(UUID id_refresh_token) {
        this.id_refresh_token = id_refresh_token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Instant getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Instant expiryDate) {
        this.expiryDate = expiryDate;
    }
}
