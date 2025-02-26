package com.br.maisAcademiaPrati.medida;

import com.br.maisAcademiaPrati.aluno.AlunoEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_id_aluno", nullable = false)
    private AlunoEntity aluno;

    private Date data_medida;
    private Double peso;
    private Double biceps;
    private Double triceps;
    private Double abdomen;
    private Double gluteo;
    private Double coxa;
    private Double panturrilha;
}
