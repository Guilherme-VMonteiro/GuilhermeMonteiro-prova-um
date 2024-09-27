package trier.jovemdev.provaum.guilherme_monteiro.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Getter;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
@Getter
public class MesaNaoEncontradaException extends ExcessaoPersonalizada {

	private static final long serialVersionUID = 1L;

	public MesaNaoEncontradaException(Long numeroMesa) {
		super(String.format("Mesa id: %s não encontrada", numeroMesa));
	}
}
