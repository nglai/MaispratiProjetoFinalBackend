package com.br.maisAcademiaPrati.funcionario;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public FuncionarioEntity criarFuncionario (FuncionarioDTO funcionarioDTO) {
        FuncionarioEntity funcionario = new FuncionarioEntity();
        BeanUtils.copyProperties(funcionarioDTO, funcionario);
        return funcionarioRepository.save(funcionario);
    }

    public List<FuncionarioEntity> listaTodosFuncionarios() {
        return funcionarioRepository.findAll();
    }

    public Optional<FuncionarioEntity> buscaFuncionarioPorId(UUID id) {
        return funcionarioRepository.findById(id);
    }

    public FuncionarioEntity atualizaFuncionarioPorId(UUID id, FuncionarioDTO funcionarioDTO) {
        Optional<FuncionarioEntity> funcionarioEntity = funcionarioRepository.findById(id);
        if(funcionarioEntity.isPresent()){
            FuncionarioEntity funcionario = funcionarioEntity.get();
            BeanUtils.copyProperties(funcionarioDTO, funcionario);
            return funcionarioRepository.save(funcionario);
        } else {
            throw new RuntimeException("Funcionário não encontrado.");
        }
    }
}