package com.br.maisAcademiaPrati.security;

import com.br.maisAcademiaPrati.util.LoginRateLimiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;
    @Autowired
    private LoginRateLimiter loginRateLimiter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        // Retorna uma instância de Argon2PasswordEncoder com os parâmetros:
        // - Salt de 16 bytes, hash de 32 bytes, 1 iteração, 65536 KB de memória e 3 threads.
        return new Argon2PasswordEncoder(16,32,1,65536,3);
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
                        .requestMatchers("/medida/**").permitAll()
                        .requestMatchers("/exercicio/**").permitAll()
//                        .requestMatchers("/funcionario/**").hasAuthority("ROLE_FUNCIONARIO") // Restringe acesso às rotas "/api/funcionario" para usuários com a role "ROLE_FUNCIONARIO".
                        .anyRequest().authenticated() // Exige autenticação para todas as outras requisições.
                )
                // Adiciona um filtro de limitação de taxa de login antes do filtro de autenticação JWT.
//                .addFilterBefore(loginRateLimiter, JwtAuthenticationFilter.class)
                // Adiciona o filtro JWT antes do filtro padrão de autenticação por email e senha
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build(); // Constrói e retorna a configuração de segurança.
    }
}
