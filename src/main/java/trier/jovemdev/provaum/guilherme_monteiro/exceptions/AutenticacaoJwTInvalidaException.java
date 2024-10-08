package trier.jovemdev.provaum.guilherme_monteiro.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class AutenticacaoJwTInvalidaException extends AuthenticationException {

	private static final long serialVersionUID = 1L;

	public AutenticacaoJwTInvalidaException(String m) {
		super(m);
	}
}
