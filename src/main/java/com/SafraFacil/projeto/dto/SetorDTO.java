package com.SafraFacil.projeto.dto;

import com.SafraFacil.projeto.entity.SetorEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
@NoArgsConstructor
public class SetorDTO {

    private Long id;
    private String nome;
    private String tipo_setor;
    private String tamanho;
    private FazendaDTO fazenda;

    public SetorDTO(SetorEntity setor) {
        BeanUtils.copyProperties(setor, this);
        if (setor.getFazenda() != null) {
            this.fazenda = new FazendaDTO(setor.getFazenda());
        }

    }
}
