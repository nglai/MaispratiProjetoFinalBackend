package com.br.maisAcademiaPrati.security;

import com.br.maisAcademiaPrati.aluno.AlunoRepository;
import com.br.maisAcademiaPrati.funcionario.FuncionarioRepository;
import com.br.maisAcademiaPrati.pessoa.PessoaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    JwtUtil jwtUtil;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    AlunoRepository alunoRepository;
    @Autowired
    FuncionarioRepository funcionarioRepository;

//    @PostMapping("/login")
//    public ResponseEntity<?> login(@RequestBody PessoaEntity pessoa){
//        try {
//            Optional<AlunoEntity> encontraAluno = alunoRepository.findByEmail(pessoa.getEmail());
//            if(encontraAluno.isPresent() && encontraAluno.get().getSenha().equals(pessoa.getSenha())){
//                String token = jwtUtil.generateToken(pessoa.getEmail());
//                return ResponseEntity.ok().body("AcessToken: " + token);
//            }
//
//            Optional<FuncionarioEntity> encontraFuncionario = funcionarioRepository.findByEmail(pessoa.getEmail());
//            if (encontraFuncionario.isPresent() && encontraFuncionario.get().getSenha().equals(pessoa.getSenha())) {
//                String token = jwtUtil.generateToken(pessoa.getEmail());
//                return ResponseEntity.ok().body("AcessToken: " + token);
//            }
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais Inválidas");
//        } catch (Exception e) {
//            e.printStackTrace(); // Ou use um logger mais robusto
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno do servidor");
//        }
//    }

    @PostMapping("/login")
    public String login(@RequestBody PessoaEntity pessoa) {
        try {
            System.out.println("Tentando autenticar: " + pessoa.getEmail());
            System.out.println("Senha fornecida: " + pessoa.getSenha());

            // Realiza a autenticação utilizando o gerenciador de autenticação do Spring Security.
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(pessoa.getEmail(), pessoa.getSenha())
            );

            return jwtUtil.generateToken(authentication.getName());
            // Exibe no console que o usuário foi autenticado com sucesso.
//            System.out.println("Usuário autenticado com sucesso: " + authentication.getName());

            // Gera um token JWT para o usuário autenticado.
//            String accessToken = jwtUtil.generateToken(authentication.getName());
//            RefreshToken refreshToken = refreshTokenService.createRefreshToken(authentication.getName());
//
//            Map<String, String> tokens = new HashMap<>();
//            tokens.put("accessToken", accessToken);
//            tokens.put("refreshToken", refreshToken.getToken());

            // Retorna o token no corpo da resposta.
//            return ResponseEntity.ok(tokens);

        } catch (AuthenticationException e) {
            throw new AuthenticationException("Usuário ou senha Inválidos") {};
        }
    }
}
