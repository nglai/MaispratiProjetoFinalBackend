package com.br.maisAcademiaPrati.medida;

import java.time.LocalDate;
import java.util.UUID;

public record MedidaDTO(
        Double peso,
        Double altura,
        LocalDate dataMedida,
        Double biceps,
        Double triceps,
        Double abdomen,
        Double gluteo,
        Double coxa,
        Double panturrilha,
        UUID alunoId // ID do aluno associado
) {}