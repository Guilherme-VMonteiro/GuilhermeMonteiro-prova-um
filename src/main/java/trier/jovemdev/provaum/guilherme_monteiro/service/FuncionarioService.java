package trier.jovemdev.provaum.guilherme_monteiro.service;

import java.util.List;

import trier.jovemdev.provaum.guilherme_monteiro.dto.FuncionarioDto;
import trier.jovemdev.provaum.guilherme_monteiro.exceptions.CargaHorariaInvalidaException;
import trier.jovemdev.provaum.guilherme_monteiro.exceptions.DataDeNascimentoInvalidaException;
import trier.jovemdev.provaum.guilherme_monteiro.exceptions.FuncionarioNaoEncontradoException;
import trier.jovemdev.provaum.guilherme_monteiro.exceptions.RestauranteNaoEncontradoException;
import trier.jovemdev.provaum.guilherme_monteiro.exceptions.SalarioInvalidoException;

public interface FuncionarioService {
	
	List<FuncionarioDto> findAll();
	
	FuncionarioDto findById(Long id) throws FuncionarioNaoEncontradoException;
	
	FuncionarioDto create(FuncionarioDto funcionarioDto) throws DataDeNascimentoInvalidaException, CargaHorariaInvalidaException, SalarioInvalidoException, RestauranteNaoEncontradoException;
	
	FuncionarioDto update(FuncionarioDto funcionarioDto) throws DataDeNascimentoInvalidaException, CargaHorariaInvalidaException, SalarioInvalidoException, RestauranteNaoEncontradoException;
	
	void delete(Long id) throws FuncionarioNaoEncontradoException;
}
