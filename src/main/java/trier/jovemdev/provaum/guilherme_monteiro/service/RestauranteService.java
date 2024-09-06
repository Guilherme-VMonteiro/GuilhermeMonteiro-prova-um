package trier.jovemdev.provaum.guilherme_monteiro.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import trier.jovemdev.provaum.guilherme_monteiro.dto.RestauranteDto;
import trier.jovemdev.provaum.guilherme_monteiro.exceptions.CnpjInvalidoException;
import trier.jovemdev.provaum.guilherme_monteiro.exceptions.CnpjJaExistenteException;
import trier.jovemdev.provaum.guilherme_monteiro.exceptions.NomeInvalidoException;
import trier.jovemdev.provaum.guilherme_monteiro.exceptions.QtdEstrelasInvalidoException;
import trier.jovemdev.provaum.guilherme_monteiro.exceptions.RestauranteNaoEncontradoException;
import trier.jovemdev.provaum.guilherme_monteiro.exceptions.ValorInvalidoEnumException;

public interface RestauranteService {

	Page<RestauranteDto> findAll(Pageable pageable, String searchTerm);

	RestauranteDto findById(Long id) throws RestauranteNaoEncontradoException;

	RestauranteDto create(RestauranteDto restauranteDto)
			throws CnpjJaExistenteException, CnpjInvalidoException, QtdEstrelasInvalidoException, NomeInvalidoException, ValorInvalidoEnumException;

	RestauranteDto update(RestauranteDto restauranteAtualizadoDto) throws RestauranteNaoEncontradoException,
			CnpjJaExistenteException, CnpjInvalidoException, QtdEstrelasInvalidoException, NomeInvalidoException, ValorInvalidoEnumException;

	void delete(Long id) throws RestauranteNaoEncontradoException;
}
