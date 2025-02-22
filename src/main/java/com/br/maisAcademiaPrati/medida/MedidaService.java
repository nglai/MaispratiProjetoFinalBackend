package com.br.maisAcademiaPrati.medida;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MedidaService {

    private final MedidaRepository medidaRepository;

    public MedidaService(MedidaRepository medidaRepository) {
        this.medidaRepository = medidaRepository;
    }

    public List<MedidaEntity> listarTodas() {
        return medidaRepository.findAll();
    }

    public Optional<MedidaEntity> buscarPorId(UUID id) {
        return medidaRepository.findById(id);
    }

    @Transactional
    public MedidaEntity salvar(MedidaEntity medida) {
        return medidaRepository.save(medida);
    }

    @Transactional
    public MedidaEntity atualizar(UUID id, MedidaEntity medidaAtualizada) {
        return medidaRepository.findById(id)
                .map(m -> {
                    m.setData_medida(medidaAtualizada.getData_medida());
                    m.setPeso(medidaAtualizada.getPeso());
                    m.setBiceps(medidaAtualizada.getBiceps());
                    m.setTriceps(medidaAtualizada.getTriceps()); // Se existir
                    m.setAbdomen(medidaAtualizada.getAbdomen());
                    m.setGluteo(medidaAtualizada.getGluteo());
                    m.setCoxa(medidaAtualizada.getCoxa());
                    m.setPanturrilha(medidaAtualizada.getPanturrilha());
                    return medidaRepository.save(m);
                })
                .orElseThrow(() -> new RuntimeException("Medida não encontrada para o id: " + id));
    }

    @Transactional
    public void deletar(UUID id) {
        medidaRepository.deleteById(id);
    }
}
