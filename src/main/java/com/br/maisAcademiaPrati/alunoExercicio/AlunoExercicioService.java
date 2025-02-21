package com.br.maisAcademiaPrati.alunoExercicio;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AlunoExercicioService {

    private final AlunoExercicioRepository alunoExercicioRepository;

    public AlunoExercicioService(AlunoExercicioRepository alunoExercicioRepository) {
        this.alunoExercicioRepository = alunoExercicioRepository;
    }

    /**
     * Lista todos os registros de AlunoExercicio.
     */
    public List<AlunoExercicio> listarTodos() {
        return alunoExercicioRepository.findAll();
    }

    /**
     * Busca um registro de AlunoExercicio pelo ID composto (AlunoExercicioId).
     */
    public Optional<AlunoExercicio> buscarPorId(AlunoExercicioId id) {
        return alunoExercicioRepository.findById(id);
    }

    /**
     * Salva um novo registro de AlunoExercicio no banco.
     */
    @Transactional
    public AlunoExercicio salvar(AlunoExercicio alunoExercicio) {
        return alunoExercicioRepository.save(alunoExercicio);
    }

    /**
     * Atualiza um registro existente de AlunoExercicio.
     * Caso não exista, lança exceção.
     */
    @Transactional
    public AlunoExercicio atualizar(AlunoExercicioId id, AlunoExercicio alunoExercicioAtualizado) {
        Optional<AlunoExercicio> optAE = alunoExercicioRepository.findById(id);
        if (optAE.isPresent()) {
            AlunoExercicio ae = optAE.get();
            // Campos que podem ser atualizados (exceto o ID, que é fixo)
            ae.setRepeticoes(alunoExercicioAtualizado.getRepeticoes());
            ae.setSeries(alunoExercicioAtualizado.getSeries());
            ae.setTipo_exercicio(alunoExercicioAtualizado.getTipo_exercicio());
            // Se você quiser permitir trocar de aluno ou exercício, tome cuidado,
            // pois isso muda a PK. Normalmente não se troca PK, mas se precisar:
            // ae.setAluno(alunoExercicioAtualizado.getAluno());
            // ae.setExercicio(alunoExercicioAtualizado.getExercicio());
            return alunoExercicioRepository.save(ae);
        }
        throw new RuntimeException("AlunoExercicio não encontrado para o ID composto: " + id);
    }

    /**
     * Deleta um registro de AlunoExercicio pelo ID composto.
     */
    @Transactional
    public void deletar(AlunoExercicioId id) {
        alunoExercicioRepository.deleteById(id);
    }
}
