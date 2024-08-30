package trier.jovemdev.provaum.guilherme_monteiro.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Getter;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
@Getter
public class NumeroMesaInvalidoException extends ExcessaoPersonalizada {

	private static final long serialVersionUID = 1L;

	public NumeroMesaInvalidoException(int numeroMesa) {
		super(String.format("Número de mesa: %s inválido. (1-20)", numeroMesa));
	}
}
