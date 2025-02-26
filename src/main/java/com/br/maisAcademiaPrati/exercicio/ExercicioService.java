package com.br.maisAcademiaPrati.exercicio;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ExercicioService {

    @Autowired
    private ExercicioRepository exercicioRepository;

    public ExercicioEntity criarExercicio (ExercicioDTO exercicioDTO) {
        ExercicioEntity exercicio = new ExercicioEntity();
        BeanUtils.copyProperties(exercicioDTO, exercicio);

        return exercicioRepository.save(exercicio);
    }

    public List<ExercicioEntity> listaTodosExercicios() {
        return exercicioRepository.findAll();
    }

    public Optional<ExercicioEntity> buscaExercicioPorId(UUID id) {
        return exercicioRepository.findById(id);
    }

    public List<ExercicioEntity> buscaExercicioPorNome(String nome) {
        return exercicioRepository.findByNomeContaining(nome);
    }

    public ExercicioEntity atualizaExercicioPorId(UUID id, ExercicioDTO exercicioDTO) {
        Optional<ExercicioEntity> exercicioEntity = exercicioRepository.findById(id);
        if(exercicioEntity.isPresent()){
            ExercicioEntity exercicio = exercicioEntity.get();

            if(exercicioDTO.nome() != null) {exercicio.setNome(exercicioDTO.nome());}
            if(exercicioDTO.grupo_muscular() != null) {exercicio.setGrupo_muscular(exercicioDTO.grupo_muscular());}
            if(exercicioDTO.dificuldade() != null) {exercicio.setDificuldade(exercicioDTO.dificuldade());}
            if(exercicioDTO.link_video() != null) {exercicio.setLink_video(exercicioDTO.link_video());}

            return exercicioRepository.save(exercicio);
        } else {
            throw new RuntimeException("Exercício não encontrado.");
        }
    }

    public void deletarExercicioPorId (UUID id) {
        Optional<ExercicioEntity> exercicioEntity = exercicioRepository.findById(id);
        exercicioEntity.ifPresent(entity -> this.exercicioRepository.delete(entity));
    }
}
