package trier.jovemdev.provaum.guilherme_monteiro.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import trier.jovemdev.provaum.guilherme_monteiro.dto.ClienteDto;

@Entity
@Table(name = "cliente")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ClienteEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false)
	private String nome;
	
	@Column(nullable = false, unique = true)
	private String email;
	
	@OneToMany(mappedBy = "cliente", cascade = CascadeType.DETACH)
	private List<ReservaEntity> reservas;
	
	public ClienteEntity(ClienteDto dto) {
		this.id = dto.getId();
		this.nome = dto.getNome();
		this.email = dto.getEmail();
	}
	
	public void atualizaCliente(ClienteDto dto) {
		this.nome = dto.getNome();
		this.email = dto.getEmail();
	}
}
