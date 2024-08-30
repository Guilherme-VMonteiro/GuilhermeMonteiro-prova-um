package trier.jovemdev.provaum.guilherme_monteiro.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Getter;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
@Getter
public class ConclusaoInvalidaException extends ExcessaoPersonalizada {

	private static final long serialVersionUID = 1L;

	public ConclusaoInvalidaException() {
		super("Concusão inválida, a concusão só pode ser realizada caso a data da reserva seja a de hoje ou após.");
	}
}
