package com.br.maisAcademiaPrati.funcionario;

import com.br.maisAcademiaPrati.pessoa.PessoaEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;
import java.time.LocalTime;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "funcionario")
public class FuncionarioEntity extends PessoaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID funcionario_id;

    private boolean ativo;

    private String carteira_trabalho;

    private BigDecimal salario;

    private LocalTime hora_entrada;

    private LocalTime hora_saida;

    private LocalTime hora_extra;
}