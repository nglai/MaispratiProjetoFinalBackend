package com.br.AcademiaPratiRepository;

import com.br.maisAcademiaPratiModel.Exercicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExercicioRepository extends JpaRepository<Exercicio, Long> {
}

