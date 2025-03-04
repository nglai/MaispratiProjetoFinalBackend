package com.br.maisAcademiaPrati.funcionario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/funcionario")
public class FuncionarioController {

    @Autowired
    FuncionarioService funcionarioService;

    @PostMapping
    public ResponseEntity<?> criaFuncionario(@RequestBody FuncionarioDTO data) {
        try {
            if (funcionarioService.buscaFuncionarioPorEmail(data.email()) != null) {
                throw new IllegalArgumentException("Erro: Email já cadastrado.");
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(funcionarioService.criarFuncionario(data));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<FuncionarioEntity>> listaTodosFuncionarios() {
        return ResponseEntity.status(HttpStatus.OK).body(funcionarioService.listaTodosFuncionarios());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FuncionarioEntity> buscaFuncionarioPorId(@PathVariable("id") UUID id) {
        Optional<FuncionarioEntity> funcionario = funcionarioService.buscaFuncionarioPorId(id);
        return funcionario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizaFuncionarioPorId(@PathVariable("id") UUID id, @RequestBody FuncionarioDTO funcionarioDTO) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(funcionarioService.atualizaFuncionarioPorId(id, funcionarioDTO));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
