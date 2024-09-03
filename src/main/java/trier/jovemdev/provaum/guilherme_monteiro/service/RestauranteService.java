package trier.jovemdev.provaum.guilherme_monteiro.service;

import java.util.List;

import trier.jovemdev.provaum.guilherme_monteiro.dto.RestauranteDto;
import trier.jovemdev.provaum.guilherme_monteiro.exceptions.CnpjInvalidoException;
import trier.jovemdev.provaum.guilherme_monteiro.exceptions.CnpjJaExistenteException;
import trier.jovemdev.provaum.guilherme_monteiro.exceptions.NomeInvalidoException;
import trier.jovemdev.provaum.guilherme_monteiro.exceptions.QtdEstrelasInvalidoException;
import trier.jovemdev.provaum.guilherme_monteiro.exceptions.RestauranteNaoEncontradoException;

public interface RestauranteService {

	List<RestauranteDto> findAll();

	RestauranteDto findById(Long id) throws RestauranteNaoEncontradoException;

	RestauranteDto create(RestauranteDto restauranteDto)
			throws CnpjJaExistenteException, CnpjInvalidoException, QtdEstrelasInvalidoException, NomeInvalidoException;

	RestauranteDto update(RestauranteDto restauranteAtualizadoDto) throws RestauranteNaoEncontradoException,
			CnpjJaExistenteException, CnpjInvalidoException, QtdEstrelasInvalidoException, NomeInvalidoException;

	void delete(Long id) throws RestauranteNaoEncontradoException;
}
