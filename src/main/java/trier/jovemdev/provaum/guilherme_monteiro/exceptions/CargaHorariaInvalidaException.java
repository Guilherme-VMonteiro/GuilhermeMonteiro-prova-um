package trier.jovemdev.provaum.guilherme_monteiro.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Getter;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
@Getter
public class CargaHorariaInvalidaException extends ExcessaoPersonalizada {

	private static final long serialVersionUID = 1L;

	public CargaHorariaInvalidaException(Integer cargaHoraria) {
		super(String.format("Carga horária máxima(220) excedida(%s)", cargaHoraria));
	}
}
