package com.br.maisAcademiaPratiService;

import com.br.maisAcademiaPratiModel.Ficha;
import com.br.maisAcademiaPratiRepository.FichaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FichaService {

    @Autowired
    private FichaRepository fichaRepository;

    public List<Ficha> listarTodos() {
        return fichaRepository.findAll();
    }

    public Ficha buscarPorId(Long id) {
        return fichaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ficha não encontrada com id " + id));
    }

    public Ficha salvar(Ficha ficha) {
        return fichaRepository.save(ficha);
    }

    public Ficha atualizar(Long id, Ficha fichaAtualizada) {
        Ficha fichaExistente = buscarPorId(id);
        fichaExistente.setDescricao(fichaAtualizada.getDescricao());
        fichaExistente.setDataCriacao(fichaAtualizada.getDataCriacao());
        fichaExistente.setAluno(fichaAtualizada.getAluno());
        return fichaRepository.save(fichaExistente);
    }

    public void deletar(Long id) {
        Ficha ficha = buscarPorId(id);
        fichaRepository.delete(ficha);
    }
}
