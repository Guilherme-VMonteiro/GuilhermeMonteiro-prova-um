package trier.jovemdev.provaum.guilherme_monteiro.service;

import java.util.List;

import trier.jovemdev.provaum.guilherme_monteiro.dto.ClienteDto;
import trier.jovemdev.provaum.guilherme_monteiro.exceptions.ClienteNaoEncontradoException;
import trier.jovemdev.provaum.guilherme_monteiro.exceptions.DataDeNascimentoInvalidaException;
import trier.jovemdev.provaum.guilherme_monteiro.exceptions.NomeInvalidoException;

public interface ClienteService {

	List<ClienteDto> findAll();

	ClienteDto findById(Long id) throws ClienteNaoEncontradoException;
	
	ClienteDto create(ClienteDto clienteDto) throws DataDeNascimentoInvalidaException;
	
	ClienteDto update(ClienteDto clienteDto) throws ClienteNaoEncontradoException, DataDeNascimentoInvalidaException;
	
	void delete(Long id) throws ClienteNaoEncontradoException;
	
	List<ClienteDto> findClientesWithMostReservas();
	
	List<ClienteDto> findClientesWithMostValueSpent();
	
	//PAGINADO
	List<ClienteDto> findReservasConcluidasAndTotalSpentByName() throws NomeInvalidoException;
	
	List<ClienteDto> findAllWithOurBestPedido();
	
	void unblockCliente(Long id) throws ClienteNaoEncontradoException;
	
}
