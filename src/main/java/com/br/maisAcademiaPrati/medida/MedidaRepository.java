package com.br.maisAcademiaPrati.medida;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MedidaRepository extends JpaRepository<MedidaEntity, UUID> {

}
