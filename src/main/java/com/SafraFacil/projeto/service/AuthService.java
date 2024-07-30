package com.SafraFacil.projeto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.SafraFacil.projeto.dto.AcessDTO;
import com.SafraFacil.projeto.dto.AuthenticationDTO;
import com.SafraFacil.projeto.security.jwt.JwtUtils;

@Service
public class AuthService {

    @Autowired
    private AuthenticationManager authenticatioManager;

    @Autowired
    private JwtUtils jwtUtils;

    public AcessDTO login(AuthenticationDTO authDto) {
        try {
            // Cria mecanismo de credencial para o spring
            UsernamePasswordAuthenticationToken userAuth =
                    new UsernamePasswordAuthenticationToken(authDto.getUsername(), authDto.getPassword());

            // Prepara mecanismo para autenticação
            Authentication authentication = authenticatioManager.authenticate(userAuth);

            // Busca usuário logado
            UserDetailsImpl userAuthenticate = (UserDetailsImpl) authentication.getPrincipal();

            String token = jwtUtils.generateTokenFromUserDetailsImpl(userAuthenticate);

            AcessDTO accessDto = new AcessDTO(token, userAuthenticate.getId()); // Passa o userId aqui

            return accessDto;

        } catch (BadCredentialsException e) {
            // TODO LOGIN OU SENHA INVALIDO
        }
        return new AcessDTO("Acesso negado", null);
    }
}
