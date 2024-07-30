package com.SafraFacil.projeto.service;

import com.SafraFacil.projeto.dto.FazendaDTO;
import com.SafraFacil.projeto.dto.SafraDTO;
import com.SafraFacil.projeto.entity.FazendaEntity;
import com.SafraFacil.projeto.entity.SafraEntity;
import com.SafraFacil.projeto.repository.SafraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SafraService {

    @Autowired
    private SafraRepository safraRepository;

    public List<SafraDTO> listarTodos() {
        List<SafraEntity> safras = safraRepository.findAll();
        return safras.stream().map(SafraDTO::new).collect(Collectors.toList());
    }

    public void inserir(SafraDTO safra) {
        SafraEntity safraEntity = new SafraEntity(safra);
        safraRepository.save(safraEntity);
    }

    public SafraDTO alterar(SafraDTO safra) {
        SafraEntity safraEntity = new SafraEntity(safra);
        return new SafraDTO(safraRepository.save(safraEntity));
    }

    public void excluir(Long id) {
        safraRepository.deleteById(id);
    }

    public List<SafraDTO> listarPorUsuario(Long usuarioId) {
        List<SafraEntity> safras = safraRepository.findByUsuarioId(usuarioId);
        return safras.stream().map(SafraDTO::new).collect(Collectors.toList());
    }

    public SafraDTO buscarPorId(Long id) {
        return new SafraDTO(safraRepository.findById(id).orElseThrow());
    }
}
