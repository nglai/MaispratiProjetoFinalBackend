package com.br.maisAcademiaPrati.aluno;

import com.br.maisAcademiaPrati.enums.Plano;
import com.br.maisAcademiaPrati.enums.Role;
import com.br.maisAcademiaPrati.pessoa.PessoaEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "aluno")
public class AlunoEntity extends PessoaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID aluno_id;

    private String profissao;

    private String enfermidades;

    @Enumerated(EnumType.STRING)
    private Plano plano;

    private double altura;

    private boolean ativo;

    private Date data_pagamento;

    private Date data_vencimento;

    private String ultimo_exercicio;
}
