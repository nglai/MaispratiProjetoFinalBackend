package com.br.maisAcademiaPrati.medida;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/medidas")
public class MedidaController {

    private final MedidaService medidaService;

    public MedidaController(MedidaService medidaService) {
        this.medidaService = medidaService;
    }

    @GetMapping
    public List<MedidaEntity> listarTodas() {
        return medidaService.listarTodas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedidaEntity> buscarPorId(@PathVariable UUID id) {
        return medidaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public MedidaEntity salvar(@RequestBody MedidaEntity medida) {
        return medidaService.salvar(medida);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedidaEntity> atualizar(@PathVariable UUID id,
                                                  @RequestBody MedidaEntity medidaAtualizada) {
        try {
            MedidaEntity atualizado = medidaService.atualizar(id, medidaAtualizada);
            return ResponseEntity.ok(atualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        medidaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
