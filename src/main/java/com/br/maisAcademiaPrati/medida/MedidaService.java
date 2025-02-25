package com.br.maisAcademiaPrati.medida;

import com.br.maisAcademiaPrati.aluno.AlunoEntity;
import com.br.maisAcademiaPrati.aluno.AlunoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MedidaService {

    private final MedidaRepository medidaRepository;
    private final AlunoRepository alunoRepository; // Adicione esta dependência

    @Autowired
    public MedidaService(MedidaRepository medidaRepository, AlunoRepository alunoRepository) {
        this.medidaRepository = medidaRepository;
        this.alunoRepository = alunoRepository;
    }

    // --- Método salvar corrigido (usa DTO) ---
    @Transactional
    public MedidaEntity salvar(MedidaDTO medidaDTO) {
        // Valida se o aluno existe
        AlunoEntity aluno = alunoRepository.findById(medidaDTO.alunoId())
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));

        // Cria a entidade a partir do DTO
        MedidaEntity medida = new MedidaEntity();
        BeanUtils.copyProperties(medidaDTO, medida, "alunoId", "dataMedida");
        medida.setAluno(aluno);
        medida.setData_medida(medidaDTO.dataMedida()); // Ajuste para o nome do campo na entidade

        return medidaRepository.save(medida);
    }

    // --- Método atualizar corrigido (usa DTO) ---
    @Transactional
    public MedidaEntity atualizar(UUID id, MedidaDTO medidaDTO) {
        return medidaRepository.findById(id)
                .map(medidaExistente -> {
                    BeanUtils.copyProperties(medidaDTO, medidaExistente, "id_medida", "alunoId");

                    // Atualiza aluno se necessário
                    if (medidaDTO.alunoId() != null) {
                        AlunoEntity aluno = alunoRepository.findById(medidaDTO.alunoId())
                                .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));
                        medidaExistente.setAluno(aluno);
                    }

                    return medidaRepository.save(medidaExistente);
                })
                .orElseThrow(() -> new RuntimeException("Medida não encontrada"));
    }

    // --- Métodos existentes mantidos ---
    public List<MedidaEntity> listarTodas() {
        return medidaRepository.findAll();
    }

    public Optional<MedidaEntity> buscarPorId(UUID id) {
        return medidaRepository.findById(id);
    }

    @Transactional
    public void deletar(UUID UUID id