package com.br.maisAcademiaPrati.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
// Implementa a interface UserDetails, que é usada pelo Spring Security para autenticação e autorização.
public class CustomUserDetails implements UserDetails {

    private final String email;
    private final String senha;

    private final Collection<? extends GrantedAuthority> authorities; // Lista de permissões ou roles.

    public CustomUserDetails(String email, String senha, Collection<? extends GrantedAuthority> authorities) {
        this.email = email;
        this.senha = senha;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // Indica se a conta do usuário está bloqueada.
    @Override
    public boolean isAccountNonLocked() {
        return true; // Retorna `true`, indicando que a conta não está bloqueada.
    }

    // Indica se as credenciais do usuário (ex.: senha) estão expiradas.
    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Retorna `true`, indicando que as credenciais não estão expiradas.
    }

    // Indica se o usuário está habilitado.
    @Override
    public boolean isEnabled() {
        return true; // Retorna `true`, indicando que o usuário está habilitado.
    }
}
