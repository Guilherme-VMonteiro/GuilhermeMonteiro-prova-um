package trier.jovemdev.provaum.guilherme_monteiro.entity.superclasses;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import trier.jovemdev.provaum.guilherme_monteiro.enums.SexoEnum;


@MappedSuperclass
@Getter
public abstract class PessoaBaseEntity {

	@Column(length = 60, nullable = false)
	protected String nome;
	
	@Column(length = 60, nullable = false)
	protected String sobrenome;
	
	@Column(length = 11, nullable = false)
	protected String cpf;
	
	@Column(name = "data_nascimento", nullable = false)
	protected LocalDate dataNascimento;
	
	@Enumerated(EnumType.ORDINAL)
	protected SexoEnum sexo;
	
	@Column(length = 13, nullable = false)
	protected String telefone;
	
}
