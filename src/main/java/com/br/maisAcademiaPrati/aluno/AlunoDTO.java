package com.br.maisAcademiaPrati.aluno;

import com.br.maisAcademiaPrati.enums.Role;

import java.util.Date;

public record AlunoDTO(
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
        String profissao,
        String enfermidades,
        String plano,
        double altura,
        boolean ativo,
        Date data_pagamento,
        Date data_vencimento,
        String ultimo_exercicio
){}
