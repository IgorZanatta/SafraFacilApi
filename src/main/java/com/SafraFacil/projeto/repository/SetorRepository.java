package com.SafraFacil.projeto.repository;

import com.SafraFacil.projeto.entity.SetorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SetorRepository extends JpaRepository<SetorEntity, Long> {
    List<SetorEntity> findByFazendaId(Long fazendaId);
    @Query("SELECT s FROM SetorEntity s WHERE s.fazenda.usuario.id = :usuarioId")
    List<SetorEntity> findByUsuarioId(@Param("usuarioId") Long usuarioId);

}
