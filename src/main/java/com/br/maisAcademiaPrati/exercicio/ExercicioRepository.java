package com.br.maisAcademiaPrati.exercicio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ExercicioRepository extends JpaRepository<ExercicioEntity, UUID> {
}
