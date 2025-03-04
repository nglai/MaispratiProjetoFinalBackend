package com.br.maisAcademiaPrati.funcionario;

import com.br.maisAcademiaPrati.endereco.EnderecoDTO;
import com.br.maisAcademiaPrati.enums.Role;
import java.util.Date;
import java.time.LocalTime;
import java.math.BigDecimal;

public record FuncionarioDTO(
        String nome,
        String email,
        String documento,
        Date data_nascimento,
        String senha,
        Date data_cadastro,
        Role role,
        byte[] imagem_perfil,
        boolean ativo,
        String carteira_trabalho,
        BigDecimal salario,
        LocalTime hora_entrada,
        LocalTime hora_saida,
        LocalTime hora_extra,
        EnderecoDTO endereco
){}
