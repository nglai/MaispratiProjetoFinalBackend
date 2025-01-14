package com.br.maisAcademiaPrati.recepcionista;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RecepcionistaRepository extends JpaRepository<RecepcionistaEntity, UUID> {
}
