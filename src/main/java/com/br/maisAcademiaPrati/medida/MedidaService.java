package com.br.maisAcademiaPrati.medida;

import com.br.maisAcademiaPrati.aluno.AlunoDTO;
import com.br.maisAcademiaPrati.aluno.AlunoEntity;
import com.br.maisAcademiaPrati.aluno.AlunoRepository;
import com.br.maisAcademiaPrati.endereco.EnderecoEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));

        MedidaEntity medida = new MedidaEntity();
        BeanUtils.copyProperties(medidaDTO, medida);
        medida.setAluno(aluno);

        return medidaRepository.save(medida);
    }

    public List<MedidaEntity> listaTodasMedidas() {
        return medidaRepository.findAll();
    }

    public Optional<MedidaEntity> buscaMedidaPorId(UUID id) {
        return medidaRepository.findById(id);
    }

    public List<MedidaDTO> listaMedidasDoAluno(UUID alunoId) {
        AlunoEntity aluno = alunoRepository.findById(alunoId)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));

        return aluno.getMedidas()
                .stream()
                .map(m -> new MedidaDTO(m.getData_medida(), m.getPeso(),
                        m.getBiceps(), m.getTriceps(), m.getAbdomen(),
                        m.getGluteo(), m.getCoxa(), m.getPanturrilha()))
                .toList();
    }

    public MedidaEntity atualizaMedidaPorId(UUID id, MedidaDTO medidaDTO) {
        Optional<MedidaEntity> medidaEntity = medidaRepository.findById(id);
        if(medidaEntity.isPresent()){
            MedidaEntity medida = medidaEntity.get();

            if(medidaDTO.peso() != null) {medida.setPeso(medidaDTO.peso());}
            if(medidaDTO.biceps() != null) {medida.setBiceps(medidaDTO.biceps());}
            if(medidaDTO.triceps() != null) {medida.setTriceps(medidaDTO.triceps());}
            if(medidaDTO.abdomen() != null) {medida.setAbdomen(medidaDTO.abdomen());}
            if(medidaDTO.gluteo() != null) {medida.setGluteo(medidaDTO.gluteo());}
            if(medidaDTO.coxa() != null) {medida.setCoxa(medidaDTO.coxa());}
            if(medidaDTO.panturrilha() != null) {medida.setPanturrilha(medidaDTO.panturrilha());}

            return medidaRepository.save(medida);
        } else {
            throw new RuntimeException("Medida não encontrada.");
        }
    }

    public void deletarMedidaPorId (UUID id) {
        Optional<MedidaEntity> medidaEntity = medidaRepository.findById(id);
        medidaEntity.ifPresent(entity -> this.medidaRepository.delete(entity));
    }
}
