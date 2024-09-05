package trier.jovemdev.provaum.guilherme_monteiro.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import trier.jovemdev.provaum.guilherme_monteiro.dto.ReservaDto;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReservaTotalEntity {
    ReservaEntity reserva;
    BigDecimal total;
}
