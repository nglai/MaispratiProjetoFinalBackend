package com.br.maisAcademiaPrati.alunoExercicio;

import com.br.maisAcademiaPrati.medida.MedidaDTO;
import com.br.maisAcademiaPrati.medida.MedidaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/alunoExercicio")
public class AlunoExercicioController {

    @Autowired
    AlunoExercicioService alunoExercicioService;

    @PostMapping("/{id}")
    public ResponseEntity<?> criaAlunoExercicio (@PathVariable("id") UUID alunoId, @RequestBody AlunoExercicioDTO data) {
        try {
            AlunoExercicio ae = this.alunoExercicioService.criaAlunoExercicio(alunoId, data);
            AlunoExercicioResponseDTO aeResponse = new AlunoExercicioResponseDTO(
                    ae.getId_aluno_exercicio().getId_aluno(),
                    ae.getId_aluno_exercicio().getId_exercicio(),
                    ae.getSeries(),
                    ae.getRepeticoes(),
                    ae.getTipo_exercicio());
            return ResponseEntity.status(HttpStatus.CREATED).body(aeResponse);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<AlunoExercicio>> listaTodosAlunoExercicio () {
        return ResponseEntity.status(HttpStatus.OK).body(this.alunoExercicioService.listaTodosAlunoExercicio());
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<AlunoExercicio>> listaExerciciosDoAluno(@PathVariable("id") UUID alunoId) {
        return ResponseEntity.status(HttpStatus.OK).body(this.alunoExercicioService.listaExerciciosDoAluno(alunoId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlunoExercicioResponseDTO> atualizaAlunoExercicio (@PathVariable("id") UUID id, @RequestBody AlunoExercicioDTO alunoExercicioDTO) {
        AlunoExercicio ae = this.alunoExercicioService.atualizaAlunoExercicio(id, alunoExercicioDTO);
        AlunoExercicioResponseDTO aeResponse = new AlunoExercicioResponseDTO(
                ae.getId_aluno_exercicio().getId_aluno(),
                ae.getId_aluno_exercicio().getId_exercicio(),
                ae.getSeries(),
                ae.getRepeticoes(),
                ae.getTipo_exercicio());
        return ResponseEntity.status(HttpStatus.CREATED).body(aeResponse);
    }

    @DeleteMapping("/{alunoId}/{exercicioId}")
    public ResponseEntity<String> deletarAlunoExercicioDoAlunoPorExercicioId (@PathVariable UUID alunoId, @PathVariable UUID exercicioId) {
        alunoExercicioService.deletarAlunoExercicioDoAlunoPorExercicioId(alunoId, exercicioId);
        return ResponseEntity.status(HttpStatus.OK).body("Medida deletada com sucesso!");
    }
}
