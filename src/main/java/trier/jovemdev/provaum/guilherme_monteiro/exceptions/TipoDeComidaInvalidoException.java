package trier.jovemdev.provaum.guilherme_monteiro.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Getter;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
@Getter
public class TipoDeComidaInvalidoException extends ExcessaoPersonalizada {

	private static final long serialVersionUID = 1L;

	public TipoDeComidaInvalidoException() {
		super("Tipo de comida inválido.");
	}
}
