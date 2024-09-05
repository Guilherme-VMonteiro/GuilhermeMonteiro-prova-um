package trier.jovemdev.provaum.guilherme_monteiro.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(RestauranteNaoEncontradoException.class)
	public ResponseEntity<ErrorMessage> handleRestauranteNaoEncontradoException(RestauranteNaoEncontradoException e) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getErrorMessage());
	}

	@ExceptionHandler(CnpjJaExistenteException.class)
	public ResponseEntity<ErrorMessage> handleCnpjJaExistenteException(CnpjJaExistenteException e) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getErrorMessage());
	}
	
	@ExceptionHandler(CnpjInvalidoException.class)
	public ResponseEntity<ErrorMessage> handleCnpjInvalidoException(CnpjInvalidoException e) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getErrorMessage());
	}
	
	@ExceptionHandler(QtdEstrelasInvalidoException.class)
	public ResponseEntity<ErrorMessage> handleQtdEstrelasInvalidoException(QtdEstrelasInvalidoException e) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getErrorMessage());
	}
	
	@ExceptionHandler(NomeInvalidoException.class)
	public ResponseEntity<ErrorMessage> handleNomeInvalidoException(NomeInvalidoException e) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getErrorMessage());
	}

	@ExceptionHandler(ValorInvalidoEnumException.class)
	public ResponseEntity<ErrorMessage> handleValorInvalidoEnumException(ValorInvalidoEnumException e) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getErrorMessage());
	}
	
	@ExceptionHandler(DataDeNascimentoInvalidaException.class)
	public ResponseEntity<ErrorMessage> handleDataDeNascimentoInvalidaException(DataDeNascimentoInvalidaException e) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getErrorMessage());
	}
	
	@ExceptionHandler(CargaHorariaInvalidaException.class)
	public ResponseEntity<ErrorMessage> handleCargaHorariaInvalidaException(CargaHorariaInvalidaException e) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getErrorMessage());
	}
	
	@ExceptionHandler(FuncionarioNaoEncontradoException.class)
	public ResponseEntity<ErrorMessage> handleFuncionarioNaoEncontradoException(FuncionarioNaoEncontradoException e) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getErrorMessage());
	}
	
	@ExceptionHandler(SalarioInvalidoException.class)
	public ResponseEntity<ErrorMessage> handleSalarioInvalidoException(SalarioInvalidoException e) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getErrorMessage());
	}
	
	@ExceptionHandler(SalarioFreelancerInvalidoException.class)
	public ResponseEntity<ErrorMessage> handleSalarioInvalidoException(SalarioFreelancerInvalidoException e) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getErrorMessage());
	}
	
	@ExceptionHandler(ClienteNaoEncontradoException.class)
	public ResponseEntity<ErrorMessage> handleClienteNaoEncontradoException(ClienteNaoEncontradoException e) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getErrorMessage());
	}
	
	@ExceptionHandler(MesaNaoEncontradaException.class)
	public ResponseEntity<ErrorMessage> handleMesaNaoEncontradaException(MesaNaoEncontradaException e) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getErrorMessage());
	}

	@ExceptionHandler(ReservaNaoEncontradaException.class)
	public ResponseEntity<ErrorMessage> handleReservaNaoEncontradaException(ReservaNaoEncontradaException e) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getErrorMessage());
	}

	@ExceptionHandler(DataInvalidaException.class)
	public ResponseEntity<ErrorMessage> handleDataInvalidaException(DataInvalidaException e) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getErrorMessage());
	}

	@ExceptionHandler(ReservaBloqueadaException.class)
	public ResponseEntity<ErrorMessage> handleReservaBloqueadaException(ReservaBloqueadaException e) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getErrorMessage());
	}

	@ExceptionHandler(ConclusaoInvalidaException.class)
	public ResponseEntity<ErrorMessage> handleConclusaoInvalidaException(ConclusaoInvalidaException e) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getErrorMessage());
	}

	@ExceptionHandler(CancelamentoInvalidoException.class)
	public ResponseEntity<ErrorMessage> handleCancelamentoInvalidoException(CancelamentoInvalidoException e) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getErrorMessage());
	}

	@ExceptionHandler(ClienteBloqueadoException.class)
	public ResponseEntity<ErrorMessage> handleClienteBloqueadoException(ClienteBloqueadoException e) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getErrorMessage());
	}
}
