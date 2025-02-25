package com.br.maisAcademiaPrati.refreshToken;

import com.br.maisAcademiaPrati.aluno.AlunoEntity;
import com.br.maisAcademiaPrati.aluno.AlunoRepository;
import com.br.maisAcademiaPrati.funcionario.FuncionarioEntity;
import com.br.maisAcademiaPrati.funcionario.FuncionarioRepository;
import com.br.maisAcademiaPrati.security.JwtUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
public class RefreshTokenService {
    // Define a validade do refresh token: 7 dias (em segundos).
    private final long REFRESH_TOKEN_EXPIRATION = 7 * 24 * 60 * 60;

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public RefreshToken createRefreshToken(String email) {
        AlunoEntity aluno = alunoRepository.findByEmail(email).orElse(null);

        FuncionarioEntity funcionario = funcionarioRepository.findByEmail(email).orElse(null);

        if(aluno == null && funcionario == null) {
            throw new IllegalArgumentException("Usuário não encontrado");
        }

        // Remove qualquer refresh token antigo associado ao usuário antes de gerar um novo.
        if(aluno != null){
            refreshTokenRepository.deleteByAluno(aluno);

            RefreshToken refreshToken = new RefreshToken(
                    UUID.randomUUID(),
                    Instant.now().plusSeconds(REFRESH_TOKEN_EXPIRATION),
                    aluno
            );
            return refreshTokenRepository.save(refreshToken);
        }

        refreshTokenRepository.deleteByFuncionario(funcionario);
        RefreshToken refreshToken = new RefreshToken(
                UUID.randomUUID(),
                Instant.now().plusSeconds(REFRESH_TOKEN_EXPIRATION),
                funcionario
        );
        return refreshTokenRepository.save(refreshToken);
    }
}
