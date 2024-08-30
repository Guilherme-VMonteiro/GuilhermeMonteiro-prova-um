package trier.jovemdev.provaum.guilherme_monteiro.exceptions;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Getter;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
@Getter
public class MesaReservadaException extends ExcessaoPersonalizada {

	private static final long serialVersionUID = 1L;

	public MesaReservadaException(Integer numeroMesa, LocalDate dataReserva) {
		super(String.format("Mesa: %s já reservada para o dia %s.", numeroMesa, dataReserva));
	}
}
