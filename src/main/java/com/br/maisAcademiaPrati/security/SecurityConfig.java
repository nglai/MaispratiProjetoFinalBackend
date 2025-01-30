package com.br.maisAcademiaPrati.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable()) // Desabilita a proteção contra CSRF (não recomendada para produção sem análise).
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/**").permitAll() // Permite acesso às rotas iniciadas por "/auth/" sem autenticação.
                        .requestMatchers("/aluno/**").permitAll()
                        .requestMatchers("/funcionario/**").permitAll()
//                        .requestMatchers("/api/protected").hasAuthority("ROLE_USER") // Restringe acesso às rotas "/api/protected" para usuários com a role "ROLE_USER".
                        .anyRequest().authenticated() // Exige autenticação para todas as outras requisições.
                )
//                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class) // Adiciona o filtro de autenticação JWT antes do filtro padrão de autenticação.
                .build(); // Constrói e retorna a configuração de segurança.
    }
}
