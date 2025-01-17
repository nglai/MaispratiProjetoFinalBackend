package com.br.maisAcademiaPrati.funcionario;

import com.br.maisAcademiaPrati.enums.Role;

import java.util.Date;

import java.time.LocalTime;

import java.math.BigDecimal;

public record FuncionarioDTO(
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
        boolean ativo,
        String carteira_trabalho,
        BigDecimal salario,
        LocalTime hora_entrada,
        LocalTime hora_saida,
        LocalTime hora_extra

){}