package trier.jovemdev.provaum.guilherme_monteiro.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MesaDto {
	
	private Long id;
	private Integer numero;
	private Integer capacidadePessoas;
	private RestauranteDto restaurante;
}
