package trier.jovemdev.provaum.guilherme_monteiro.entity;

import java.math.BigDecimal;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
import trier.jovemdev.provaum.guilherme_monteiro.dto.PedidoDto;
import trier.jovemdev.provaum.guilherme_monteiro.dto.ReservaDto;

@Entity
@Table(name = "pedido")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PedidoEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
	@JoinColumn(name = "reserva_id", nullable = false)
	private ReservaEntity reserva;

	@Column(name = "descricao", nullable = false)
	private String descricao;

	@Column(nullable = false)
	private BigDecimal valor;

    public PedidoEntity(PedidoDto pedidoDto) {
		this.id = pedidoDto.getId();
		this.reserva = new ReservaEntity(pedidoDto.getReserva());
		this.descricao = pedidoDto.getDescricao();
		this.valor = pedidoDto.getValor();
    }

    public void atualizaCampos(PedidoDto pedidoDto) {
		this.reserva = new ReservaEntity(pedidoDto.getReserva());
		this.descricao = pedidoDto.getDescricao();
		this.valor = pedidoDto.getValor();
    }
}
