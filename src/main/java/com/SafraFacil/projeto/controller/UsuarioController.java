package com.SafraFacil.projeto.controller;

import com.SafraFacil.projeto.dto.UsuarioDTO;
import com.SafraFacil.projeto.entity.enums.TipoSituacaoUsuario;
import com.SafraFacil.projeto.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/usuario")
@CrossOrigin
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public List<UsuarioDTO> listarTodos(){
        return usuarioService.listarTodos();
    }

    @PostMapping
    public void inserir(@RequestBody UsuarioDTO usuario){
        usuarioService.inserir(usuario);

    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> buscarPorId(@PathVariable Long id) {
        UsuarioDTO usuario = usuarioService.buscarPorId(id);
        return ResponseEntity.ok().body(usuario);
    }

    @PutMapping("/campos")
    public UsuarioDTO alterarCampos(@RequestBody UsuarioDTO usuario) {
        return usuarioService.alterarCampos(usuario);
    }

    @PutMapping("/senha/{id}")
    public UsuarioDTO alterarSenha(@PathVariable Long id, @RequestBody Map<String, String> novaSenhaMap) {
        String novaSenha = novaSenhaMap.get("senha");
        System.out.println("Senha recebida no controlador: " + novaSenha);
        return usuarioService.alterarSenha(id, novaSenha);
    }

    @PutMapping
    public UsuarioDTO alterar(@RequestBody UsuarioDTO usuario){
        return usuarioService.alterar(usuario);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable("id") Long id){
        usuarioService.excluir(id);
        return ResponseEntity.ok().build();
    }

}
