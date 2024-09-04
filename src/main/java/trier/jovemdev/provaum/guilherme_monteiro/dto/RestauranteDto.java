package trier.jovemdev.provaum.guilherme_monteiro.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import trier.jovemdev.provaum.guilherme_monteiro.entity.RestauranteEntity;
import trier.jovemdev.provaum.guilherme_monteiro.enums.TipoComidaEnum;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RestauranteDto {

	private Long id;
	private String nome;
	private String cnpj;
	private Integer estrelas;
	private TipoComidaEnum tipoComida;
	
	public RestauranteDto(RestauranteEntity entity) {
		this.id = entity.getId();
		this.nome = entity.getNome();
		this.cnpj = entity.getCnpj();
		this.estrelas = entity.getEstrelas();
		this.tipoComida = entity.getTipoComida();
	}
}
