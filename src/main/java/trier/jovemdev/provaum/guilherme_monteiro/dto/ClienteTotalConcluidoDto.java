package trier.jovemdev.provaum.guilherme_monteiro.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteTotalConcluidoDto {
    private ClienteDto cliente;
    private Long reservasConcluidas;
    private BigDecimal valorGasto;
}
