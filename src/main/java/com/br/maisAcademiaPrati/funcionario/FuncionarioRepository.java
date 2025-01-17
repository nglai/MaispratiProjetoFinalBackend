package com.br.maisAcademiaPrati.funcionario    ;

import com.br.maisAcademiaPrati.pessoa.PessoaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FuncionarioRepository extends JpaRepository<FuncionarioEntity, UUID> {
}