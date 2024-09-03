package trier.jovemdev.provaum.guilherme_monteiro.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import trier.jovemdev.provaum.guilherme_monteiro.enums.StatusReservaEnum;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReservaDto {

	private Long id;
	private ClienteDto cliente;
	private MesaDto mesa;
	private LocalDate dataReserva;
	private Integer quantidadePessoas;
	private StatusReservaEnum status;
	private String observacao;
	private List<PedidoDto> pedidos;
}
