package com.br.maisAcademiaPrati.exercicio;

import com.br.maisAcademiaPrati.enums.Dificuldade;
import com.br.maisAcademiaPrati.enums.GrupoMuscular;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "exercicio")
public class ExercicioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id_exercicio;

    private String nome;

    @Enumerated(EnumType.STRING)
    private GrupoMuscular grupo_muscular;

    @Enumerated(EnumType.STRING)
    private Dificuldade dificuldade;

    private String link_video;

}
