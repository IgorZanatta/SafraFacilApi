package com.SafraFacil.projeto.service;

import com.SafraFacil.projeto.dto.FazendaDTO;
import com.SafraFacil.projeto.entity.FazendaEntity;
import com.SafraFacil.projeto.entity.SafraEntity;
import com.SafraFacil.projeto.entity.UsuarioEntity;
import com.SafraFacil.projeto.repository.FazendaRepository;
import com.SafraFacil.projeto.repository.SafraRepository;
import com.SafraFacil.projeto.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FazendaService {

    @Autowired
    private FazendaRepository fazendaRepository;

    @Autowired
    private SafraRepository safraRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<FazendaDTO> listarTodas() {
        List<FazendaEntity> fazendas = fazendaRepository.findAll();
        return fazendas.stream().map(FazendaDTO::new).collect(Collectors.toList());
    }

    public void inserir(FazendaDTO fazenda) {
        FazendaEntity fazendaEntity = new FazendaEntity(fazenda);

        if (fazenda.getSafra() != null) {
            SafraEntity safraEntity = safraRepository.findById(fazenda.getSafra().getId()).orElse(null);
            fazendaEntity.setSafra(safraEntity);
        }

        if (fazenda.getUsuario() != null) {
            UsuarioEntity usuarioEntity = usuarioRepository.findById(fazenda.getUsuario().getId()).orElse(null);
            fazendaEntity.setUsuario(usuarioEntity);
        }

        fazendaRepository.save(fazendaEntity);
    }

    public FazendaDTO alterar(FazendaDTO fazenda) {
        FazendaEntity fazendaEntity = new FazendaEntity(fazenda);
        return new FazendaDTO(fazendaRepository.save(fazendaEntity));
    }

    public void excluir(Long id) {
        FazendaEntity fazenda = fazendaRepository.findById(id).orElseThrow();
        fazendaRepository.delete(fazenda);
    }

    public List<FazendaDTO> listarPorUsuario(Long usuarioId) {
        List<FazendaEntity> fazendas = fazendaRepository.findByUsuarioId(usuarioId);
        return fazendas.stream().map(FazendaDTO::new).collect(Collectors.toList());
    }

    public FazendaDTO buscarPorId(Long id) {
        return new FazendaDTO(fazendaRepository.findById(id).orElseThrow());
    }
}
