package trier.jovemdev.provaum.guilherme_monteiro.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import trier.jovemdev.provaum.guilherme_monteiro.entity.MesaEntity;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MesaDto {

	private Long id;
	private Integer numero;
	private Integer capacidadePessoas;
	private RestauranteDto restaurante;

	public MesaDto(MesaEntity entity) {
		this.id = entity.getId();
		this.numero = entity.getNumero();
		this.capacidadePessoas = entity.getCapacidadePessoas();
		this.restaurante = new RestauranteDto(entity.getRestaurante());
	}
}
