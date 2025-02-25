package com.br.maisAcademiaPrati.exercicio;

import com.br.maisAcademiaPrati.enums.Dificuldade;
import com.br.maisAcademiaPrati.enums.GrupoMuscular;
import org.springframework.beans.BeanUtils;
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

    // --- Cria um novo exercício via DTO ---
    @Transactional
    public ExercicioEntity criarExercicio(ExercicioDTO exercicioDTO) {
        ExercicioEntity exercicio = new ExercicioEntity();
        BeanUtils.copyProperties(exercicioDTO, exercicio);
        return exercicioRepository.save(exercicio);
    }

    // --- Lista todos os exercícios ---
    public List<ExercicioEntity> listarTodos() {
        return exercicioRepository.findAll();
    }

    // --- Busca por ID ---
    public Optional<ExercicioEntity> buscarPorId(UUID id) {
        return exercicioRepository.findById(id);
    }

    // --- Atualiza via DTO ---
    @Transactional
    public ExercicioEntity atualizarExercicio(UUID id, ExercicioDTO exercicioDTO) {
        return exercicioRepository.findById(id)
                .map(exercicioExistente -> {
                    BeanUtils.copyProperties(exercicioDTO, exercicioExistente, "id_exercicio");
                    return exercicioRepository.save(exercicioExistente);
                })
                .orElseThrow(() -> new RuntimeException("Exercício não encontrado"));
    }

    // --- Deleta ---
    @Transactional
    public void deletar(UUID id) {
        exercicioRepository.deleteById(id);
    }
}