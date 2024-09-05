package trier.jovemdev.provaum.guilherme_monteiro.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import trier.jovemdev.provaum.guilherme_monteiro.entity.PedidoEntity;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PedidoDto {

	private Long id;
	private ReservaDto reserva;
	private String descricao;
	private BigDecimal valor;

    public PedidoDto(PedidoEntity pedidoEntity) {
		this.id = pedidoEntity.getId();
		this.reserva = new ReservaDto(pedidoEntity.getReserva());
		this.descricao = pedidoEntity.getDescricao();
		this.valor = pedidoEntity.getValor();
    }
}
