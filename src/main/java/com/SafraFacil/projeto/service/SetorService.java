package com.SafraFacil.projeto.service;

import com.SafraFacil.projeto.dto.SetorDTO;
import com.SafraFacil.projeto.entity.SetorEntity;
import com.SafraFacil.projeto.repository.SetorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SetorService {

    @Autowired
    private SetorRepository setorRepository;

    public List<SetorDTO> listarTodos() {
        List<SetorEntity> setores = setorRepository.findAll();
        return setores.stream().map(SetorDTO::new).collect(Collectors.toList());
    }

    public void inserir(SetorDTO setor) {
        SetorEntity setorEntity = new SetorEntity(setor);
        setorRepository.save(setorEntity);
    }

    public SetorDTO alterar(SetorDTO setor) {
        SetorEntity setorEntity = new SetorEntity(setor);
        return new SetorDTO(setorRepository.save(setorEntity));
    }

    public void excluir(Long id) {
        setorRepository.deleteById(id);
    }

    public SetorDTO buscarPorId(Long id) {
        return new SetorDTO(setorRepository.findById(id).orElseThrow());
    }

    public List<SetorDTO> listarPorFazenda(Long fazendaId) {
        List<SetorEntity> setores = setorRepository.findByFazendaId(fazendaId);
        return setores.stream().map(SetorDTO::new).collect(Collectors.toList());
    }

    public List<SetorDTO> listarPorUsuario(Long usuarioId) {
        List<SetorEntity> setores = setorRepository.findByUsuarioId(usuarioId);
        return setores.stream().map(SetorDTO::new).collect(Collectors.toList());
    }
}
