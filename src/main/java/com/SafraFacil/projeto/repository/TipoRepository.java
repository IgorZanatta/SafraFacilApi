package com.SafraFacil.projeto.repository;

import com.SafraFacil.projeto.entity.TipoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface TipoRepository extends JpaRepository<TipoEntity, Long> {
    List<TipoEntity> findBySetorId(Long setorId);
    List<TipoEntity> findByData(Date data);
    @Query("SELECT t FROM TipoEntity t WHERE t.setor.fazenda.usuario.id = :usuarioId")
    List<TipoEntity> findByUsuarioId(@Param("usuarioId") Long usuarioId);
}