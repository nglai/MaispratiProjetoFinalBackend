package com.br.maisAcademiaPrati.funcionario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/administrador")
public class AdministradorController {

    @Autowired
    FuncionarioService funcionarioService;

    @PostMapping
    public ResponseEntity<?> criaAdm(@RequestBody FuncionarioDTO data) {
        try {
            if (funcionarioService.buscaFuncionarioPorEmail(data.email()) != null) {
                throw new IllegalArgumentException("Erro: Email já cadastrado.");
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(funcionarioService.criarFuncionario(data));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}
