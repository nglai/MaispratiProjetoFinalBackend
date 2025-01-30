package com.br.maisAcademiaPrati.autenticacao;

import com.br.maisAcademiaPrati.aluno.AlunoEntity;
import com.br.maisAcademiaPrati.aluno.AlunoRepository;
import com.br.maisAcademiaPrati.funcionario.FuncionarioEntity;
import com.br.maisAcademiaPrati.funcionario.FuncionarioRepository;
import com.br.maisAcademiaPrati.pessoa.PessoaEntity;
import com.br.maisAcademiaPrati.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    AlunoRepository alunoRepository;

    @Autowired
    FuncionarioRepository funcionarioRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody PessoaEntity pessoa){
        try {
            Optional<AlunoEntity> encontraAluno = alunoRepository.findByEmail(pessoa.getEmail());
            if(encontraAluno.isPresent() && encontraAluno.get().getSenha().equals(pessoa.getSenha())){
                String token = jwtUtil.generateToken(pessoa.getEmail());
                return ResponseEntity.ok().body("AcessToken: " + token);
            }

            Optional<FuncionarioEntity> encontraFuncionario = funcionarioRepository.findByEmail(pessoa.getEmail());
            if (encontraFuncionario.isPresent() && encontraFuncionario.get().getSenha().equals(pessoa.getSenha())) {
                String token = jwtUtil.generateToken(pessoa.getEmail());
                return ResponseEntity.ok().body("AcessToken: " + token);
            }
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais Inválidas");
        } catch (Exception e) {
            e.printStackTrace(); // Ou use um logger mais robusto
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno do servidor");
        }
    }
}
