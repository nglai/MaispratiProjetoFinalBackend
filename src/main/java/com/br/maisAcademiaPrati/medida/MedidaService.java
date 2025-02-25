package com.br.maisAcademiaPrati.medida;

import com.br.maisAcademiaPrati.aluno.AlunoDTO;
import com.br.maisAcademiaPrati.aluno.AlunoEntity;
import com.br.maisAcademiaPrati.aluno.AlunoRepository;
import com.br.maisAcademiaPrati.endereco.EnderecoEntity;
import com.br.maisAcademiaPrati.endereco.EnderecoRepository;
import com.br.maisAcademiaPrati.enums.Plano;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MedidaService {

    @Autowired
    private MedidaRepository medidaRepository;

    @Autowired
    private AlunoRepository alunoRepository;

    public MedidaEntity criarMedida (UUID alunoId, MedidaDTO medidaDTO) {
        AlunoEntity aluno = alunoRepository.findById(alunoId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        MedidaEntity medida = new MedidaEntity();
        BeanUtils.copyProperties(medidaDTO, medida);
        medida.setAluno(aluno);

        return medidaRepository.save(medida);
    }

//    public List<AlunoEntity> listaTodosAlunos() {
//        return alunoRepository.findAll();
//    }
//
//    public Optional<AlunoEntity> buscaAlunoPorId(UUID id) {
//        return alunoRepository.findById(id);
//    }
//
//    public AlunoEntity buscaAlunoPorEmail(String email) {
//        return alunoRepository.findByEmail(email).orElse(null);
//    }
//
//    public AlunoEntity atualizaAlunoPorId(UUID id, AlunoDTO alunoDTO) {
//        Optional<AlunoEntity> alunoEntity = alunoRepository.findById(id);
//        if(alunoEntity.isPresent()){
//            AlunoEntity aluno = alunoEntity.get();
//            BeanUtils.copyProperties(alunoDTO, aluno);
//
//            EnderecoEntity endereco = aluno.getEndereco();
//            endereco.setRua(alunoDTO.endereco().rua());
//            endereco.setBairro(alunoDTO.endereco().bairro());
//            endereco.setCep(alunoDTO.endereco().cep());
//            endereco.setComplemento(alunoDTO.endereco().complemento());
//            enderecoRepository.save(endereco);
//
//            return alunoRepository.save(aluno);
//        } else {
//            throw new RuntimeException("Aluno não encontrado.");
//        }
//    }
}
