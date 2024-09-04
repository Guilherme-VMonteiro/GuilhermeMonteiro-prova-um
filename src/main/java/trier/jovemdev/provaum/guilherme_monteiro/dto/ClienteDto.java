package trier.jovemdev.provaum.guilherme_monteiro.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import trier.jovemdev.provaum.guilherme_monteiro.dto.superclasses.PessoaBaseDto;
import trier.jovemdev.provaum.guilherme_monteiro.entity.ClienteEntity;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDto extends PessoaBaseDto {

	private Long id;
	private LocalDate dataCadastro;
	private Integer quantidadeReservas;
	private BigDecimal valorGasto;
	private RestauranteDto restaurante;
	private Boolean bloqueado;

	public ClienteDto(ClienteEntity entity) {
		this.id = entity.getId();
		this.dataCadastro = entity.getDataCadastro();
		this.quantidadeReservas = entity.getQuantidadeReservas();
		this.valorGasto = entity.getValorGasto();
		this.restaurante = new RestauranteDto(entity.getRestaurante());
		this.nome = entity.getNome();
		this.sobrenome = entity.getSobrenome();
		this.cpf = entity.getCpf();
		this.dataNascimento = entity.getDataNascimento();
		this.sexo = entity.getSexo();
		this.telefone = entity.getTelefone();
		this.bloqueado = entity.getBloqueado();
	}
}
