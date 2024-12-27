package com.br.maisAcademiaPrati.aluno;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    public AlunoEntity criarAluno (AlunoDTO alunoDTO) {
        AlunoEntity aluno = new AlunoEntity();
        BeanUtils.copyProperties(alunoDTO, aluno);
        return alunoRepository.save(aluno);
    }

    public List<AlunoEntity> listaTodosAlunos() {
        return alunoRepository.findAll();
    }

    public Optional<AlunoEntity> buscaAlunoPorId(UUID id) {
        return alunoRepository.findById(id);
    }

    public AlunoEntity atualizaAlunoPorId(UUID id, AlunoDTO alunoDTO) {
        Optional<AlunoEntity> alunoEntity = alunoRepository.findById(id);
        if(alunoEntity.isPresent()){
            AlunoEntity aluno = alunoEntity.get();
            BeanUtils.copyProperties(alunoDTO, aluno);
            return alunoRepository.save(aluno);
        } else {
            throw new RuntimeException("Aluno não encontrado.");
        }
    }
}
