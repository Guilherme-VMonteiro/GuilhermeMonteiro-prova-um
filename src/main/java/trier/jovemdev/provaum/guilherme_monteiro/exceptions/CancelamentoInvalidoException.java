package trier.jovemdev.provaum.guilherme_monteiro.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Getter;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
@Getter
public class CancelamentoInvalidoException extends ExcessaoPersonalizada {

	private static final long serialVersionUID = 1L;

	public CancelamentoInvalidoException() {
		super("Cancelamento inválido, o cancelamento deve ser feito com 1 dia de antecedência.");
	}
}
