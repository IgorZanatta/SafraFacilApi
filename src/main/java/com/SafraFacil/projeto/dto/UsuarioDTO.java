package com.SafraFacil.projeto.dto;

import com.SafraFacil.projeto.entity.UsuarioEntity;
import com.SafraFacil.projeto.entity.enums.TipoSituacaoUsuario;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
public class UsuarioDTO {

    private Long id;

    private String nome;

    private String senha;

    private String login;

    private String telefone;

    private TipoSituacaoUsuario situacao;

    private Integer codigoVerificacao;

    private Instant dataExpiracaoCodigo;


    public UsuarioDTO(UsuarioEntity usuario){
        BeanUtils.copyProperties(usuario, this);
    }

    public UsuarioDTO(){

    }
}
