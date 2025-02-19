package com.br.maisAcademiaPratiService;

import com.br.maisAcademiaPratiModel.Avaliacao;
import com.br.AcademiaPratiRepository.AvaliacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AvaliacaoService {

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    public List<Avaliacao> listarTodos() {
        return avaliacaoRepository.findAll();
    }

    public Avaliacao buscarPorId(Long id) {
        return avaliacaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Avaliação não encontrada com id " + id));
    }

    public Avaliacao salvar(Avaliacao avaliacao) {
        return avaliacaoRepository.save(avaliacao);
    }

    public Avaliacao atualizar(Long id, Avaliacao avaliacaoAtualizada) {
        Avaliacao avaliacaoExistente = buscarPorId(id);
        avaliacaoExistente.setDataAvaliacao(avaliacaoAtualizada.getDataAvaliacao());
        avaliacaoExistente.setNota(avaliacaoAtualizada.getNota());
        avaliacaoExistente.setComentario(avaliacaoAtualizada.getComentario());
        avaliacaoExistente.setAluno(avaliacaoAtualizada.getAluno());
        return avaliacaoRepository.save(avaliacaoExistente);
    }

    public void deletar(Long id) {
        Avaliacao avaliacao = buscarPorId(id);
        avaliacaoRepository.delete(avaliacao);
    }
}

