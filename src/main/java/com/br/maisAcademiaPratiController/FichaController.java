package com.br.maisAcademiaPratiController;

import com.br.maisAcademiaPratiModel.Ficha;
import com.br.maisAcademiaPratiService.FichaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fichas")
public class FichaController {

    @Autowired
    private FichaService fichaService;

    @GetMapping
    public ResponseEntity<List<Ficha>> listarTodos() {
        return ResponseEntity.ok(fichaService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ficha> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(fichaService.buscarPorId(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Ficha salvar(@RequestBody Ficha ficha) {
        return fichaService.salvar(ficha);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ficha> atualizar(@PathVariable Long id, @RequestBody Ficha ficha) {
        return ResponseEntity.ok(fichaService.atualizar(id, ficha));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        fichaService.deletar(id);
    }
}

