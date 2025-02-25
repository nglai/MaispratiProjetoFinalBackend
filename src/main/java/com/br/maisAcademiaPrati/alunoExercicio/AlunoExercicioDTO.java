package com.br.maisAcademiaPrati.alunoExercicio;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.util.UUID;

public record AlunoExercicioDTO(
        UUID idAluno,
        UUID idExercicio,
        Integer repeticoes,
        Integer series,
        String tipoExercicio
) {}