package com.br.maisAcademiaPrati.alunoExercicio;

import com.br.maisAcademiaPrati.aluno.AlunoEntity;
import com.br.maisAcademiaPrati.aluno.AlunoRepository;
import com.br.maisAcademiaPrati.exercicio.ExercicioEntity;
import com.br.maisAcademiaPrati.exercicio.ExercicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AlunoExercicioService {

    @Autowired
    private AlunoExercicioRepository alunoExercicioRepository;
    @Autowired
    private AlunoRepository alunoRepository;
    @Autowired
    private ExercicioRepository exercicioRepository;

    public AlunoExercicio criaAlunoExercicio (UUID alunoId, AlunoExercicioDTO alunoExercicioDTO) {
        AlunoEntity aluno = alunoRepository.findById(alunoId)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));

        ExercicioEntity exercicio = exercicioRepository.findById(alunoExercicioDTO.idExercicio())
                .orElseThrow(() -> new RuntimeException("Exercício não encontrado"));

        AlunoExercicioId id = new AlunoExercicioId(aluno.getId_aluno(), exercicio.getId_exercicio());
        AlunoExercicio ae = new AlunoExercicio(
                id,
                aluno,
                exercicio,
                alunoExercicioDTO.repeticoes(),
                alunoExercicioDTO.series(),
                alunoExercicioDTO.tipo_exercicio()
        );

        return alunoExercicioRepository.save(ae);
    }

    public List<AlunoExercicio> listaTodosAlunoExercicio() {
        return alunoExercicioRepository.findAll();
    }

    public List<AlunoExercicio> listaExerciciosDoAluno(UUID alunoId) {
        AlunoEntity aluno = alunoRepository.findById(alunoId)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));

        return aluno.getExercicios()
                .stream()
                .map(ae -> new AlunoExercicio(
                        ae.getId_aluno_exercicio(),
                        ae.getAluno(), ae.getExercicio(),
                        ae.getRepeticoes(),
                        ae.getSeries(), ae.getTipo_exercicio()
                ))
                .toList();
    }

    public AlunoExercicio atualizaAlunoExercicio(UUID alunoId, AlunoExercicioDTO alunoExercicioDTO) {
        List<AlunoExercicio> listaExercicios = listaExerciciosDoAluno(alunoId);

        AlunoExercicio alunoExercicio = null;

        if(listaExercicios != null || !listaExercicios.isEmpty()) {
            for(AlunoExercicio ae : listaExercicios) {
                if(ae.getExercicio().getId_exercicio().equals(alunoExercicioDTO.idExercicio())){
                    alunoExercicio = ae;
                }
            }
        }

        if(alunoExercicio != null){
            if(alunoExercicioDTO.repeticoes() != null) { alunoExercicio.setRepeticoes(alunoExercicioDTO.repeticoes());}
            if(alunoExercicioDTO.series() != null) { alunoExercicio.setSeries(alunoExercicioDTO.series());}
            if(alunoExercicioDTO.tipo_exercicio() != null) { alunoExercicio.setTipo_exercicio(alunoExercicioDTO.tipo_exercicio());}

            return alunoExercicioRepository.save(alunoExercicio);
        } else {
            throw new RuntimeException("Exercício do aluno não encontrado.");
        }
    }

    public void deletarAlunoExercicioDoAlunoPorExercicioId (UUID alunoId, UUID exercicioId) {
        List<AlunoExercicio> listaExercicios = listaExerciciosDoAluno(alunoId);

        AlunoExercicio alunoExercicio = null;

        if(listaExercicios != null || !listaExercicios.isEmpty()) {
            for(AlunoExercicio ae : listaExercicios) {
                if(ae.getExercicio().getId_exercicio().equals(exercicioId)){
                    alunoExercicio = ae;
                }
            }
        }

        if(alunoExercicio != null){
            this.alunoExercicioRepository.delete(alunoExercicio);
        } else {
            throw new RuntimeException("Exercício do aluno não encontrado.");
        }
    }
}
