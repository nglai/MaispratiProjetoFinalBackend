package com.br.maisAcademiaPrati;

import com.br.maisAcademiaPrati.refreshToken.RefreshToken;
import com.br.maisAcademiaPrati.refreshToken.RefreshTokenService;
import com.br.maisAcademiaPrati.security.AuthController;
import com.br.maisAcademiaPrati.security.JwtUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(AuthController.class)
@AutoConfigureMockMvc(addFilters = false) // Desabilita os filtros do Spring Security para os testes
public class AuthControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    // Mocks para as dependências injetadas no AuthController
    @MockBean
    private AuthenticationManager authenticationManager;

    @MockBean
    private JwtUtil jwtUtil;

    @MockBean
    private RefreshTokenService refreshTokenService;

    /**
     * Testa o endpoint de /auth/login.
     * Simula a autenticação e valida se o response contém os tokens esperados.
     */
    @Test
    public void testLogin() throws Exception {
        // Dados de teste
        String email = "test@example.com";
        String senha = "password";
        String dummyAccessToken = "dummyAccessToken";
        String dummyRefreshTokenValue = "dummyRefreshToken";

        // Cria um objeto dummy RefreshToken
        RefreshToken dummyRefreshToken = new RefreshToken();
        dummyRefreshToken.setToken(dummyRefreshTokenValue);
        dummyRefreshToken.setUsername(email);

        // Configura o comportamento do AuthenticationManager
        Authentication authentication = Mockito.mock(Authentication.class);
        Mockito.when(authentication.getName()).thenReturn(email);
        Mockito.when(authenticationManager.authenticate(
                        Mockito.any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(authentication);

        // Configura os mocks para geração de tokens
        Mockito.when(jwtUtil.generateToken(email)).thenReturn(dummyAccessToken);
        Mockito.when(refreshTokenService.createRefreshToken(email)).thenReturn(dummyRefreshToken);

        // JSON de entrada para o login
        String loginPayload = "{\"email\": \"" + email + "\", \"senha\": \"" + senha + "\"}";

        // Realiza a requisição e verifica os resultados
        mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(loginPayload))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accessToken").value(dummyAccessToken))
                .andExpect(jsonPath("$.refreshToken").value(dummyRefreshTokenValue));
    }

    /**
     * Testa o endpoint de /auth/refresh-token.
     * Simula a renovação do access token usando o refresh token.
     */
    @Test
    public void testRefreshToken() throws Exception {
        // Dados de teste
        String email = "test@example.com";
        String newAccessToken = "newDummyAccessToken";
        String dummyRefreshTokenValue = "dummyRefreshToken";

        // Cria e configura um objeto RefreshToken
        RefreshToken dummyRefreshToken = new RefreshToken();
        dummyRefreshToken.setToken(dummyRefreshTokenValue);
        dummyRefreshToken.setUsername(email);

        // Configura os mocks para o fluxo de refresh token
        Mockito.when(refreshTokenService.findByToken(dummyRefreshTokenValue))
                .thenReturn(Optional.of(dummyRefreshToken));
        // Supondo que o método verifyExpiration retorne o próprio token se não estiver expirado
        Mockito.when(refreshTokenService.verifyExpiration(dummyRefreshToken))
                .thenReturn(dummyRefreshToken);
        Mockito.when(jwtUtil.generateToken(email)).thenReturn(newAccessToken);

        // JSON de entrada para o refresh token
        String refreshPayload = "{\"refreshToken\": \"" + dummyRefreshTokenValue + "\"}";

        // Realiza a requisição e valida a resposta
        mockMvc.perform(post("/auth/refresh-token")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(refreshPayload))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accessToken").value(newAccessToken));
    }
}
