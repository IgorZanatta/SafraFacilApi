package com.SafraFacil.projeto.controller;

import com.SafraFacil.projeto.dto.SafraDTO;
import com.SafraFacil.projeto.service.SafraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/safra")
@CrossOrigin
public class SafraController {

    @Autowired
    private SafraService safraService;

    @GetMapping
    public List<SafraDTO> listarTodos() {
        return safraService.listarTodos();
    }

    @PostMapping
    public void inserir(@RequestBody SafraDTO safra) {
        safraService.inserir(safra);
    }

    @PutMapping
    public SafraDTO alterar(@RequestBody SafraDTO safra) {
        return safraService.alterar(safra);
    }

    @GetMapping("/usuario/{usuarioId}")
    public List<SafraDTO> listarPorUsuario(@PathVariable Long usuarioId) {
        return safraService.listarPorUsuario(usuarioId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable("id") Long id) {
        safraService.excluir(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SafraDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(safraService.buscarPorId(id));
    }
}
