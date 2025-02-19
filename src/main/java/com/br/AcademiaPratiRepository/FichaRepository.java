package com.br.maisAcademiaPratiRepository;

import com.br.maisAcademiaPratiModel.Ficha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FichaRepository extends JpaRepository<Ficha, Long> {
}
