package trier.jovemdev.provaum.guilherme_monteiro.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Getter;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
@Getter
public class NumeroPessoasInvalidoException extends ExcessaoPersonalizada {

	private static final long serialVersionUID = 1L;

	public NumeroPessoasInvalidoException(int numeroPessoas) {
		super(String.format("Número de pessoas: %s inválido. (1-10)", numeroPessoas));
	}
}
