package com.br.maisAcademiaPrati.professor;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProfessorService {

    @Autowired
    private ProfessorRepository professorRepository;

    public ProfessorEntity criarProfessor(ProfessorDTO professorDTO) {
        ProfessorEntity professor = new ProfessorEntity();
        BeanUtils.copyProperties(professorDTO, professor);
        return professorRepository.save(professor);
    }

    public List<ProfessorEntity> listaTodosProfessores() {
        return professorRepository.findAll();
    }

    public Optional<ProfessorEntity> buscaProfessorPorId(UUID id) {
        return professorRepository.findById(id);
    }

    public ProfessorEntity atualizaProfessorPorId(UUID id, ProfessorDTO professorDTO) {
        Optional<ProfessorEntity> professorEntity = professorRepository.findById(id);
        if (professorEntity.isPresent()) {
            ProfessorEntity professor = professorEntity.get();
            BeanUtils.copyProperties(professorDTO, professor);
            return professorRepository.save(professor);
        } else {
            throw new RuntimeException("Professor não encontrado.");
        }
    }
}
