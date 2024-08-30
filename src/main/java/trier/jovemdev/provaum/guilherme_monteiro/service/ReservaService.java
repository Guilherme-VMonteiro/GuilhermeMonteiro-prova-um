package trier.jovemdev.provaum.guilherme_monteiro.service;

import java.time.LocalDate;
import java.util.List;

import trier.jovemdev.provaum.guilherme_monteiro.dto.ReservaDto;
import trier.jovemdev.provaum.guilherme_monteiro.enums.StatusReserva;
import trier.jovemdev.provaum.guilherme_monteiro.exceptions.CancelamentoInvalidoException;
import trier.jovemdev.provaum.guilherme_monteiro.exceptions.ClienteInexistenteException;
import trier.jovemdev.provaum.guilherme_monteiro.exceptions.ConclusaoInvalidaException;
import trier.jovemdev.provaum.guilherme_monteiro.exceptions.DataInvalidaException;
import trier.jovemdev.provaum.guilherme_monteiro.exceptions.MesaReservadaException;
import trier.jovemdev.provaum.guilherme_monteiro.exceptions.NumeroMesaInvalidoException;
import trier.jovemdev.provaum.guilherme_monteiro.exceptions.NumeroPessoasInvalidoException;
import trier.jovemdev.provaum.guilherme_monteiro.exceptions.ReservaInexistenteException;

public interface ReservaService {

	List<ReservaDto> findAll();

	ReservaDto create(ReservaDto reservaDto) throws ClienteInexistenteException, DataInvalidaException,
			NumeroPessoasInvalidoException, MesaReservadaException, NumeroMesaInvalidoException;

	List<ReservaDto> findAllByCliente(Long clienteId) throws ClienteInexistenteException;

	void findDisponibility(Integer numeroMesa, LocalDate dataReserva)
			throws DataInvalidaException, MesaReservadaException, NumeroMesaInvalidoException;

	ReservaDto updateStatus(Long idReserva, StatusReserva status) throws ReservaInexistenteException, CancelamentoInvalidoException, ConclusaoInvalidaException;
}
