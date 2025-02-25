package com.br.maisAcademiaPrati.medida;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/medidas")
public class MedidaController {

    private final MedidaService medidaService;

    public MedidaController(MedidaService medidaService) {
        this.medidaService = medidaService;
    }

    // --- POST corrigido (recebe DTO) ---
    @PostMapping
    public ResponseEntity<?> salvar(@Valid @RequestBody MedidaDTO medidaDTO) {
        try {
            MedidaEntity medidaSalva = medidaService.salvar(medidaDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(medidaSalva);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // --- PUT corrigido (recebe DTO) ---
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(
            @PathVariable UUID id,
            @Valid @RequestBody MedidaDTO medidaDTO
    ) {
        try {
            MedidaEntity medidaAtualizada = medidaService.atualizar(id, medidaDTO);
            return ResponseEntity.ok(medidaAtualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // --- Métodos existentes mantidos ---
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

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        medidaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}