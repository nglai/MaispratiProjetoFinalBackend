package com.br.maisAcademiaPrati.exercicio;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ExercicioService {

    private final ExercicioRepository exercicioRepository;

    public ExercicioService(ExercicioRepository exercicioRepository) {
        this.exercicioRepository = exercicioRepository;
    }

    /**
     * Lista todos os exercícios cadastrados.
     */
    public List<ExercicioEntity> listarTodos() {
        return exercicioRepository.findAll();
    }

    /**
     * Busca um exercício pelo ID (UUID).
     */
    public Optional<ExercicioEntity> buscarPorId(UUID id) {
        return exercicioRepository.findById(id);
    }

    /**
     * Cria/salva um novo exercício no banco de dados.
     */
    @Transactional
    public ExercicioEntity salvar(ExercicioEntity exercicio) {
        return exercicioRepository.save(exercicio);
    }

    /**
     * Atualiza um exercício existente, caso ele seja encontrado.
     */
    @Transactional
    public ExercicioEntity atualizar(UUID id, ExercicioEntity exercicioAtualizado) {
        Optional<ExercicioEntity> optExercicio = exercicioRepository.findById(id);
        if (optExercicio.isPresent()) {
            ExercicioEntity exercicio = optExercicio.get();
            // Atualiza apenas os campos necessários
            exercicio.setNome(exercicioAtualizado.getNome());
            exercicio.setGrupo_muscular(exercicioAtualizado.getGrupo_muscular());
            exercicio.setDificuldade(exercicioAtualizado.getDificuldade());
            exercicio.setLink_video(exercicioAtualizado.getLink_video());
            return exercicioRepository.save(exercicio);
        }
        throw new RuntimeException("Exercício não encontrado para o ID: " + id);
    }

    /**
     * Deleta um exercício pelo ID (UUID).
     */
    @Transactional
    public void deletar(UUID id) {
        exercicioRepository.deleteById(id);
    }
}
