package com.br.maisAcademiaPrati.professor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/professor")
public class ProfessorController {

    @Autowired
    ProfessorService professorService;

    @PostMapping
    public ResponseEntity<ProfessorEntity> criaProfessor(@RequestBody ProfessorDTO data) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.professorService.criarProfessor(data));
    }

    @GetMapping
    public ResponseEntity<List<ProfessorEntity>> listaTodosProfessores() {
        return ResponseEntity.status(HttpStatus.OK).body(this.professorService.listaTodosProfessores());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfessorEntity> buscaProfessorPorId(@PathVariable("id") UUID id) {
        Optional<ProfessorEntity> professor = professorService.buscaProfessorPorId(id);
        return professor.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfessorEntity> atualizaProfessorPorId(@PathVariable("id") UUID id, @RequestBody ProfessorDTO professorDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(this.professorService.atualizaProfessorPorId(id, professorDTO));
    }
}
