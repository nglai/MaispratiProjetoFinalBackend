package com.br.maisAcademiaPrati.professor;

import com.br.maisAcademiaPrati.pessoa.PessoaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProfessorRepository extends JpaRepository<ProfessorEntity, UUID> {
}
