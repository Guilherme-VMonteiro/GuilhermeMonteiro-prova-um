package trier.jovemdev.provaum.guilherme_monteiro.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PedidoDto {

	private Long id;
	private ReservaDto reserva;
	private String descricao;
	private BigDecimal valor;
}
