package trier.jovemdev.provaum.guilherme_monteiro.service;

import java.util.List;

import trier.jovemdev.provaum.guilherme_monteiro.dto.MesaDto;
import trier.jovemdev.provaum.guilherme_monteiro.exceptions.MesaNaoEncontradaException;
import trier.jovemdev.provaum.guilherme_monteiro.exceptions.RestauranteNaoEncontradoException;

public interface MesaService {
	
	List<MesaDto> findAllByRestaurante(Long idRestaurante) throws RestauranteNaoEncontradoException;
	
	MesaDto findById(Long id) throws MesaNaoEncontradaException;

	MesaDto create(MesaDto mesaDto) throws RestauranteNaoEncontradoException;
	
	MesaDto update(MesaDto mesaDto) throws RestauranteNaoEncontradoException, MesaNaoEncontradaException;
	
	void delete(Long idMesa) throws MesaNaoEncontradaException;
	
	List<MesaDto> findAllAvailableByRestaurante(Long idRestaurante) throws RestauranteNaoEncontradoException;
}
