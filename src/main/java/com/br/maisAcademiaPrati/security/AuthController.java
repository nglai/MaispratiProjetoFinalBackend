package com.br.maisAcademiaPrati.security;

import com.br.maisAcademiaPrati.pessoa.PessoaEntity;
import com.br.maisAcademiaPrati.refreshToken.RefreshToken;
import com.br.maisAcademiaPrati.refreshToken.RefreshTokenService;
import com.br.maisAcademiaPrati.refreshToken.TokenRefreshRequest;
import com.br.maisAcademiaPrati.refreshToken.TokenRefreshResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final RefreshTokenService refreshTokenService;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil, RefreshTokenService refreshTokenService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.refreshTokenService = refreshTokenService;
    }

    /**
     * Endpoint de login: autentica o usuário e retorna os tokens.
     */
    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody PessoaEntity pessoa) {
        try {
            System.out.println("Tentando autenticar: " + pessoa.getEmail());
            System.out.println("Senha fornecida: " + pessoa.getSenha());

            // Autentica o usuário
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(pessoa.getEmail(), pessoa.getSenha())
            );

            // Gera o access token e cria um novo refresh token
            String accessToken = jwtUtil.generateToken(authentication.getName());
            RefreshToken refreshToken = refreshTokenService.createRefreshToken(authentication.getName());

            // Prepara o retorno com os dois tokens
            Map<String, String> tokens = new HashMap<>();
            tokens.put("accessToken", accessToken);
            tokens.put("refreshToken", refreshToken.getToken());

            return ResponseEntity.ok(tokens);
        } catch (AuthenticationException e) {
            throw new RuntimeException("Usuário ou senha inválidos", e);
        }
    }

    /**
     * Endpoint para refresh do access token.
     * Recebe o refresh token e, se válido, retorna um novo access token.
     */
    @PostMapping("/refresh-token")
    public ResponseEntity<TokenRefreshResponse> refreshToken(@RequestBody TokenRefreshRequest request) {
        String requestRefreshToken = request.getRefreshToken();

        return refreshTokenService.findByToken(requestRefreshToken)
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUsername)
                .map(username -> {
                    String newAccessToken = jwtUtil.generateToken(username);
                    return ResponseEntity.ok(new TokenRefreshResponse(newAccessToken, requestRefreshToken));
                })
                .orElseThrow(() -> new RuntimeException("Refresh token não encontrado no banco de dados!"));
    }
}


