package com.br.maisAcademiaPrati.refreshToken;

import com.br.maisAcademiaPrati.aluno.AlunoEntity;
import com.br.maisAcademiaPrati.funcionario.FuncionarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, UUID> {

    Optional<RefreshToken> findByToken(String token);

    Optional<RefreshToken> findByAluno(AlunoEntity aluno);
    void deleteByAluno(AlunoEntity aluno);

    void deleteByFuncionario(FuncionarioEntity funcionario);
}
