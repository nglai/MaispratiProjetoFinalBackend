package com.br.maisAcademiaPrati.exercicio;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/exercicios")
public class ExercicioController {

    private final ExercicioService exercicioService;

    public ExercicioController(ExercicioService exercicioService) {
        this.exercicioService = exercicioService;
    }

    // --- Criação (POST) com DTO ---
    @PostMapping
    public ResponseEntity<?> criarExercicio(@Valid @RequestBody ExercicioDTO exercicioDTO) {
        try {
            ExercicioEntity exercicioSalvo = exercicioService.criarExercicio(exercicioDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(exercicioSalvo);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // --- Listagem (GET) ---
    @GetMapping
    public List<ExercicioEntity> listarTodos() {
        return exercicioService.listarTodos();
    }

    // --- Busca por ID (GET) ---
    @GetMapping("/{id}")
    public ResponseEntity<ExercicioEntity> buscarPorId(@PathVariable UUID id) {
        return exercicioService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // --- Atualização (PUT) com DTO ---
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(
            @PathVariable UUID id,
            @Valid @RequestBody ExercicioDTO exercicioDTO
    ) {
        try {
            ExercicioEntity atualizado = exercicioService.atualizarExercicio(id, exercicioDTO);
            return ResponseEntity.ok(atualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // --- Exclusão (DELETE) ---
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        exercicioService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}