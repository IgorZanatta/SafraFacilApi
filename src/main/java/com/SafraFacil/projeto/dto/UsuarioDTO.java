package com.SafraFacil.projeto.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private String situacao;
    private Integer codigoVerificacao;
    private Instant dataExpiracaoCodigo;

    // Se precisar de um construtor com parâmetros, adicione-o assim:
    public UsuarioDTO(String nome, String senha, String login, String telefone, String situacao, Integer codigoVerificacao, Instant dataExpiracaoCodigo) {
        this.nome = nome;
        this.senha = senha;
        this.login = login;
        this.telefone = telefone;
        this.situacao = situacao;
        this.codigoVerificacao = codigoVerificacao;
        this.dataExpiracaoCodigo = dataExpiracaoCodigo;
    }
}
