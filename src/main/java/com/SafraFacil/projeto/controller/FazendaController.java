package com.SafraFacil.projeto.controller;

import com.SafraFacil.projeto.dto.FazendaDTO;
import com.SafraFacil.projeto.service.FazendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/fazenda")
@CrossOrigin
public class FazendaController {

    @Autowired
    private FazendaService fazendaService;

    @GetMapping
    public List<FazendaDTO> listarTodos() {
        return fazendaService.listarTodas();
    }

    @PostMapping
    public void inserir(@RequestBody FazendaDTO fazenda) {
        fazendaService.inserir(fazenda);
    }

    @PutMapping
    public FazendaDTO alterar(@RequestBody FazendaDTO fazenda) {
        return fazendaService.alterar(fazenda);
    }

    @GetMapping("/{usuarioId}")
    public List<FazendaDTO> listarPorUsuario(@PathVariable Long usuarioId) {
        return fazendaService.listarPorUsuario(usuarioId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable("id") Long id) {
        fazendaService.excluir(id);
        return ResponseEntity.ok().build();
    }
}
