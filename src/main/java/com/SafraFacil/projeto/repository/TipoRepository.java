package com.SafraFacil.projeto.repository;

import com.SafraFacil.projeto.entity.TipoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface TipoRepository extends JpaRepository<TipoEntity, Long> {
    List<TipoEntity> findBySetorId(Long setorId);
    List<TipoEntity> findByData(Date data);
}
