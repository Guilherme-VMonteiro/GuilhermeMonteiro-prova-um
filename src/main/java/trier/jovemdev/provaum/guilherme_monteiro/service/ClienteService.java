package trier.jovemdev.provaum.guilherme_monteiro.service;

import java.util.List;

import trier.jovemdev.provaum.guilherme_monteiro.dto.ClienteDto;
import trier.jovemdev.provaum.guilherme_monteiro.exceptions.ClienteNaoEncontradoException;
import trier.jovemdev.provaum.guilherme_monteiro.exceptions.DataDeNascimentoInvalidaException;
import trier.jovemdev.provaum.guilherme_monteiro.exceptions.NomeInvalidoException;
import trier.jovemdev.provaum.guilherme_monteiro.exceptions.RestauranteNaoEncontradoException;

public interface ClienteService {

	List<ClienteDto> findAll();

	ClienteDto findById(Long id) throws ClienteNaoEncontradoException;
	
	ClienteDto create(ClienteDto clienteDto) throws DataDeNascimentoInvalidaException;
	
	ClienteDto update(ClienteDto clienteDto) throws ClienteNaoEncontradoException, DataDeNascimentoInvalidaException;
	
	void delete(Long id) throws ClienteNaoEncontradoException;
	
	List<ClienteDto> findClientesWithMostReservas(Long idRestaurante) throws RestauranteNaoEncontradoException;
	
	List<ClienteDto> findClientesWithMostValueSpent(Long idRestaurante) throws RestauranteNaoEncontradoException;
	
	//PAGINADO
	List<ClienteDto> findReservasConcluidasAndTotalSpentByName(Long idRestaurante) throws NomeInvalidoException, RestauranteNaoEncontradoException;
	
	List<ClienteDto> findAllWithOurBestPedido(Long idRestaurante) throws RestauranteNaoEncontradoException;
	
	void switchBlockClienteFlag(Long id) throws ClienteNaoEncontradoException;
	
}
