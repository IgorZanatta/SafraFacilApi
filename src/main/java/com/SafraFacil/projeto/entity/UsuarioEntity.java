package com.SafraFacil.projeto.entity;

import com.SafraFacil.projeto.dto.UsuarioDTO;
import com.SafraFacil.projeto.entity.enums.TipoSituacaoUsuario;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.time.Instant;
import java.util.Objects;


@Entity
@Table(name = "SF_USUARIO")
@Getter
@Setter
@NoArgsConstructor
public class UsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String senha;

    @Column(nullable = false, unique = true)
    private String login;

    @Column(nullable = false)
    private String telefone;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoSituacaoUsuario situacao;

    @Column
    private Integer codigoVerificacao;

    @Column
    private Instant dataExpiracaoCodigo;

    public UsuarioEntity(UsuarioDTO usuario){
        BeanUtils.copyProperties(usuario, this);
    }

    public UsuarioEntity(){

    }
}
