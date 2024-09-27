package trier.jovemdev.provaum.guilherme_monteiro.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import trier.jovemdev.provaum.guilherme_monteiro.dto.RestauranteDto;
import trier.jovemdev.provaum.guilherme_monteiro.enums.TipoComidaEnum;

@Entity
@Table(name = "restaurante")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RestauranteEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(length = 80, nullable = false)
	private String nome;
	
	@Column(length = 14, nullable = false, unique = true)
	private String cnpj;
	
	@Column(length = 3)
	private Integer estrelas;
	
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "tipo_comida", nullable = false)
	private TipoComidaEnum tipoComida;
	
	@OneToMany(mappedBy = "restaurante", cascade = CascadeType.DETACH)
	private List<ClienteEntity> clientes;
	
	@OneToMany(mappedBy = "restaurante", cascade = CascadeType.DETACH)
	private List<FuncionarioEntity> funcionarios;
	
	@OneToMany(mappedBy = "restaurante", cascade = CascadeType.DETACH)
	private List<MesaEntity> mesas;
	
	public RestauranteEntity(RestauranteDto restauranteDto) {
		this.id = restauranteDto.getId();
		this.nome = restauranteDto.getNome();
		this.cnpj = restauranteDto.getCnpj();
		this.estrelas = restauranteDto.getEstrelas();
		this.tipoComida = restauranteDto.getTipoComida();
	}
	
	public void atualizaCampos(RestauranteDto restauranteDto) {
		this.nome = restauranteDto.getNome();
		this.cnpj = restauranteDto.getCnpj();
		this.estrelas = restauranteDto.getEstrelas();
		this.tipoComida = restauranteDto.getTipoComida();
	}
}
