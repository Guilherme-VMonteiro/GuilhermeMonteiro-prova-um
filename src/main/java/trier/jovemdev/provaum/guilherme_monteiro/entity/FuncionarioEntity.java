package trier.jovemdev.provaum.guilherme_monteiro.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import trier.jovemdev.provaum.guilherme_monteiro.dto.FuncionarioDto;
import trier.jovemdev.provaum.guilherme_monteiro.entity.superclasses.PessoaBaseEntity;
import trier.jovemdev.provaum.guilherme_monteiro.enums.CargoEnum;

@Entity
@Table(name = "funcionario")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FuncionarioEntity extends PessoaBaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Enumerated(EnumType.ORDINAL)
	private CargoEnum cargo;

	@Column(name = "data_admisao")
	private LocalDate dataAdmisao;

	private BigDecimal salario;

	@Column(name = "carga_horaria")
	private Integer cargaHoraria;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
	@JoinColumn(name = "restaurante_id", nullable = false)
	private RestauranteEntity restaurante;

	public FuncionarioEntity(FuncionarioDto dto) {
		this.id = dto.getId();
		this.nome = dto.getNome();
		this.sobrenome = dto.getSobrenome();
		this.cpf = dto.getCpf();
		this.dataNascimento = dto.getDataNascimento();
		this.sexo = dto.getSexo();
		this.telefone = dto.getTelefone();
		this.cargo = dto.getCargo();
		this.dataAdmisao = dto.getDataAdmisao();
		this.salario = dto.getSalario();
		this.cargaHoraria = dto.getCargaHoraria();
		this.restaurante = new RestauranteEntity(dto.getRestaurante());
	}

	public void atualizaCampos(FuncionarioDto dto) {
		this.nome = dto.getNome();
		this.sobrenome = dto.getSobrenome();
		this.cpf = dto.getCpf();
		this.dataNascimento = dto.getDataNascimento();
		this.sexo = dto.getSexo();
		this.telefone = dto.getTelefone();
		this.cargo = dto.getCargo();
		this.dataAdmisao = dto.getDataAdmisao();
		this.salario = dto.getSalario();
		this.cargaHoraria = dto.getCargaHoraria();
		this.restaurante = new RestauranteEntity(dto.getRestaurante());
	}
}
