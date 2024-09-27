package trier.jovemdev.provaum.guilherme_monteiro.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
@Getter
public class PedidoNaoEncontradoException extends ExcessaoPersonalizada {

	private static final long serialVersionUID = 1L;

	public PedidoNaoEncontradoException(Long id) {
		super(String.format("Pedido id: %s não encontrado.", id));
	}
}
