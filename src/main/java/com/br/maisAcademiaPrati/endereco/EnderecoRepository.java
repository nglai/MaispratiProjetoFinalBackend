package com.br.maisAcademiaPrati.endereco;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EnderecoRepository extends JpaRepository<EnderecoEntity, UUID> {
}
