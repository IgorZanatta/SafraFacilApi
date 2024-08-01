package com.SafraFacil.projeto.controller;

import com.SafraFacil.projeto.dto.AuthenticationDTO;
import com.SafraFacil.projeto.dto.AcessDTO;
import com.SafraFacil.projeto.dto.UsuarioDTO;
import com.SafraFacil.projeto.service.AuthService;
import com.SafraFacil.projeto.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping(value = "/login")
    public ResponseEntity<AcessDTO> login(@RequestBody AuthenticationDTO authDto) {
        return ResponseEntity.ok(authService.login(authDto));
    }

    @PostMapping(value = "/novoUsuario")
    public void inserirNovoUsuario(@RequestBody UsuarioDTO novoUsuario) {
        usuarioService.inserirNovoUsuario(novoUsuario);
    }

    @GetMapping("/verificarCadastro/{uuid}")
    public RedirectView verificarCadastro(@PathVariable("uuid") String uuid) {
        String resultado = usuarioService.verificarCadastro(uuid);
        if ("Usuário Verificado".equals(resultado)) {
            return new RedirectView("/verificacaoSucesso");
        } else {
            return new RedirectView("/verificacaoFalha");
        }
    }
    @PostMapping("/esqueciSenha")
    public ResponseEntity<String> esqueciSenha(@RequestBody Map<String, String> payload) {
        String login = payload.get("login");
        usuarioService.solicitarRedefinicaoSenha(login);
        return ResponseEntity.ok("Código de verificação enviado");
    }

    @PostMapping("/verificarCodigo")
    public ResponseEntity<Boolean> verificarCodigo(@RequestBody Map<String, String> payload) {
        String login = payload.get("login");
        int codigo = Integer.parseInt(payload.get("codigoVerificacao"));
        boolean isValid = usuarioService.verificarCodigo(login, codigo);
        return ResponseEntity.ok(isValid);
    }

    @PostMapping("/redefinirSenha")
    public ResponseEntity<String> redefinirSenha(@RequestBody Map<String, String> payload) {
        String login = payload.get("login");
        String novaSenha = payload.get("novaSenha");
        usuarioService.redefinirSenha(login, novaSenha);
        return ResponseEntity.ok("Senha redefinida com sucesso");
    }

    @PostMapping("/renew-token")
    public ResponseEntity<AcessDTO> renewToken(@RequestHeader("Authorization") String token) {
        String jwt = token.substring(7); // Remove "Bearer " prefix
        return ResponseEntity.ok(authService.renewToken(jwt));
    }
}