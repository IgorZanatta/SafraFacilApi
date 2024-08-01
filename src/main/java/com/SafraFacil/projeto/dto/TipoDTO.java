package com.SafraFacil.projeto.dto;

import com.SafraFacil.projeto.entity.TipoEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class TipoDTO {

    private Long id;
    private String tipo_atividade;
    private String gasto;
    private String lucro;
    private Date data;  // Alterado para Date
    private String observacao;
    private byte[] anexos;
    private SetorDTO setor; // Use SetorDTO aqui

    public TipoDTO(TipoEntity tipo) {
        BeanUtils.copyProperties(tipo, this);
        if (tipo.getSetor() != null) {
            this.setor = new SetorDTO(tipo.getSetor());
        }
    }
}
