package trier.jovemdev.provaum.guilherme_monteiro.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import trier.jovemdev.provaum.guilherme_monteiro.dto.superclasses.PessoaBaseDto;
import trier.jovemdev.provaum.guilherme_monteiro.entity.FuncionarioEntity;
import trier.jovemdev.provaum.guilherme_monteiro.enums.CargoEnum;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FuncionarioDto extends PessoaBaseDto{

	private Long id;
	private CargoEnum cargo;
	private LocalDate dataAdmisao;
	private BigDecimal salario;
	private Integer cargaHoraria; 
	private RestauranteDto restaurante;
	
	public FuncionarioDto(FuncionarioEntity entity) {
		this.id = entity.getId();
		this.nome = entity.getNome();
		this.sobrenome = entity.getSobrenome();
		this.cpf = entity.getCpf();
		this.dataNascimento = entity.getDataNascimento();
		this.sexo = entity.getSexo();
		this.telefone = entity.getTelefone();
		this.cargo = entity.getCargo();
		this.dataAdmisao = entity.getDataAdmisao();
		this.salario = entity.getSalario();
		this.cargaHoraria = entity.getCargaHoraria();
		this.restaurante = new RestauranteDto(entity.getRestaurante());
	}
}
