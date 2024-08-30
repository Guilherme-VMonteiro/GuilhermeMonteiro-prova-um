package trier.jovemdev.provaum.guilherme_monteiro.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import trier.jovemdev.provaum.guilherme_monteiro.entity.ReservaEntity;
import trier.jovemdev.provaum.guilherme_monteiro.enums.StatusReserva;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ReservaDto {
	private Long id;
	private LocalDate dataReserva;
	private Integer numeroPessoas;
	private Integer numeroMesa;
	private StatusReserva status;
	private ClienteDto cliente;
	
	public ReservaDto(ReservaEntity entity) {
		this.id = entity.getId();
		this.dataReserva = entity.getDataReserva();
		this.numeroPessoas = entity.getNumeroPessoas();
		this.numeroMesa = entity.getNumeroMesa();
		this.status = entity.getStatus();
		this.cliente = new ClienteDto(entity.getCliente());
	}
}
