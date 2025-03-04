package com.br.maisAcademiaPrati.alunoExercicio;

import java.util.UUID;

public record AlunoExercicioResponseDTO(
        UUID idAluno,
        UUID idExercicio,
        Integer repeticoes,
        Integer series,
        String tipo_exercicio
){
}
