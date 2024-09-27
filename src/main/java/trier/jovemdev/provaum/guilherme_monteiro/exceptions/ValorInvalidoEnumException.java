package trier.jovemdev.provaum.guilherme_monteiro.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Getter;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
@Getter
public class ValorInvalidoEnumException extends ExcessaoPersonalizada {

	private static final long serialVersionUID = 1L;

	public ValorInvalidoEnumException(String campo, String valor) {
		super(String.format("Valor do campo %s inválido (%s).", campo, valor));
	}
}
