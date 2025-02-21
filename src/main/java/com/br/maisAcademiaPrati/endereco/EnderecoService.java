package com.br.maisAcademiaPrati.endereco;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;

    public EnderecoService(EnderecoRepository enderecoRepository) {
        this.enderecoRepository = enderecoRepository;
    }

    /**
     * Retorna todos os endereços cadastrados.
     */
    public List<EnderecoEntity> listarTodos() {
        return enderecoRepository.findAll();
    }

    /**
     * Busca um endereço pelo ID (UUID).
     */
    public Optional<EnderecoEntity> buscarPorId(UUID id) {
        return enderecoRepository.findById(id);
    }

    /**
     * Cria um novo endereço no banco de dados a partir do DTO.
     */
    @Transactional
    public EnderecoEntity salvar(EnderecoDTO dto) {
        EnderecoEntity novoEndereco = fromDTO(dto);
        return enderecoRepository.save(novoEndereco);
    }

    /**
     * Atualiza um endereço existente, caso encontrado.
     */
    @Transactional
    public EnderecoEntity atualizar(UUID id, EnderecoDTO dto) {
        Optional<EnderecoEntity> optEndereco = enderecoRepository.findById(id);
        if (optEndereco.isPresent()) {
            EnderecoEntity enderecoExistente = optEndereco.get();
            enderecoExistente.setRua(dto.rua());
            enderecoExistente.setBairro(dto.bairro());
            enderecoExistente.setCep(dto.cep());
            enderecoExistente.setComplemento(dto.complemento());
            return enderecoRepository.save(enderecoExistente);
        }
        throw new RuntimeException("Endereço não encontrado para o id: " + id);
    }

    /**
     * Exclui um endereço pelo ID (UUID).
     */
    @Transactional
    public void deletar(UUID id) {
        enderecoRepository.deleteById(id);
    }

    /**
     * Converte um EnderecoDTO em EnderecoEntity.
     */
    private EnderecoEntity fromDTO(EnderecoDTO dto) {
        EnderecoEntity entity = new EnderecoEntity();
        entity.setRua(dto.rua());
        entity.setBairro(dto.bairro());
        entity.setCep(dto.cep());
        entity.setComplemento(dto.complemento());
        return entity;
    }
}
