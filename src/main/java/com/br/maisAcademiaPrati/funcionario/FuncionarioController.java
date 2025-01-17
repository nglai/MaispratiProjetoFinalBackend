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
    public ResponseEntity<FuncionarioEntity> criaFuncionario(@RequestBody FuncionarioDTO data) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.funcionarioService.criarFuncionario(data));
    }

    @GetMapping
    public ResponseEntity<List<FuncionarioEntity>> listaTodosFuncionarios() {
        return ResponseEntity.status(HttpStatus.OK).body(this.funcionarioService.listaTodosFuncionarios());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FuncionarioEntity> buscaFuncionarioPorId(@PathVariable("id") UUID id) {
        Optional<FuncionarioEntity> funcionario = funcionarioService.buscaFuncionarioPorId(id);
        return funcionario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<FuncionarioEntity> atualizaFuncionarioPorId(@PathVariable("id") UUID id, @RequestBody FuncionarioDTO funcionarioDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(this.funcionarioService.atualizaFuncionarioPorId(id, funcionarioDTO));
    }
}