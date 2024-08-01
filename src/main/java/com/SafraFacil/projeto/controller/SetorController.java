package com.SafraFacil.projeto.controller;

import com.SafraFacil.projeto.dto.SafraDTO;
import com.SafraFacil.projeto.dto.SetorDTO;
import com.SafraFacil.projeto.service.SetorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/setor")
@CrossOrigin
public class SetorController {

    @Autowired
    private SetorService setorService;

    @GetMapping
    public List<SetorDTO> listarTodos() {
        return setorService.listarTodos();
    }

    @PostMapping
    public void inserir(@RequestBody SetorDTO setor) {
        setorService.inserir(setor);
    }

    @PutMapping
    public SetorDTO alterar(@RequestBody SetorDTO setor) {
        return setorService.alterar(setor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable("id") Long id) {
        setorService.excluir(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/fazenda/{fazendaId}")
    public ResponseEntity<List<SetorDTO>> listarPorFazenda(@PathVariable Long fazendaId) {
        return ResponseEntity.ok(setorService.listarPorFazenda(fazendaId));
    }

    @GetMapping("/usuario/{usuarioId}")
    public List<SetorDTO> listarPorUsuario(@PathVariable Long usuarioId) {
        return setorService.listarPorUsuario(usuarioId);
    }

}
