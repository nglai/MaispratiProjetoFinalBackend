package com.br.maisAcademiaPrati.refreshToken;

import com.br.maisAcademiaPrati.aluno.AlunoEntity;
import com.br.maisAcademiaPrati.endereco.EnderecoEntity;
import com.br.maisAcademiaPrati.funcionario.FuncionarioEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "refresh_token")
public class RefreshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id_refresh_token;

    @Column(nullable = false, unique = true)
    private String token;

    @Column(nullable = false)
    private Instant expiryDate;

    @ManyToOne
    @JoinColumn(name = "fk_id_aluno")
    private AlunoEntity aluno;

    @ManyToOne
    @JoinColumn(name = "fk_id_funcionario")
    private FuncionarioEntity funcionario;

    public RefreshToken(UUID id_refresh_token, Instant expiryDate, FuncionarioEntity funcionario) {
        this.id_refresh_token = id_refresh_token;
        this.expiryDate = expiryDate;
        this.funcionario = funcionario;
    }

    public RefreshToken(UUID id_refresh_token, Instant expiryDate, AlunoEntity aluno) {
        this.id_refresh_token = id_refresh_token;
        this.expiryDate = expiryDate;
        this.aluno = aluno;
    }

    public RefreshToken(UUID id_refresh_token, String token, Instant expiryDate, AlunoEntity aluno) {
        this.id_refresh_token = id_refresh_token;
        this.token = token;
        this.expiryDate = expiryDate;
        this.aluno = aluno;
    }
}
