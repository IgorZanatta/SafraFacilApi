package com.SafraFacil.projeto.dto;

import com.SafraFacil.projeto.entity.SafraEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
@NoArgsConstructor
public class SafraDTO {

    private Long id;
    private String qual_safra;
    private UsuarioDTO usuario;

    public SafraDTO(SafraEntity safra) {
        BeanUtils.copyProperties(safra, this);
        if (safra.getUsuario() != null) {
            this.usuario = new UsuarioDTO(safra.getUsuario());
        }
    }
}