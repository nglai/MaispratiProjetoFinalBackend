package com.br.AcademiaPratiRepository;

import com.br.maisAcademiaPratiModel.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {
    // O JpaRepository já fornece os métodos de CRUD (save, findAll, findById, delete, etc.)
}

