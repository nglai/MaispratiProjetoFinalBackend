package com.br.maisAcademiaPrati.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserDetailsService userDetailsService;

    // Método principal do filtro que intercepta todas as requisições HTTP.
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // Obtém o cabeçalho Authorization da requisição.
        String authHeader = request.getHeader("Authorization");

        // Verifica se o cabeçalho Authorization não é nulo e começa com "Bearer ".
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            // Remove o prefixo "Bearer " do token e remove espaços em branco ao redor, se existirem.
            String jwt = authHeader.substring(7).trim();
            System.out.println("Token recebido (após remover Bearer): " + jwt);

            try {
                // Valida o formato do token utilizando uma expressão regular.
                if (!jwt.matches("^[A-Za-z0-9-_\\.]+\\.[A-Za-z0-9-_\\.]+\\.[A-Za-z0-9-_\\.]+$")) {
                    throw new IllegalArgumentException("Token JWT malformatado: " + jwt);
                }

                // Extrai o email de usuário do token.
                String useremail = jwtUtil.getEmailFromToken(jwt);
                System.out.println("Email extraído do token: " + useremail);

                // Verifica se o email de usuário foi extraído e se não há autenticação no contexto de segurança atual.
                if (useremail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    // Carrega os detalhes do usuário com base no email de usuário extraído.
                    UserDetails userDetails = userDetailsService.loadUserByUsername(useremail);

                    // Valida o token usando a classe JwtUtil.
                    if (jwtUtil.isTokenValid(jwt, userDetails)) {
                        // Cria um token de autenticação com os detalhes do usuário e suas permissões.
                        UsernamePasswordAuthenticationToken authentication =
                                new UsernamePasswordAuthenticationToken(
                                        userDetails, null, userDetails.getAuthorities());
                        // Define a autenticação no contexto de segurança.
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    }
                }
            } catch (Exception e) {
                System.out.println("Erro ao processar o token JWT: " + e.getMessage());
            }
        } else {
            if (authHeader == null) {
                System.out.println("Cabeçalho Authorization não está presente.");
            } else {
                System.out.println("Cabeçalho Authorization não começa com Bearer.");
            }
        }
        filterChain.doFilter(request, response);
    }

}
