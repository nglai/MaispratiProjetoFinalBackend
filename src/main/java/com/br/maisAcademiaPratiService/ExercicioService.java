package com.br.maisAcademiaPratiService;

import com.br.maisAcademiaPratiModel.Exercicio;
import com.br.AcademiaPratiRepository.ExercicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ExercicioService {

    @Autowired
    private ExercicioRepository exercicioRepository;

    public List<Exercicio> listarTodos() {
        return exercicioRepository.findAll();
    }

    public Exercicio buscarPorId(Long id) {
        return exercicioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Exercício não encontrado com id " + id));
    }

    public Exercicio salvar(Exercicio exercicio) {
        return exercicioRepository.save(exercicio);
    }

    public Exercicio atualizar(Long id, Exercicio exercicioAtualizado) {
        Exercicio exercicioExistente = buscarPorId(id);
        exercicioExistente.setNome(exercicioAtualizado.getNome());
        exercicioExistente.setDescricao(exercicioAtualizado.getDescricao());
        exercicioExistente.setFicha(exercicioAtualizado.getFicha());
        return exercicioRepository.save(exercicioExistente);
    }

    public void deletar(Long id) {
        Exercicio exercicio = buscarPorId(id);
        exercicioRepository.delete(exercicio);
    }
}
