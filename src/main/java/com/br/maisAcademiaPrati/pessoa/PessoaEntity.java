package com.br.maisAcademiaPrati.pessoa;

import com.br.maisAcademiaPrati.enums.Role;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PessoaEntity {

    private String nome;

    private String email;

    private String documento;

    @Temporal(TemporalType.DATE)
    private Date data_nascimento;

    private String senha;

    @Temporal(TemporalType.DATE)
    private Date data_cadastro;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Lob
    private byte[] imagem_perfil;
}