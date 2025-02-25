package com.br.maisAcademiaPrati.exercicio;

import com.br.maisAcademiaPrati.enums.Dificuldade;
import com.br.maisAcademiaPrati.enums.GrupoMuscular;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ExercicioDTO(
        String nome,
        GrupoMuscular grupoMuscular,
        Dificuldade dificuldade,
        String linkVideo
) {}
