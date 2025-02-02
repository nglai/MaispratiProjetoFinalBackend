package com.br.maisAcademiaPrati.alunoExercicio;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Embeddable
public class AlunoExercicioId {

    private UUID id_aluno;

    private UUID id_exercicio;
}
