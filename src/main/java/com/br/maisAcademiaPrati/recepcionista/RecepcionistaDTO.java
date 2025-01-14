package com.br.maisAcademiaPrati.recepcionista;

import com.br.maisAcademiaPrati.enums.Role;

import java.util.Date;

public record RecepcionistaDTO(
        String nome,
        String email,
        String documento,
        Date data_nascimento,
        String senha,
        Date data_cadastro,
        Role role,
        String turno,
        String telefone
) {}
