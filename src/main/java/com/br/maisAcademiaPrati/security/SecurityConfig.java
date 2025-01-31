package com.br.maisAcademiaPrati.security;

import com.br.maisAcademiaPrati.aluno.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) throws Exception {
        // Configura um provedor de autenticação baseado no DAO.

        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService); // Define o serviço para carregar os usuários.
        provider.setPasswordEncoder(passwordEncoder); // Define o codificador de senha.

        return new ProviderManager(provider); // Retorna um ProviderManager com o provedor configurado.
    }

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
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class) // Adiciona o filtro de autenticação JWT antes do filtro padrão de autenticação.
                .build(); // Constrói e retorna a configuração de segurança.
    }
}
