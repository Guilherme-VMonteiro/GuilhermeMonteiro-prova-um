package trier.jovemdev.provaum.guilherme_monteiro.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Getter;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
@Getter
public class QtdEstrelasInvalidoException extends ExcessaoPersonalizada {

	private static final long serialVersionUID = 1L;

	public QtdEstrelasInvalidoException() {
		super("Quantidade de estrelas inválido. Valores de 0 a 3");
	}
}
