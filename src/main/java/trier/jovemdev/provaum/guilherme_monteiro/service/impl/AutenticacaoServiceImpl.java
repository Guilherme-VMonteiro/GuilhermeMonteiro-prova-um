package trier.jovemdev.provaum.guilherme_monteiro.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import trier.jovemdev.provaum.guilherme_monteiro.dto.security.CredenciaisDto;
import trier.jovemdev.provaum.guilherme_monteiro.dto.security.TokenDto;
import trier.jovemdev.provaum.guilherme_monteiro.entity.RestauranteEntity;
import trier.jovemdev.provaum.guilherme_monteiro.repository.custom.RestauranteRepositoryCustom;
import trier.jovemdev.provaum.guilherme_monteiro.security.JwtTokenProvider;
import trier.jovemdev.provaum.guilherme_monteiro.service.AutenticacaoService;

@Service
public class AutenticacaoServiceImpl implements AutenticacaoService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private RestauranteRepositoryCustom restauranteRepository;

    public TokenDto signin(CredenciaisDto credenciaisDto) throws BadCredentialsException {
        try {
            if (credenciaisDto == null || credenciaisDto.getEmail() == null || credenciaisDto.getEmail().isBlank() || credenciaisDto.getPassword() == null || credenciaisDto.getPassword().isBlank()) {
                throw new BadCredentialsException("Usuario ou senha invalidos.");
            }

            String email = credenciaisDto.getEmail();
            String password = credenciaisDto.getPassword();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));

            RestauranteEntity restaurante = restauranteRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Restaurante com email: " + email + " nao encontrado"));

            TokenDto accessToken = jwtTokenProvider.createAccessToken(email, restaurante.getRoles());

            if(accessToken == null){
                throw new BadCredentialsException("Usuario ou senha invalidos.");
            }

            return accessToken;
        } catch (Exception e) {
            throw new BadCredentialsException("Usuario ou senha invalidos.");
        }
    }
}
