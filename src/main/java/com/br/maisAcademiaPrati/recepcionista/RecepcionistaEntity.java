package com.br.maisAcademiaPrati.recepcionista;

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
@Table(name = "recepcionista")
public class RecepcionistaEntity extends PessoaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID recepcionista_id;

    private String setor;
}