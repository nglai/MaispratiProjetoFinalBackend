package com.br.maisAcademiaPratiController;

import com.br.maisAcademiaPratiModel.Avaliacao;
import com.br.maisAcademiaPratiService.AvaliacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/avaliacoes")
public class AvaliacaoController {

    @Autowired
    private AvaliacaoService avaliacaoService;

    @GetMapping
    public ResponseEntity<List<Avaliacao>> listarTodos() {
        return ResponseEntity.ok(avaliacaoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Avaliacao> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(avaliacaoService.buscarPorId(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Avaliacao salvar(@RequestBody Avaliacao avaliacao) {
        return avaliacaoService.salvar(avaliacao);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Avaliacao> atualizar(@PathVariable Long id, @RequestBody Avaliacao avaliacao) {
        return ResponseEntity.ok(avaliacaoService.atualizar(id, avaliacao));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        avaliacaoService.deletar(id);
    }
}
