package trier.jovemdev.provaum.guilherme_monteiro.entity.security;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@Entity
@NoArgsConstructor
@Data
public class Permissoes implements GrantedAuthority{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String descricao;

    @Override
    public String getAuthority() {
        return this.descricao;
    }
}
