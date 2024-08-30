package trier.jovemdev.provaum.guilherme_monteiro.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import trier.jovemdev.provaum.guilherme_monteiro.entity.ClienteEntity;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ClienteDto {

	private Long id;
	private String nome;
	private String email;
	
	public ClienteDto(ClienteEntity entity) {
		this.id = entity.getId();
		this.nome = entity.getNome();
		this.email = entity.getEmail();
	}
}
