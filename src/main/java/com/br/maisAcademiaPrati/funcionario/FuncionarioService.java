package com.br.maisAcademiaPrati.funcionario;

import com.br.maisAcademiaPrati.endereco.EnderecoEntity;
import com.br.maisAcademiaPrati.endereco.EnderecoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public FuncionarioEntity criarFuncionario (FuncionarioDTO funcionarioDTO) {
        var endereco = new EnderecoEntity();
        endereco.setRua(funcionarioDTO.endereco().rua());
        endereco.setBairro(funcionarioDTO.endereco().bairro());
        endereco.setCep(funcionarioDTO.endereco().cep());
        endereco.setComplemento(funcionarioDTO.endereco().complemento());
        enderecoRepository.save(endereco);

        FuncionarioEntity funcionario = new FuncionarioEntity();
        BeanUtils.copyProperties(funcionarioDTO, funcionario);
        funcionario.setSenha(passwordEncoder.encode(funcionarioDTO.senha()));
        funcionario.setEndereco(endereco);
        return funcionarioRepository.save(funcionario);
    }

    public List<FuncionarioEntity> listaTodosFuncionarios() {
        return funcionarioRepository.findAll();
    }

    public FuncionarioEntity buscaFuncionarioPorEmail(String email) {
        return funcionarioRepository.findByEmail(email).orElse(null);
    }

    public Optional<FuncionarioEntity> buscaFuncionarioPorId(UUID id) {
        return funcionarioRepository.findById(id);
    }

    public FuncionarioEntity atualizaFuncionarioPorId(UUID id, FuncionarioDTO funcionarioDTO) {
        Optional<FuncionarioEntity> funcionarioEntity = funcionarioRepository.findById(id);

        if (funcionarioEntity.isPresent()) {
            FuncionarioEntity funcionario = funcionarioEntity.get();

            // Verifica se uma nova senha foi enviada
            if (funcionarioDTO.senha() != null && !funcionarioDTO.senha().isBlank()) {
                String senhaCriptografada = passwordEncoder.encode(funcionarioDTO.senha());
                funcionario.setSenha(senhaCriptografada);
            }

            // Copia apenas os campos relevantes, sem sobrescrever a senha
            if (funcionarioDTO.nome() != null) {
                funcionario.setNome(funcionarioDTO.nome());
            }
            if (funcionarioDTO.email() != null) {
                funcionario.setEmail(funcionarioDTO.email());
            }
            if (funcionarioDTO.documento() != null) {
                funcionario.setDocumento(funcionarioDTO.documento());
            }
            if (funcionarioDTO.role() != null) {
                funcionario.setRole(funcionarioDTO.role());
            }

            // Atualiza o endereço
            EnderecoEntity endereco = funcionario.getEndereco();
            if (funcionarioDTO.endereco() != null) {
                endereco.setRua(funcionarioDTO.endereco().rua());
                endereco.setBairro(funcionarioDTO.endereco().bairro());
                endereco.setCep(funcionarioDTO.endereco().cep());
                endereco.setComplemento(funcionarioDTO.endereco().complemento());
                enderecoRepository.save(endereco);
            }

            return funcionarioRepository.save(funcionario);
        } else {
            throw new RuntimeException("Funcionário não encontrado.");
        }
    }


}