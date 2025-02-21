package com.br.maisAcademiaPrati.exercicio;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/exercicios")
public class ExercicioController {

    private final ExercicioService exercicioService;

    public ExercicioController(ExercicioService exercicioService) {
        this.exercicioService = exercicioService;
    }

    /**
     * Lista todos os exercícios.
     */
    @GetMapping
    public List<ExercicioEntity> listarTodos() {
        return exercicioService.listarTodos();
    }

    /**
     * Busca um exercício pelo ID (UUID).
     */
    @GetMapping("/{id}")
    public ResponseEntity<ExercicioEntity> buscarPorId(@PathVariable UUID id) {
        return exercicioService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Cria um novo exercício.
     */
    @PostMapping
    public ExercicioEntity salvar(@RequestBody ExercicioEntity exercicio) {
        return exercicioService.salvar(exercicio);
    }

    /**
     * Atualiza um exercício existente.
     */
    @PutMapping("/{id}")
    public ResponseEntity<ExercicioEntity> atualizar(@PathVariable UUID id,
                                                     @RequestBody ExercicioEntity exercicioAtualizado) {
        try {
            ExercicioEntity atualizado = exercicioService.atualizar(id, exercicioAtualizado);
            return ResponseEntity.ok(atualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Deleta um exercício pelo ID.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        exercicioService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
