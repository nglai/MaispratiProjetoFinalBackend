package com.br.maisAcademiaPrati.exercicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/exercicio")
public class ExercicioController {

    @Autowired
    ExercicioService exercicioService;

    @PostMapping()
    public ResponseEntity<?> criaMedida (@RequestBody ExercicioDTO data) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(this.exercicioService.criarExercicio(data));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<ExercicioEntity>> listaTodasMedidas () {
        return ResponseEntity.status(HttpStatus.OK).body(this.exercicioService.listaTodosExercicios());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExercicioEntity> buscaExercicioPorId (@PathVariable("id") UUID id) {
        Optional<ExercicioEntity> exercicio = exercicioService.buscaExercicioPorId(id);
        return exercicio.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<ExercicioEntity>> buscaExercicioPorNome (@PathVariable("nome") String nome) {
        return ResponseEntity.status(HttpStatus.OK).body(this.exercicioService.buscaExercicioPorNome(nome));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExercicioEntity> atualizaExercicioPorId (@PathVariable("id") UUID id, @RequestBody ExercicioDTO exercicioDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(this.exercicioService.atualizaExercicioPorId(id, exercicioDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarExercicioPorId(@PathVariable UUID id) {
        exercicioService.deletarExercicioPorId(id);
        return ResponseEntity.status(HttpStatus.OK).body("Exercicio deletado com sucesso!");
    }
}
