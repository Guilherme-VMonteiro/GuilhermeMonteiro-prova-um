package trier.jovemdev.provaum.guilherme_monteiro.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import lombok.*;
import trier.jovemdev.provaum.guilherme_monteiro.entity.ReservaEntity;
import trier.jovemdev.provaum.guilherme_monteiro.enums.StatusReservaEnum;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReservaDto {

	private Long id;
	private ClienteDto cliente;
	private MesaDto mesa;
	private LocalDate dataReserva;
	private Integer quantidadePessoas;
	private StatusReservaEnum status;
	private String observacao;

	public ReservaDto(Long id) {
		this.id = id;
	}

	public ReservaDto(ReservaEntity reservaEntity) {
		this.id = reservaEntity.getId();
		this.cliente = new ClienteDto(reservaEntity.getCliente());
		this.mesa = new MesaDto(reservaEntity.getMesa());
		this.dataReserva = reservaEntity.getDataReserva();
		this.quantidadePessoas = reservaEntity.getQuantidadePessoas();
		this.status = reservaEntity.getStatus();
		this.observacao = reservaEntity.getObservacao();
	}
}
