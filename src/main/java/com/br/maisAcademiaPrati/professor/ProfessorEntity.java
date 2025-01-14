package com.br.maisAcademiaPrati.professor;

import com.br.maisAcademiaPrati.pessoa.PessoaEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "professor")
public class ProfessorEntity extends PessoaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID professor_id;

    private String especialidade;
}