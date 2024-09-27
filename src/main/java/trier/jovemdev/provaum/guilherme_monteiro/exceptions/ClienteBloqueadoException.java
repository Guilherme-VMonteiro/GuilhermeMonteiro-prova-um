package trier.jovemdev.provaum.guilherme_monteiro.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import trier.jovemdev.provaum.guilherme_monteiro.dto.ClienteDto;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
@Getter
public class ClienteBloqueadoException extends ExcessaoPersonalizada {

	private static final long serialVersionUID = 1L;

	public ClienteBloqueadoException(String cliente) {
		super(String.format("Cliente %s bloqueado. Para desbloquear fale com o proprietário e pague a taxa.", cliente));
	}
}
