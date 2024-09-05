package trier.jovemdev.provaum.guilherme_monteiro.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import trier.jovemdev.provaum.guilherme_monteiro.entity.ReservaEntity;
import trier.jovemdev.provaum.guilherme_monteiro.enums.StatusReservaEnum;

@Getter
@Setter
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

	public ReservaDto(ReservaEntity reservaEntity) {
		this.id = reservaEntity.getId();
		this.cliente = new ClienteDto(reservaEntity.getCliente());
		this.mesa = new MesaDto(reservaEntity.getMesa());
		this.dataReserva = reservaEntity.getDataReserva();
		this.quantidadePessoas = reservaEntity.getQuantidadePessoas();
		this.status = reservaEntity.getStatus();
		this.observacao = reservaEntity.getObservacao();
		this.pedidos = reservaEntity.getPedidos().stream().map(PedidoDto::new).toList();
	}
}
