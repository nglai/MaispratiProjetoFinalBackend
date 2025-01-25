package com.br.maisAcademiaPrati.aluno;

import com.br.maisAcademiaPrati.alunoExercicio.AlunoExercicio;
import com.br.maisAcademiaPrati.endereco.EnderecoEntity;
import com.br.maisAcademiaPrati.enums.Plano;
import com.br.maisAcademiaPrati.medida.MedidaEntity;
import com.br.maisAcademiaPrati.pessoa.PessoaEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@JsonIgnoreProperties({"medidas", "exercicios"})
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "aluno")
public class AlunoEntity extends PessoaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id_aluno;

    private String profissao;

    private String enfermidades;

    @Enumerated(EnumType.STRING)
    private Plano plano;

    private Double altura;

    private Boolean ativo;

    private Date data_pagamento;

    private Date data_vencimento;

    private String ultimo_exercicio;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_id_endereco")
    private EnderecoEntity endereco;

    @Transient
    @OneToMany(mappedBy = "aluno")
    private List<MedidaEntity> medidas;

    @Transient
    @OneToMany(mappedBy = "aluno")
    private List<AlunoExercicio> exercicios;
}
