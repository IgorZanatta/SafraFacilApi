package com.SafraFacil.projeto.repository;

import com.SafraFacil.projeto.entity.SetorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SetorRepository extends JpaRepository<SetorEntity, Long> {
    List<SetorEntity> findByFazendaId(Long fazendaId);

}
