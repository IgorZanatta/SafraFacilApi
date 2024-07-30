package com.SafraFacil.projeto.repository;

import com.SafraFacil.projeto.entity.FazendaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FazendaRepository extends JpaRepository<FazendaEntity, Long> {
    List<FazendaEntity> findByUsuarioId(Long usuarioId);
}
