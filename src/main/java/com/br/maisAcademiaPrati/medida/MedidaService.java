package com.br.maisAcademiaPrati.medida;

import com.br.maisAcademiaPrati.medida.MedidaEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class MedidaService {

    private final MedidaRepository medidaRepository;

    public MedidaService(MedidaRepository medidaRepository) {
        this.medidaRepository = medidaRepository;
    }

    /**
     * Lista todas as medidas.
     */
    public List<MedidaEntity> listarTodas() {
        return medidaRepository.findAll();
    }

    /**
     * Busca uma medida pelo ID (Long).
     */
    public Optional<MedidaEntity> buscarPorId(Long id) {
        return medidaRepository.findById(id);
    }

    /**
     * Salva uma nova medida no banco.
     */
    @Transactional
    public MedidaEntity salvar(MedidaEntity medida) {
        return medidaRepository.save(medida);
    }

    /**
     * Atualiza uma medida existente (caso exista).
     */
    @Transactional
    public MedidaEntity atualizar(Long id, MedidaEntity medidaAtualizada) {
        return medidaRepository.findById(id)
                .map(m -> {
                    m.setData_medida(medidaAtualizada.getData_medida());
                    m.setPeso(medidaAtualizada.getPeso());
                    m.setBiceps(medidaAtualizada.getBiceps());
                    m.setTriceps(medidaAtualizada.getTriceps());
                    m.setAbdomen(medidaAtualizada.getAbdomen());
                    m.setCoxa(medidaAtualizada.getCoxa());
                    m.setPanturrilha(medidaAtualizada.getPanturrilha());
                    return medidaRepository.save(m);
                })
                .orElseThrow(() -> new RuntimeException("Medida não encontrada para o id: " + id));
    }

    /**
     * Deleta uma medida pelo ID (Long).
     */
    @Transactional
    public void deletar(Long id) {
        medidaRepository.deleteById(id);
    }
}
