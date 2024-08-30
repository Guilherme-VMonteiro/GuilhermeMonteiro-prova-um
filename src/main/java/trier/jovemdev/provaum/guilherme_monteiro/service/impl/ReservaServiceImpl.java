package trier.jovemdev.provaum.guilherme_monteiro.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import trier.jovemdev.provaum.guilherme_monteiro.dto.ClienteDto;
import trier.jovemdev.provaum.guilherme_monteiro.dto.ReservaDto;
import trier.jovemdev.provaum.guilherme_monteiro.entity.ClienteEntity;
import trier.jovemdev.provaum.guilherme_monteiro.entity.ReservaEntity;
import trier.jovemdev.provaum.guilherme_monteiro.enums.StatusReserva;
import trier.jovemdev.provaum.guilherme_monteiro.exceptions.CancelamentoInvalidoException;
import trier.jovemdev.provaum.guilherme_monteiro.exceptions.ClienteInexistenteException;
import trier.jovemdev.provaum.guilherme_monteiro.exceptions.ConclusaoInvalidaException;
import trier.jovemdev.provaum.guilherme_monteiro.exceptions.DataInvalidaException;
import trier.jovemdev.provaum.guilherme_monteiro.exceptions.MesaReservadaException;
import trier.jovemdev.provaum.guilherme_monteiro.exceptions.NumeroMesaInvalidoException;
import trier.jovemdev.provaum.guilherme_monteiro.exceptions.NumeroPessoasInvalidoException;
import trier.jovemdev.provaum.guilherme_monteiro.exceptions.ReservaInexistenteException;
import trier.jovemdev.provaum.guilherme_monteiro.repository.ReservaRepository;
import trier.jovemdev.provaum.guilherme_monteiro.service.ClienteService;
import trier.jovemdev.provaum.guilherme_monteiro.service.ReservaService;

@Service
public class ReservaServiceImpl implements ReservaService {

	@Autowired
	private ReservaRepository repository;

	@Autowired
	private ClienteService clienteService;

	public List<ReservaDto> findAll() {
		return repository.findAll().stream().map(ReservaDto::new).toList();
	}

	public ReservaDto create(ReservaDto reservaDto) throws ClienteInexistenteException, DataInvalidaException,
			NumeroPessoasInvalidoException, MesaReservadaException {

		ClienteEntity cliente = new ClienteEntity(clienteService.findById(reservaDto.getCliente().getId())
				.orElseThrow(() -> new ClienteInexistenteException(reservaDto.getCliente().getId())));

		validaReserva(reservaDto);

		reservaDto.setStatus(StatusReserva.FEITA);
		reservaDto.setCliente(new ClienteDto(cliente));

		ReservaEntity reserva = new ReservaEntity(reservaDto);

		return new ReservaDto(repository.save(reserva));
	}

	public List<ReservaDto> findAllByCliente(Long clienteId) throws ClienteInexistenteException{
		ClienteEntity cliente = new ClienteEntity(
				clienteService.findById(clienteId).orElseThrow(() -> new ClienteInexistenteException(clienteId)));
		return repository.findAllByCliente(cliente).stream().map(ReservaDto::new).toList();
	}

	public void findDisponibility(Integer numeroMesa, LocalDate dataReserva)
			throws DataInvalidaException, MesaReservadaException, NumeroMesaInvalidoException {
		validaDataReserva(dataReserva);
		validaMesa(numeroMesa, dataReserva);
	}

	public ReservaDto updateStatus(Long idReserva, StatusReserva status)
			throws ReservaInexistenteException, CancelamentoInvalidoException, ConclusaoInvalidaException {

		Optional<ReservaEntity> reservaOptional = repository.findById(idReserva);

		if (reservaOptional.isEmpty()) {
			throw new ReservaInexistenteException(idReserva);
		}

		if (status.equals(StatusReserva.CANCELADA)) {
			validaCancelamento(reservaOptional.get().getDataReserva());
		}
		
		if (status.equals(StatusReserva.CONCLUIDA)) {
			validaConcluida(reservaOptional.get().getDataReserva());
		}

		reservaOptional.get().atualizarStatus(status);

		return new ReservaDto(repository.save(reservaOptional.get()));
	}

	private void validaReserva(ReservaDto reserva) throws DataInvalidaException, NumeroPessoasInvalidoException,
			NumeroMesaInvalidoException, MesaReservadaException {
		validaDataReserva(reserva.getDataReserva());
		if (reserva.getNumeroPessoas() > 10 || reserva.getNumeroPessoas() < 1) {
			throw new NumeroPessoasInvalidoException(reserva.getNumeroPessoas());
		}
		validaMesa(reserva.getNumeroMesa(), reserva.getDataReserva());
	}

	private void validaMesa(Integer numeroMesa, LocalDate dataReserva)
			throws MesaReservadaException, NumeroMesaInvalidoException {

		if (numeroMesa > 20 || numeroMesa < 1) {
			throw new NumeroMesaInvalidoException(numeroMesa);
		} else if (repository.findByNumeroMesaAndDataReservaAndStatus(numeroMesa, dataReserva, StatusReserva.FEITA)
				.isPresent()) {
			throw new MesaReservadaException(numeroMesa, dataReserva);
		}
	}

	private void validaDataReserva(LocalDate dataReseva) throws DataInvalidaException {
		if (dataReseva.isBefore(LocalDate.now())) {
			throw new DataInvalidaException(dataReseva);
		}
	}

	private void validaCancelamento(LocalDate dataReserva) throws CancelamentoInvalidoException {
		if (dataReserva.isBefore(LocalDate.now()) || dataReserva.isEqual(LocalDate.now())) {
			throw new CancelamentoInvalidoException();
		}
	}
	
	private void validaConcluida(LocalDate dataReserva) throws ConclusaoInvalidaException {
		if (dataReserva.isBefore(LocalDate.now())) {
			throw new ConclusaoInvalidaException();
		}
	}
}
