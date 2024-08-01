package com.SafraFacil.projeto.service;

import com.SafraFacil.projeto.dto.SafraDTO;
import com.SafraFacil.projeto.dto.SetorDTO;
import com.SafraFacil.projeto.dto.TipoDTO;
import com.SafraFacil.projeto.entity.SafraEntity;
import com.SafraFacil.projeto.entity.SetorEntity;
import com.SafraFacil.projeto.entity.TipoEntity;
import com.SafraFacil.projeto.repository.TipoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TipoService {

    @Autowired
    private TipoRepository tipoRepository;

    @Transactional(readOnly = true)
    public List<TipoDTO> listarTodos() {
        List<TipoEntity> tipos = tipoRepository.findAll();
        return tipos.stream().map(TipoDTO::new).collect(Collectors.toList());
    }

    @Transactional
    public void inserir(TipoDTO tipoDTO) {
        TipoEntity tipoEntity = new TipoEntity(tipoDTO);
        tipoRepository.save(tipoEntity);
    }

    @Transactional(readOnly = true)
    public List<TipoDTO> listarPorSetor(Long setorId) {
        List<TipoEntity> tipos = tipoRepository.findBySetorId(setorId);
        return tipos.stream().map(TipoDTO::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<TipoDTO> listarPorData(Date data) { // Novo método para listar por data
        List<TipoEntity> tipos = tipoRepository.findByData(data);
        return tipos.stream().map(TipoDTO::new).collect(Collectors.toList());
    }

    @Transactional
    public TipoDTO alterar(TipoDTO tipoDTO) {
        TipoEntity tipoEntity = tipoRepository.findById(tipoDTO.getId())
                .orElseThrow(() -> new RuntimeException("Tipo não encontrado"));

        tipoEntity.setTipo_atividade(tipoDTO.getTipo_atividade());
        tipoEntity.setData(tipoDTO.getData());
        tipoEntity.setGasto(tipoDTO.getGasto());
        tipoEntity.setLucro(tipoDTO.getLucro());
        tipoEntity.setObservacao(tipoDTO.getObservacao());
        tipoEntity.setAnexos(tipoDTO.getAnexos());

        if (tipoDTO.getSetor() != null) {
            SetorEntity setorEntity = convertSetorDTOToEntity(tipoDTO.getSetor());
            tipoEntity.setSetor(setorEntity);
        }

        return new TipoDTO(tipoRepository.save(tipoEntity));
    }

    @Transactional
    public void excluir(Long id) {
        TipoEntity tipo = tipoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tipo não encontrado"));
        tipoRepository.delete(tipo);
    }

    @Transactional(readOnly = true)
    public TipoDTO buscarPorId(Long id) {
        TipoEntity tipoEntity = tipoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tipo não encontrado"));
        return new TipoDTO(tipoEntity);
    }

    @Transactional(readOnly = true)
    public List<TipoDTO> listarPorUsuario(Long usuarioId) { // Certifique-se de que este método esteja correto
        List<TipoEntity> tipos = tipoRepository.findByUsuarioId(usuarioId);
        return tipos.stream().map(TipoDTO::new).collect(Collectors.toList());
    }

    private SetorEntity convertSetorDTOToEntity(SetorDTO setorDTO) {
        SetorEntity setorEntity = new SetorEntity();
        setorEntity.setId(setorDTO.getId());
        setorEntity.setNome(setorDTO.getNome());
        setorEntity.setTipo_setor(setorDTO.getTipo_setor());
        setorEntity.setTamanho(setorDTO.getTamanho());

        return setorEntity;
    }
}
