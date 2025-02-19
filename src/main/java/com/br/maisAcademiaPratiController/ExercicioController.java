package com.br.maisAcademiaPratiController;

import com.br.maisAcademiaPratiModel.Exercicio;
import com.br.maisAcademiaPratiService.ExercicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exercicios")
public class ExercicioController {

    @Autowired
    private ExercicioService exercicioService;

    @GetMapping
    public ResponseEntity<List<Exercicio>> listarTodos() {
        return ResponseEntity.ok(exercicioService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Exercicio> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(exercicioService.buscarPorId(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Exercicio salvar(@RequestBody Exercicio exercicio) {
        return exercicioService.salvar(exercicio);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Exercicio> atualizar(@PathVariable Long id, @RequestBody Exercicio exercicio) {
        return ResponseEntity.ok(exercicioService.atualizar(id, exercicio));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        exercicioService.deletar(id);
    }
}

