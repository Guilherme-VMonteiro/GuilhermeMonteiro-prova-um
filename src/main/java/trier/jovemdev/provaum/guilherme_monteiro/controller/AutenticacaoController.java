package trier.jovemdev.provaum.guilherme_monteiro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import trier.jovemdev.provaum.guilherme_monteiro.dto.security.CredenciaisDto;
import trier.jovemdev.provaum.guilherme_monteiro.dto.security.TokenDto;
import trier.jovemdev.provaum.guilherme_monteiro.service.AutenticacaoService;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {

    @Autowired
    private AutenticacaoService autenticacaoService;

    @PostMapping("/signin")
    public TokenDto autenticar(@RequestBody CredenciaisDto credenciaisDto) {
        return autenticacaoService.signin(credenciaisDto);
    }
}
