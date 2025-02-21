package com.br.maisAcademiaPrati.alunoExercicio;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/aluno-exercicios")
public class AlunoExercicioController {

    private final AlunoExercicioService alunoExercicioService;

    public AlunoExercicioController(AlunoExercicioService alunoExercicioService) {
        this.alunoExercicioService = alunoExercicioService;
    }

    /**
     * Lista todos os registros de AlunoExercicio.
     */
    @GetMapping
    public List<AlunoExercicio> listarTodos() {
        return alunoExercicioService.listarTodos();
    }

    /**
     * Busca um registro de AlunoExercicio pela PK composta (id_aluno, id_exercicio).
     * Exemplo de endpoint: GET /api/aluno-exercicios/{idAluno}/{idExercicio}
     */
    @GetMapping("/{idAluno}/{idExercicio}")
    public ResponseEntity<AlunoExercicio> buscarPorId(@PathVariable UUID idAluno,
                                                      @PathVariable UUID idExercicio) {
        AlunoExercicioId compositeId = new AlunoExercicioId(idAluno, idExercicio);
        return alunoExercicioService.buscarPorId(compositeId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Cria um novo registro de AlunoExercicio.
     */
    @PostMapping
    public AlunoExercicio salvar(@RequestBody AlunoExercicio alunoExercicio) {
        return alunoExercicioService.salvar(alunoExercicio);
    }

    /**
     * Atualiza um registro de AlunoExercicio existente pela PK composta.
     */
    @PutMapping("/{idAluno}/{idExercicio}")
    public ResponseEntity<AlunoExercicio> atualizar(@PathVariable UUID idAluno,
                                                    @PathVariable UUID idExercicio,
                                                    @RequestBody AlunoExercicio alunoExercicioAtualizado) {
        try {
            AlunoExercicioId compositeId = new AlunoExercicioId(idAluno, idExercicio);
            AlunoExercicio atualizado = alunoExercicioService.atualizar(compositeId, alunoExercicioAtualizado);
            return ResponseEntity.ok(atualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Deleta um registro de AlunoExercicio pela PK composta.
     */
    @DeleteMapping("/{idAluno}/{idExercicio}")
    public ResponseEntity<Void> deletar(@PathVariable UUID idAluno,
                                        @PathVariable UUID idExercicio) {
        AlunoExercicioId compositeId = new AlunoExercicioId(idAluno, idExercicio);
        alunoExercicioService.deletar(compositeId);
        return ResponseEntity.noContent().build();
    }
}
