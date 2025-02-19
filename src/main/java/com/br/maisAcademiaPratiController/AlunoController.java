package com.br.maisAcademiaPratiController;

import com.br.maisAcademiaPratiModel.Aluno;
import com.br.maisAcademiaPratiService.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    // GET /alunos - Lista todos os alunos
    @GetMapping
    public ResponseEntity<List<Aluno>> listarTodos() {
        return ResponseEntity.ok(alunoService.listarTodos());
    }

    // GET /alunos/{id} - Busca um aluno por ID
    @GetMapping("/{id}")
    public ResponseEntity<Aluno> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(alunoService.buscarPorId(id));
    }

    // POST /alunos - Cria um novo aluno
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Aluno salvar(@RequestBody Aluno aluno) {
        return alunoService.salvar(aluno);
    }

    // PUT /alunos/{id} - Atualiza um aluno existente
    @PutMapping("/{id}")
    public ResponseEntity<Aluno> atualizar(@PathVariable Long id, @RequestBody Aluno aluno) {
        return ResponseEntity.ok(alunoService.atualizar(id, aluno));
    }

    // DELETE /alunos/{id} - Deleta um aluno
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        alunoService.deletar(id);
    }
}
