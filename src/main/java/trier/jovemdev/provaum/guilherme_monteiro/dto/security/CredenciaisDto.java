package trier.jovemdev.provaum.guilherme_monteiro.dto.security;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CredenciaisDto {

    private String email;
    private String password;
}
