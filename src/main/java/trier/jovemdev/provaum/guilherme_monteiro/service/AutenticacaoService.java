package trier.jovemdev.provaum.guilherme_monteiro.service;

import org.springframework.security.authentication.BadCredentialsException;
import trier.jovemdev.provaum.guilherme_monteiro.dto.security.CredenciaisDto;
import trier.jovemdev.provaum.guilherme_monteiro.dto.security.TokenDto;

public interface AutenticacaoService {

    TokenDto signin(CredenciaisDto credenciaisDto) throws BadCredentialsException;
}
