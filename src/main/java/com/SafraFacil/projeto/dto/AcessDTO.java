package com.SafraFacil.projeto.dto;

public class AcessDTO {

    private String token;
    private Long userId;

    // Construtor que inclui o userId
    public AcessDTO(String token, Long userId) {
        this.token = token;
        this.userId = userId;
    }

    // Getter e Setter para token
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    // Getter e Setter para userId
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
