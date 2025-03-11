package com.br.maisAcademiaPrati.aluno;

import com.br.maisAcademiaPrati.endereco.EnderecoEntity;
import com.br.maisAcademiaPrati.endereco.EnderecoRepository;
import com.br.maisAcademiaPrati.enums.Plano;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public AlunoEntity criarAluno (AlunoDTO alunoDTO) {
        var endereco = new EnderecoEntity();
        endereco.setRua(alunoDTO.endereco().rua());
        endereco.setBairro(alunoDTO.endereco().bairro());
        endereco.setCep(alunoDTO.endereco().cep());
        endereco.setComplemento(alunoDTO.endereco().complemento());
        enderecoRepository.save(endereco);

        AlunoEntity aluno = new AlunoEntity();
        BeanUtils.copyProperties(alunoDTO, aluno);
        aluno.setSenha(passwordEncoder.encode(alunoDTO.senha()));
        aluno.setPlano(Plano.fromString(alunoDTO.plano()));
        aluno.setEndereco(endereco);
        return alunoRepository.save(aluno);
    }

    public List<AlunoEntity> listaTodosAlunos() {
        return alunoRepository.findAll();
    }

    public Optional<AlunoEntity> buscaAlunoPorId(UUID id) {
        return alunoRepository.findById(id);
    }

    public AlunoEntity buscaAlunoPorEmail(String email) {
        return alunoRepository.findByEmail(email).orElse(null);
    }

    public AlunoEntity atualizaAlunoPorId(UUID id, AlunoDTO alunoDTO) {
        Optional<AlunoEntity> alunoEntity = alunoRepository.findById(id);
        if(alunoEntity.isPresent()){
            AlunoEntity aluno = alunoEntity.get();

            // Ignora a senha durante a cópia para não sobrescrever com texto simples
            BeanUtils.copyProperties(alunoDTO, aluno, "senha");

            // Codifica a nova senha se for fornecida
            if (alunoDTO.senha() != null && !alunoDTO.senha().isEmpty()) {
                aluno.setSenha(passwordEncoder.encode(alunoDTO.senha()));
            }

            // Atualiza o endereço
            EnderecoEntity endereco = aluno.getEndereco();
            endereco.setRua(alunoDTO.endereco().rua());
            endereco.setBairro(alunoDTO.endereco().bairro());
            endereco.setCep(alunoDTO.endereco().cep());
            endereco.setComplemento(alunoDTO.endereco().complemento());
            enderecoRepository.save(endereco);

            return alunoRepository.save(aluno);
        } else {
            throw new RuntimeException("Aluno não encontrado.");
        }
    }

    public AlunoEntity atualizarStatus(UUID id, boolean ativo) {
        AlunoEntity aluno = alunoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));

        aluno.setAtivo(ativo);
        return alunoRepository.save(aluno);
    }
}
