package com.br.maisAcademiaPrati.aluno;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AlunoRepository extends JpaRepository<AlunoEntity, UUID> {
    Optional<AlunoEntity> findByEmail(String email);
}
