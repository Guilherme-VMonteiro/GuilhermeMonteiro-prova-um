package trier.jovemdev.provaum.guilherme_monteiro.exceptions;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Getter;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
@Getter
public class DataInvalidaException extends ExcessaoPersonalizada{

	private static final long serialVersionUID = 1L;
	
	public DataInvalidaException(LocalDate data) {
		super(String.format("Data de reserva: %s inválida.", data));
	}
}
