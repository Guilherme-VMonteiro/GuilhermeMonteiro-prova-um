package trier.jovemdev.provaum.guilherme_monteiro.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDate;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
@Getter
public class ReservaBloqueadaException extends ExcessaoPersonalizada {

	private static final long serialVersionUID = 1L;

	public ReservaBloqueadaException(String nomeCliente, LocalDate data) {
		super(String.format("Reserva bloqueada para o cliente: %s, tente novamente após %s", nomeCliente, data));
	}
}
