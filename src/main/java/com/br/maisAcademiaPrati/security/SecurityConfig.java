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
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

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
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))  // Configura o CORS utilizando a fonte de configuração definida no método corsConfigurationSource()
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/**").permitAll()// Permite acesso às rotas iniciadas por "/auth/" sem autenticação.
                        .requestMatchers("/administrador").permitAll()
                        .requestMatchers("/aluno/**").hasAnyAuthority("ROLE_ALUNO", "ROLE_PROFESSOR", "ROLE_RECEPCIONISTA")
                        .requestMatchers("/funcionario/**").hasAuthority("ROLE_ADMINISTRADOR")
                        .requestMatchers("/medida/**").hasAuthority("ROLE_ALUNO")
                        .requestMatchers("/exercicio/**").hasAnyAuthority("ROLE_ALUNO", "ROLE_PROFESSOR")//professor
                        .requestMatchers("/funcionario/**").hasAuthority("ROLE_ADMINISTRADOR") // Restringe acesso às rotas "/api/funcionario" para usuários com a role "ROLE_FUNCIONARIO".
                        .anyRequest().authenticated() // Exige autenticação para todas as outras requisições.
                )
                .sessionManagement(session -> session.sessionCreationPolicy(
                        org.springframework.security.config.http.SessionCreationPolicy.STATELESS
                )) // Configura o gerenciamento de sessão para ser stateless, ou seja, sem armazenar estado de sessão no servidor
//                .addFilterBefore(loginRateLimiter, JwtAuthenticationFilter.class) // Adiciona um filtro de limitação de taxa de login antes do filtro de autenticação JWT.
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class) // Adiciona o filtro JWT antes do filtro padrão de autenticação por email e senha
                .build(); // Constrói e retorna a configuração de segurança.
    }

    @Bean // Define que o método abaixo retornará um bean gerenciado pelo Spring
    public CorsConfigurationSource corsConfigurationSource() { // Método que configura e retorna as definições de CORS
        CorsConfiguration configuration = new CorsConfiguration(); // Cria uma nova instância de CorsConfiguration para definir as configurações de CORS
        configuration.addAllowedOrigin("http://localhost:5173"); // Permite requisições de origem "http://localhost:5173"
        configuration.addAllowedMethod("*"); // Permite todos os métodos HTTP (GET, POST, PUT, DELETE, etc.)
        configuration.addAllowedHeader("*"); // Permite todos os cabeçalhos HTTP
        configuration.setAllowCredentials(true); // Permite o envio de credenciais (cookies, cabeçalhos de autorização, etc.) nas requisições

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource(); // Cria uma nova instância para mapear as configurações de CORS com base em URLs
        source.registerCorsConfiguration("/**", configuration); // Registra as configurações de CORS para todas as rotas da aplicação
        return source; // Retorna a fonte de configuração CORS
    }
}
