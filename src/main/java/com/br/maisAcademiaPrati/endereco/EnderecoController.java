package com.br.maisAcademiaPrati.endereco;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/enderecos")
public class EnderecoController {

    private final EnderecoService enderecoService;

    public EnderecoController(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }

    /**
     * Lista todos os endereços.
     */
    @GetMapping
    public List<EnderecoEntity> listarTodos() {
        return enderecoService.listarTodos();
    }

    /**
     * Busca um endereço pelo ID (UUID).
     */
    @GetMapping("/{id}")
    public ResponseEntity<EnderecoEntity> buscarPorId(@PathVariable UUID id) {
        Optional<EnderecoEntity> enderecoOpt = enderecoService.buscarPorId(id);
        return enderecoOpt.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Cria um novo endereço a partir do DTO.
     */
    @PostMapping
    public EnderecoEntity salvar(@RequestBody EnderecoDTO dto) {
        return enderecoService.salvar(dto);
    }

    /**
     * Atualiza um endereço existente (caso encontrado).
     */
    @PutMapping("/{id}")
    public ResponseEntity<EnderecoEntity> atualizar(@PathVariable UUID id,
                                                    @RequestBody EnderecoDTO dto) {
        try {
            EnderecoEntity atualizado = enderecoService.atualizar(id, dto);
            return ResponseEntity.ok(atualizado);
        } catch (RuntimeException e) {
            // Lança exceção se não encontrar o endereço
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Exclui um endereço pelo ID (UUID).
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        enderecoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
