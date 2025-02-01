package com.br.maisAcademiaPrati.security;

import com.br.maisAcademiaPrati.aluno.AlunoEntity;
import com.br.maisAcademiaPrati.aluno.AlunoRepository;
import com.br.maisAcademiaPrati.funcionario.FuncionarioEntity;
import com.br.maisAcademiaPrati.funcionario.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private AlunoRepository alunoRepository;
    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<AlunoEntity> aluno = alunoRepository.findByEmail(username);
        if(aluno.isPresent()){
            return new CustomUserDetails(
                    aluno.get().getEmail(),
                    aluno.get().getSenha(),
                    List.of(new SimpleGrantedAuthority("ROLE_ALUNO"))
            ); // Lista de permissões/roles (neste caso, apenas "ROLE_ALUNO").
        }

        Optional<FuncionarioEntity> funcionario = funcionarioRepository.findByEmail(username);
        if (funcionario.isPresent()) {
            return new CustomUserDetails(
                    funcionario.get().getEmail(),
                    funcionario.get().getSenha(),
                    List.of(new SimpleGrantedAuthority("ROLE_FUNCIONARIO"))
                    ); // Lista de permissões/roles (neste caso, apenas "ROLE_FUNCIONARIO").
        }

        return null;
    }
}
