package com.SafraFacil.projeto.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

public class VerificacaoController {

    @GetMapping("/verificacaoSucesso")
    public String verificacaoSucesso() {
        return "verificacaoSucesso";
    }

    @GetMapping("/verificacaoFalha")
    public String verificacaoFalha() {
        return "verificacaoFalha";
    }
}
