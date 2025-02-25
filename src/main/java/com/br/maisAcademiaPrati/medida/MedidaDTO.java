package com.br.maisAcademiaPrati.medida;

import java.util.Date;

public record MedidaDTO(
    Date data_medida,
    Double peso,
    Double biceps,
    Double triceps,
    Double abdomen,
    Double gluteo,
    Double coxa,
    Double panturrilha
){
}
