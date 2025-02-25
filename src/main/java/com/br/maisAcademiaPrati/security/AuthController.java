package com.br.maisAcademiaPrati.security;

import com.br.maisAcademiaPrati.aluno.AlunoRepository;
import com.br.maisAcademiaPrati.funcionario.FuncionarioRepository;
import com.br.maisAcademiaPrati.pessoa.PessoaEntity;
import com.br.maisAcademiaPrati.refreshToken.RefreshToken;
import com.br.maisAcademiaPrati.refreshToken.RefreshTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private AlunoRepository alunoRepository;
    @Autowired
    private FuncionarioRepository funcionarioRepository;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private RefreshTokenService refreshTokenService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody PessoaEntity pessoa) {
        try {
            System.out.println("Tentando autenticar: " + pessoa.getEmail());
            System.out.println("Senha fornecida: " + pessoa.getSenha());

            // Realiza a autenticação utilizando o gerenciador de autenticação do Spring Security.
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(pessoa.getEmail(), pessoa.getSenha())
            );

            System.out.println("Usuário autenticado com sucesso: " + authentication.getName());

            // Gera um token JWT para o usuário autenticado.
            String accessToken = jwtUtil.generateToken(authentication.getName());
            RefreshToken refreshToken = refreshTokenService.createRefreshToken(authentication.getName());

            Map<String, String> tokens = new HashMap<>();
            tokens.put("accessToken", accessToken);
            tokens.put("refreshToken", refreshToken.getToken());

           return ResponseEntity.ok(tokens.toString());
        } catch (AuthenticationException e) {
            throw new AuthenticationException("Usuário ou senha Inválidos") {};
        }
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<String> refreshToken(@RequestBody RefreshTokenDTO refreshToken) {
        try {
            String userEmail = jwtUtil.getEmailFromToken(refreshToken.getRefreshToken());

            UserDetails userDetails = userDetailsService.loadUserByUsername(userEmail);

            if(jwtUtil.isTokenValid(refreshToken.getRefreshToken(), userDetails)) {
                String newAccessToken = jwtUtil.generateToken(userEmail);
                return ResponseEntity.ok(newAccessToken);
            } else {
                return ResponseEntity.status(401).body("Refresh Token Inválido!");
            }
        } catch(Exception error){
            return ResponseEntity.status(401).body("Erro ao processar o Refresh Token!");
        }
    }
}
