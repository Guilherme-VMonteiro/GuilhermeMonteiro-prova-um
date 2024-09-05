package trier.jovemdev.provaum.guilherme_monteiro.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import trier.jovemdev.provaum.guilherme_monteiro.dto.ReservaDto;
import trier.jovemdev.provaum.guilherme_monteiro.enums.StatusReservaEnum;

@Entity
@Table(name = "reserva")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReservaEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
	@JoinColumn(name = "cliente_id", nullable = false)
	private ClienteEntity cliente;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
	@JoinColumn(name = "mesa_id", nullable = false)
	private MesaEntity mesa;
	
	@Column(name = "data_reserva", nullable = false)
	private LocalDate dataReserva;
	
	@Column(name = "quantidade_pessoas", nullable = false)
	private Integer quantidadePessoas;
	
	@Enumerated(EnumType.ORDINAL)
	private StatusReservaEnum status;
	
	private String observacao;
	
	@OneToMany(mappedBy = "reserva", cascade = CascadeType.DETACH)
	private List<PedidoEntity> pedidos;

    public ReservaEntity(ReservaDto reservaDto) {
		this.id = reservaDto.getId();
		this.cliente = new ClienteEntity(reservaDto.getCliente());
		this.mesa = new MesaEntity(reservaDto.getMesa());
		this.dataReserva = reservaDto.getDataReserva();
		this.quantidadePessoas = reservaDto.getQuantidadePessoas();
		this.status = reservaDto.getStatus();
		this.observacao = reservaDto.getObservacao();
    }

	public void atualizaCampos(ReservaDto reservaDto){
		this.cliente = new ClienteEntity(reservaDto.getCliente());
		this.mesa = new MesaEntity(reservaDto.getMesa());
		this.dataReserva = reservaDto.getDataReserva();
		this.quantidadePessoas = reservaDto.getQuantidadePessoas();
		this.status = reservaDto.getStatus();
		this.observacao = reservaDto.getObservacao();
	}
}
