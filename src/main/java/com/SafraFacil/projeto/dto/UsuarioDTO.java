package com.SafraFacil.projeto.dto;

import com.SafraFacil.projeto.entity.UsuarioEntity;
import com.SafraFacil.projeto.entity.enums.TipoSituacaoUsuario;
import org.springframework.beans.BeanUtils;

import java.time.Instant;

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

    public TipoSituacaoUsuario getSituacao() {
        return situacao;
    }

    public void setSituacao(TipoSituacaoUsuario situacao) {
        this.situacao = situacao;
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
}
