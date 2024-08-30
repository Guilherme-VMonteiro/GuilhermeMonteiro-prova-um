package trier.jovemdev.provaum.guilherme_monteiro.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Getter;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
@Getter
public class EmailExistenteException extends ExcessaoPersonalizada{

	private static final long serialVersionUID = 1L;
	
	public EmailExistenteException(String email) {
		super(String.format("Cliente com o email: %s já existente.", email));
	}
}
