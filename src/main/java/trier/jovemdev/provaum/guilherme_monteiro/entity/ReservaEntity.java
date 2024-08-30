package trier.jovemdev.provaum.guilherme_monteiro.entity;

import java.time.LocalDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import trier.jovemdev.provaum.guilherme_monteiro.dto.ReservaDto;
import trier.jovemdev.provaum.guilherme_monteiro.enums.StatusReserva;

@Entity
@Table(name = "reserva")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReservaEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "data_reserva", nullable = false)
	private LocalDate dataReserva;

	@Column(name = "numero_pessoas", nullable = false, length = 10)
	private Integer numeroPessoas;

	@Column(name = "numero_mesa", nullable = false, length = 20)
	private Integer numeroMesa;

	@Enumerated(EnumType.ORDINAL)
	private StatusReserva status;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
	@JoinColumn(name = "id_cliente", nullable = false)
	private ClienteEntity cliente;

	public ReservaEntity(ReservaDto reservaDto) {
		this.id = reservaDto.getId();
		this.dataReserva = reservaDto.getDataReserva();
		this.numeroPessoas = reservaDto.getNumeroPessoas();
		this.numeroMesa = reservaDto.getNumeroMesa();
		this.status = reservaDto.getStatus();
		this.cliente = new ClienteEntity(reservaDto.getCliente());
	}
	
	public void atualizarStatus(StatusReserva status) {
		this.status = status;
	}
}
