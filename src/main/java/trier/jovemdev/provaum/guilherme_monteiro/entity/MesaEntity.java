package trier.jovemdev.provaum.guilherme_monteiro.entity;

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
import trier.jovemdev.provaum.guilherme_monteiro.dto.MesaDto;

@Entity
@Table(name = "mesa")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MesaEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false)
	private Integer numero;

	@Column(name = "capacidade_pessoas", nullable = false)
	private Integer capacidadePessoas;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
	@JoinColumn(name = "restaurante_id", nullable = false)
	private RestauranteEntity restaurante;

	@OneToMany(mappedBy = "mesa", cascade = CascadeType.DETACH)
	private List<ReservaEntity> reservas;

	public MesaEntity(MesaDto dto) {
		this.id = dto.getId();
		this.numero = dto.getNumero();
		this.capacidadePessoas = dto.getCapacidadePessoas();
		this.restaurante = new RestauranteEntity(dto.getRestaurante());
	}

	public void atualizaCampos(MesaDto dto) {
		this.numero = dto.getNumero();
		this.capacidadePessoas = dto.getCapacidadePessoas();
	}
}
