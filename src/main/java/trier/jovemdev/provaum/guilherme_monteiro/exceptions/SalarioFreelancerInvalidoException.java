package trier.jovemdev.provaum.guilherme_monteiro.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Getter;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
@Getter
public class SalarioFreelancerInvalidoException extends SalarioInvalidoException	 {

	private static final long serialVersionUID = 1L;

	public SalarioFreelancerInvalidoException() {
		super("Salário do freelancer inválido. O salário deve ser menor que um salário mínimo");
	}

}
