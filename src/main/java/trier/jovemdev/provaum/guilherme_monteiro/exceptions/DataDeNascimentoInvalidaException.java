package trier.jovemdev.provaum.guilherme_monteiro.exceptions;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Getter;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
@Getter
public class DataDeNascimentoInvalidaException extends ExcessaoPersonalizada {

	private static final long serialVersionUID = 1L;

	public DataDeNascimentoInvalidaException(LocalDate dataAniversario) {
		super(String.format("Data de nascimento inválida(%s), é necessário ter de 12 até 100 anos.", dataAniversario));
	}
}
