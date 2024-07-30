package com.SafraFacil.projeto.repository;

import com.SafraFacil.projeto.entity.FazendaEntity;
import com.SafraFacil.projeto.entity.SafraEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SafraRepository extends JpaRepository<SafraEntity, Long> {
    List<SafraEntity> findByUsuarioId(Long usuarioId);

}
