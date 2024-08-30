package trier.jovemdev.provaum.guilherme_monteiro.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Getter;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
@Getter
public class ExcessaoPersonalizada extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	private ErrorMessage errorMessage;

	public ExcessaoPersonalizada(String message) {
		this.errorMessage = new ErrorMessage(message);
	}
}
