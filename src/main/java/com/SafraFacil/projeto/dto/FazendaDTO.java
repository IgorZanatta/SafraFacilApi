package com.SafraFacil.projeto.dto;

import com.SafraFacil.projeto.entity.FazendaEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
@NoArgsConstructor
public class FazendaDTO {

    private Long id;
    private String nome;
    private String tamanho;
    private SafraDTO safra;
    private UsuarioDTO usuario;

    public FazendaDTO(FazendaEntity fazenda) {
        BeanUtils.copyProperties(fazenda, this);
        if (fazenda.getSafra() != null) {
            this.safra = new SafraDTO(fazenda.getSafra());
        }
        if (fazenda.getUsuario() != null) {
            this.usuario = new UsuarioDTO(fazenda.getUsuario());
        }
    }
}
