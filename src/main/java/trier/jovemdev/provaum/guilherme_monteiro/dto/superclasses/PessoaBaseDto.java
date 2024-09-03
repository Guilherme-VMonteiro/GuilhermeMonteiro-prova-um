package trier.jovemdev.provaum.guilherme_monteiro.dto.superclasses;

import java.time.LocalDate;

import lombok.Getter;
import trier.jovemdev.provaum.guilherme_monteiro.enums.SexoEnum;

@Getter
public abstract class PessoaBaseDto {

	protected String nome;
	protected String sobrenome;
	protected String cpf;
	protected LocalDate dataNascimento;
	protected SexoEnum sexo;
	protected String telefone;
}
