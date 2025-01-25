package com.br.maisAcademiaPrati.medida;

import com.br.maisAcademiaPrati.aluno.AlunoEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "medida")
public class MedidaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id_medida;

    @ManyToOne
    @JoinColumn(name = "fk_id_aluno")
    private AlunoEntity aluno;

    private Date data_medida;
    private Double peso;
    private Double biceps;
    private Double abdomen;
    private Double gluteo;
    private Double coxa;
    private Double panturrilha;
}
