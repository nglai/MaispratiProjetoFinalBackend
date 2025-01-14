package com.br.maisAcademiaPrati.recepcionista;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/recepcionista")
public class RecepcionistaController {

    @Autowired
    RecepcionistaService recepcionistaService;

    @PostMapping
    public ResponseEntity<RecepcionistaEntity> criaRecepcionista(@RequestBody RecepcionistaDTO data) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.recepcionistaService.criarRecepcionista(data));
    }

    @GetMapping
    public ResponseEntity<List<RecepcionistaEntity>> listaTodosRecepcionistas() {
        return ResponseEntity.status(HttpStatus.OK).body(this.recepcionistaService.listaTodosRecepcionistas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecepcionistaEntity> buscaRecepcionistaPorId(@PathVariable("id") UUID id) {
        Optional<RecepcionistaEntity> recepcionista = recepcionistaService.buscaRecepcionistaPorId(id);
        return recepcionista.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<RecepcionistaEntity> atualizaRecepcionistaPorId(@PathVariable("id") UUID id, @RequestBody RecepcionistaDTO recepcionistaDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(this.recepcionistaService.atualizaRecepcionistaPorId(id, recepcionistaDTO));
    }
}
