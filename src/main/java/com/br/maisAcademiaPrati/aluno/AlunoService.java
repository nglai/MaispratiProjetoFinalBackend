package com.br.maisAcademiaPrati.aluno;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    public AlunoEntity criarAluno (AlunoDTO alunoDTO) {
        AlunoEntity aluno = new AlunoEntity();
        BeanUtils.copyProperties(alunoDTO, aluno);
        return alunoRepository.save(aluno);
    }
}
