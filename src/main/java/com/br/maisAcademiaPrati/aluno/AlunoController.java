package com.br.maisAcademiaPrati.aluno;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/aluno")
public class AlunoController {

    @Autowired
    AlunoService alunoService;

    @PostMapping
    public ResponseEntity<?> criaAluno (@RequestBody AlunoDTO data) {
        try {
            if (alunoService.buscaAlunoPorEmail(data.email()) != null) {
               throw new IllegalArgumentException("Erro: Email já cadastrado.");
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(this.alunoService.criarAluno(data));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<AlunoEntity>> listaTodosAlunos () {
        return ResponseEntity.status(HttpStatus.OK).body(this.alunoService.listaTodosAlunos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlunoEntity> buscaAlunoPorId (@PathVariable("id") UUID id) {
        Optional<AlunoEntity> aluno = alunoService.buscaAlunoPorId(id);
        return aluno.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlunoEntity> atualizaAlunoPorId (@PathVariable("id") UUID id, @RequestBody AlunoDTO alunoDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(this.alunoService.atualizaAlunoPorId(id, alunoDTO));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<?> atualizarStatusAluno(@PathVariable("id") UUID id, @RequestBody Map<String, Boolean> status) {
        try {
            Boolean ativo = status.get("ativo");
            if (ativo == null) {
                return ResponseEntity.badRequest().body("Campo 'ativo' é obrigatório");
            }

            AlunoEntity aluno = alunoService.atualizarStatus(id, ativo);
            return ResponseEntity.ok(aluno);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao atualizar status do aluno: " + e.getMessage());
        }
    }
}
