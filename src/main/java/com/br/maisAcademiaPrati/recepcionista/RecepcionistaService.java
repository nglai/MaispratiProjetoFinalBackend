package com.br.maisAcademiaPrati.recepcionista;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RecepcionistaService {

    @Autowired
    private RecepcionistaRepository recepcionistaRepository;

    public RecepcionistaEntity criarRecepcionista(RecepcionistaDTO recepcionistaDTO) {
        RecepcionistaEntity recepcionista = new RecepcionistaEntity();
        BeanUtils.copyProperties(recepcionistaDTO, recepcionista);
        return recepcionistaRepository.save(recepcionista);
    }

    public List<RecepcionistaEntity> listaTodosRecepcionistas() {
        return recepcionistaRepository.findAll();
    }

    public Optional<RecepcionistaEntity> buscaRecepcionistaPorId(UUID id) {
        return recepcionistaRepository.findById(id);
    }

    public RecepcionistaEntity atualizaRecepcionistaPorId(UUID id, RecepcionistaDTO recepcionistaDTO) {
        Optional<RecepcionistaEntity> recepcionistaEntity = recepcionistaRepository.findById(id);
        if (recepcionistaEntity.isPresent()) {
            RecepcionistaEntity recepcionista = recepcionistaEntity.get();
            BeanUtils.copyProperties(recepcionistaDTO, recepcionista);
            return recepcionistaRepository.save(recepcionista);
        } else {
            throw new RuntimeException("Recepcionista não encontrado.");
        }
    }
}
