package com.SafraFacil.projeto.entity;

import com.SafraFacil.projeto.dto.SafraDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Entity
@Table(name = "SF_SAFRA")
@Getter
@Setter
@NoArgsConstructor
public class SafraEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String qual_safra;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private UsuarioEntity usuario;

    public SafraEntity(SafraDTO safra) {
        BeanUtils.copyProperties(safra, this);
        if (safra.getUsuario() != null) {
            this.usuario = new UsuarioEntity(safra.getUsuario());
        }
    }
}