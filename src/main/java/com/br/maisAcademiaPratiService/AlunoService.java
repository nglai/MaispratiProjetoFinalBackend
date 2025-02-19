package com.br.maisAcademiaPratiService;

import com.br.AcademiaPratiRepository.AlunoRepository;
import com.br.maisAcademiaPratiModel.Aluno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    // READ: Listar todos os alunos
    public List<Aluno> listarTodos() {
        return alunoRepository.findAll();
    }

    // READ: Buscar aluno por ID
    public Aluno buscarPorId(Long id) {
        return alunoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado com id " + id));
    }

    // CREATE: Salvar um novo aluno
    public Aluno salvar(Aluno aluno) {
        return alunoRepository.save(aluno);
    }

    // UPDATE: Atualizar dados de um aluno existente
    public Aluno atualizar(Long id, Aluno alunoAtualizado) {
        Aluno alunoExistente = buscarPorId(id);
        alunoExistente.setNome(alunoAtualizado.getNome());
        alunoExistente.setEmail(alunoAtualizado.getEmail());
        alunoExistente.setTelefone(alunoAtualizado.getTelefone());
        alunoExistente.setDataNascimento(alunoAtualizado.getDataNascimento());
        return alunoRepository.save(alunoExistente);
    }

    // DELETE: Deletar um aluno
    public void deletar(Long id) {
        Aluno aluno = buscarPorId(id);
        alunoRepository.delete(aluno);
    }
}
