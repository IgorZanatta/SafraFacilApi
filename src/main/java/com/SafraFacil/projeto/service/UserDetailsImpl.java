package com.SafraFacil.projeto.service;

import java.util.ArrayList;
import java.util.Collection;

import com.SafraFacil.projeto.entity.UsuarioEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserDetailsImpl implements UserDetails {

    private Long id;

    private String name;

    private String username;

    private String password;

    private String telefone;

    public UserDetailsImpl(Long id, String name, String username, String password, String telefone,
                           Collection<? extends GrantedAuthority> authorities) {
        super();
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.telefone = telefone;
        this.authorities = authorities;
    }

    public static UserDetailsImpl build(UsuarioEntity usuario) {
        return new UserDetailsImpl(
                usuario.getId(),
                usuario.getNome(),
                usuario.getLogin(),
                usuario.getSenha(),
                usuario.getTelefone(),
                new ArrayList<>());
    }

    private Collection<? extends GrantedAuthority> authorities;

    public Long getId() {  // Adicione este método
        return id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
