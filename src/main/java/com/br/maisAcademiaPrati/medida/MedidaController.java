package com.br.maisAcademiaPrati.medida;

import com.br.maisAcademiaPrati.aluno.AlunoDTO;
import com.br.maisAcademiaPrati.aluno.AlunoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/medida")
public class MedidaController {

    @Autowired
    MedidaService medidaService;

    @PostMapping("/{id}")
    public ResponseEntity<?> criaMedida (@PathVariable("id") UUID id, @RequestBody MedidaDTO data) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(this.medidaService.criarMedida(id, data));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<MedidaEntity>> listaTodasMedidas () {
        return ResponseEntity.status(HttpStatus.OK).body(this.medidaService.listaTodasMedidas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedidaEntity> buscaMedidaPorId (@PathVariable("id") UUID id) {
        Optional<MedidaEntity> medida = medidaService.buscaMedidaPorId(id);
        return medida.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/aluno/{id}")
    public ResponseEntity<List<MedidaDTO>> listaMedidasDoAluno(@PathVariable("id") UUID alunoId) {
        return ResponseEntity.status(HttpStatus.OK).body(this.medidaService.listaMedidasDoAluno(alunoId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedidaEntity> atualizaMedidaPorId (@PathVariable("id") UUID id, @RequestBody MedidaDTO medidaDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(this.medidaService.atualizaMedidaPorId(id, medidaDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletar(@PathVariable UUID id) {
        medidaService.deletarMedidaPorId(id);
        return ResponseEntity.status(HttpStatus.OK).body("Medida deletada com sucesso!");
    }
}
