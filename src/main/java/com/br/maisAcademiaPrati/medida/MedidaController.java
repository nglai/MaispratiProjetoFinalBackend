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

//    @GetMapping
//    public ResponseEntity<List<AlunoEntity>> listaTodosAlunos () {
//        return ResponseEntity.status(HttpStatus.OK).body(this.alunoService.listaTodosAlunos());
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<AlunoEntity> buscaAlunoPorId (@PathVariable("id") UUID id) {
//        Optional<AlunoEntity> aluno = alunoService.buscaAlunoPorId(id);
//        return aluno.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<AlunoEntity> atualizaAlunoPorId (@PathVariable("id") UUID id, @RequestBody AlunoDTO alunoDTO) {
//        return ResponseEntity.status(HttpStatus.OK).body(this.alunoService.atualizaAlunoPorId(id, alunoDTO));
//    }
}
