package trier.jovemdev.provaum.guilherme_monteiro.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import trier.jovemdev.provaum.guilherme_monteiro.entity.ReservaEntity;
import trier.jovemdev.provaum.guilherme_monteiro.entity.ReservaTotalEntity;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReservaTotalDto {
    ReservaDto reserva;
    BigDecimal total;

    public ReservaTotalDto(ReservaTotalEntity reservaTotalEntity) {
        this.reserva = new ReservaDto(reservaTotalEntity.getReserva());
        this.total = reservaTotalEntity.getTotal();
    }
}
