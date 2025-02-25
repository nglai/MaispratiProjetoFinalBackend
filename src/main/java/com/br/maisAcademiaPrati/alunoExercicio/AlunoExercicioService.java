package com.br.maisAcademiaPrati.alunoExercicio;

import com.br.maisAcademiaPrati.aluno.AlunoEntity;
import com.br.maisAcademiaPrati.aluno.AlunoRepository;
import com.br.maisAcademiaPrati.exercicio.ExercicioEntity;
import com.br.maisAcademiaPrati.exercicio.ExercicioRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class AlunoExercicioService {

    private final AlunoExercicioRepository alunoExercicioRepository;
    private final AlunoRepository alunoRepository;
    private final ExercicioRepository exercicioRepository;

    @Autowired
    public AlunoExercicioService(
            AlunoExercicioRepository alunoExercicioRepository,
            AlunoRepository alunoRepository,
            ExercicioRepository exercicioRepository
    ) {
        this.alunoExercicioRepository = alunoExercicioRepository;
        this.alunoRepository = alunoRepository;
        this.exercicioRepository = exercicioRepository;
    }

    // --- Método salvar corrigido ---
    @Transactional
    public AlunoExercicio salvar(AlunoExercicioDTO alunoExercicioDTO) {
        // Validação do Aluno
        AlunoEntity aluno = alunoRepository.findById(alunoExercicioDTO.idAluno())
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));

        // Validação do Exercício
        ExercicioEntity exercicio = exercicioRepository.findById(alunoExercicioDTO.idExercicio())
                .orElseThrow(() -> new RuntimeException("Exercício não encontrado"));

        // Criação da entidade
        AlunoExercicio alunoExercicio = new AlunoExercicio();
        BeanUtils.copyProperties(alunoExercicioDTO, alunoExercicio, "idAluno", "idExercicio");

        // Configuração da PK composta
        AlunoExercicioId compositeId = new AlunoExercicioId(
                alunoExercicioDTO.idAluno(),
                alunoExercicioDTO.idExercicio()
        );
        alunoExercicio.setId_aluno_exercicio(compositeId);
        alunoExercicio.setAluno(aluno);
        alunoExercicio.setExercicio(exercicio);

        return alunoExercicioRepository.save(alunoExercicio);
    }

    // --- Outros métodos ---
    public List<AlunoExercicio> listarTodos() {
        return alunoExercicioRepository.findAll();
    }

    public Optional<AlunoExercicio> buscarPorId(AlunoExercicioId id) {
        return alunoExercicioRepository.findById(id);
    }

    @Transactional
    public AlunoExercicio atualizar(AlunoExercicioId id, AlunoExercicio alunoExercicioAtualizado) {
        return alunoExercicioRepository.findById(id)
                .map(ae -> {
                    ae.setRepeticoes(alunoExercicioAtualizado.getRepeticoes());
                    ae.setSeries(alunoExercicioAtualizado.getSeries());
                    ae.setTipo_exercicio(alunoExercicioAtualizado.getTipo_exercicio());
                    return alunoExercicioRepository.save(ae);
                })
                .orElseThrow(() -> new RuntimeException("Registro não encontrado"));
    }

    @Transactional
    public void deletar(AlunoExercicioId id) {
        alunoExercicioRepository.deleteById(id);
    }
}