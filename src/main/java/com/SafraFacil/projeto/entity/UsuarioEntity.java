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

    public TipoSituacaoUsuario getSituacao() {
        return situacao;
    }

    public void setSituacao(TipoSituacaoUsuario situacao) {
        this.situacao = situacao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Integer getCodigoVerificacao() {
        return codigoVerificacao;
    }

    public void setCodigoVerificacao(Integer codigoVerificacao) {
        this.codigoVerificacao = codigoVerificacao;
    }

    public Instant getDataExpiracaoCodigo() {
        return dataExpiracaoCodigo;
    }

    public void setDataExpiracaoCodigo(Instant dataExpiracaoCodigo) {
        this.dataExpiracaoCodigo = dataExpiracaoCodigo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UsuarioEntity that)) return false;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }
}
