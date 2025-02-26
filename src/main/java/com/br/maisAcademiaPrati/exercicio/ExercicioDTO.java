package com.br.maisAcademiaPrati.exercicio;

import com.br.maisAcademiaPrati.enums.Dificuldade;
import com.br.maisAcademiaPrati.enums.GrupoMuscular;

public record ExercicioDTO(
        String nome,
        GrupoMuscular grupo_muscular,
        Dificuldade dificuldade,
        String link_video
){
}
