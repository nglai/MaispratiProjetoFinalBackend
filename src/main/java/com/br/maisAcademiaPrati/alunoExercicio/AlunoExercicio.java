package com.br.maisAcademiaPrati.alunoExercicio;

import com.br.maisAcademiaPrati.aluno.AlunoEntity;
import com.br.maisAcademiaPrati.exercicio.ExercicioEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "aluno_exercicio")
public class AlunoExercicio {

    @EmbeddedId
    private AlunoExercicioId id_aluno_exercicio;

    @ManyToOne
    @MapsId("id_aluno")
    @JoinColumn(name = "id_aluno")
    private AlunoEntity aluno;

    @ManyToOne
    @MapsId("id_exercicio")
    @JoinColumn(name = "id_exercicio")
    private ExercicioEntity exercicio;

    private Integer repeticoes;

    private Integer series;

    private String tipo_exercicio;
}
