package trier.jovemdev.provaum.guilherme_monteiro.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
import trier.jovemdev.provaum.guilherme_monteiro.entity.superclasses.PessoaBaseEntity;

@Entity
@Table(name = "cliente")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ClienteEntity extends PessoaBaseEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "data_cadastro", nullable = false)
	private LocalDate dataCadastro;
	
	@Column(name = "quantidade_reservas")
	private Integer quantidadeReservas;
	
	@Column(name = "valor_gasto", precision = 3)
	private BigDecimal valorGasto; 
	
	private Boolean bloqueado;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
	@JoinColumn(name = "restaurante_id", nullable = false)
	private RestauranteEntity restaurante;
	
	@OneToMany(mappedBy = "cliente", cascade = CascadeType.DETACH)
	private List<ReservaEntity> reservas;
}
