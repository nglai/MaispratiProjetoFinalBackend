package com.br.maisAcademiaPrati.professor;

import com.br.maisAcademiaPrati.enums.Role;

import java.util.Date;

public record ProfessorDTO(
        String nome,
        String email,
        String documento,
        Date data_nascimento,
        String senha,
        Date data_cadastro,
        Role role,
        String rua,
        String bairro,
        String cep,
        String complemento,
        String especialidade
) {}
