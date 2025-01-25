package com.br.maisAcademiaPrati.alunoExercicio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlunoExercicioRepository extends JpaRepository<AlunoExercicio, AlunoExercicioId> {
}
