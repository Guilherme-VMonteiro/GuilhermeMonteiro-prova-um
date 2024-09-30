package trier.jovemdev.provaum.guilherme_monteiro.dto.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TokenDto {

    private String email;
    private Boolean autenticated;
    private Date created;
    private Date expiration;
    private String accessToken;
    private String refreshToken;
}
