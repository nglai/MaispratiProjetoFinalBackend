package com.br.maisAcademiaPrati.alunoExercicio;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/aluno-exercicios")
public class AlunoExercicioController {

    private final AlunoExercicioService alunoExercicioService;

    public AlunoExercicioController(AlunoExercicioService alunoExercicioService) {
        this.alunoExercicioService = alunoExercicioService;
    }

    // --- POST corrigido (usa DTO) ---
    @PostMapping
    public ResponseEntity<?> salvar(@Valid @RequestBody AlunoExercicioDTO alunoExercicioDTO) {
        try {
            AlunoExercicio salvo = alunoExercicioService.salvar(alunoExercicioDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // --- Outros endpoints ---
    @GetMapping
    public List<AlunoExercicio> listarTodos() {
        return alunoExercicioService.listarTodos();
    }

    @GetMapping("/{idAluno}/{idExercicio}")
    public ResponseEntity<AlunoExercicio> buscarPorId(
            @PathVariable UUID idAluno,
            @PathVariable UUID idExercicio
    ) {
        AlunoExercicioId compositeId = new AlunoExercicioId(idAluno, idExercicio);
        return alunoExercicioService.buscarPorId(compositeId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{idAluno}/{idExercicio}")
    public ResponseEntity<AlunoExercicio> atualizar(
            @PathVariable UUID idAluno,
            @PathVariable UUID idExercicio,
            @RequestBody AlunoExercicio alunoExercicioAtualizado
    ) {
        try {
            AlunoExercicioId compositeId = new AlunoExercicioId(idAluno, idExercicio);
            AlunoExercicio atualizado = alunoExercicioService.atualizar(compositeId, alunoExercicioAtualizado);
            return ResponseEntity.ok(atualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{idAluno}/{idExercicio}")
    public ResponseEntity<Void> deletar(
            @PathVariable UUID idAluno,
            @PathVariable UUID idExercicio
    ) {
        AlunoExercicioId compositeId = new AlunoExercicioId(idAluno, idExercicio);
        alunoExercicioService.deletar(compositeId);
        return ResponseEntity.noContent().build();
    }
}