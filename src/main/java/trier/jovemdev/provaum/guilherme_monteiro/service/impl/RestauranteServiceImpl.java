package trier.jovemdev.provaum.guilherme_monteiro.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import trier.jovemdev.provaum.guilherme_monteiro.dto.RestauranteDto;
import trier.jovemdev.provaum.guilherme_monteiro.entity.RestauranteEntity;
import trier.jovemdev.provaum.guilherme_monteiro.exceptions.CnpjInvalidoException;
import trier.jovemdev.provaum.guilherme_monteiro.exceptions.CnpjJaExistenteException;
import trier.jovemdev.provaum.guilherme_monteiro.exceptions.NomeInvalidoException;
import trier.jovemdev.provaum.guilherme_monteiro.exceptions.QtdEstrelasInvalidoException;
import trier.jovemdev.provaum.guilherme_monteiro.exceptions.RestauranteNaoEncontradoException;
import trier.jovemdev.provaum.guilherme_monteiro.repository.RestauranteRepository;
import trier.jovemdev.provaum.guilherme_monteiro.service.RestauranteService;

@Service
public class RestauranteServiceImpl implements RestauranteService {

	@Autowired
	private RestauranteRepository repository;

	@Override
	public List<RestauranteDto> findAll() {
		return repository.findAll().stream().map(RestauranteDto::new).toList();
	}

	@Override
	public RestauranteDto findById(Long id) throws RestauranteNaoEncontradoException {
		return new RestauranteDto(repository.findById(id).orElseThrow(() -> new RestauranteNaoEncontradoException(id)));
	}

	@Override
	public RestauranteDto create(RestauranteDto restauranteDto)
			throws CnpjJaExistenteException, CnpjInvalidoException, QtdEstrelasInvalidoException, NomeInvalidoException {

		validaCampos(restauranteDto);

		RestauranteEntity entity = new RestauranteEntity(restauranteDto);

		return new RestauranteDto(repository.save(entity));
	}

	@Override
	public RestauranteDto update(RestauranteDto restauranteAtualizadoDto) throws RestauranteNaoEncontradoException,
			CnpjJaExistenteException, CnpjInvalidoException, QtdEstrelasInvalidoException, NomeInvalidoException {

		RestauranteEntity entity = new RestauranteEntity(findById(restauranteAtualizadoDto.getId()));

		validaCampos(restauranteAtualizadoDto);

		entity.atualizaCampos(restauranteAtualizadoDto);

		return new RestauranteDto(repository.save(entity));
	}

	@Override
	public void delete(Long id) throws RestauranteNaoEncontradoException {
		repository.delete(new RestauranteEntity(findById(id)));
	}

	private void validaCampos(RestauranteDto restauranteDto)
			throws CnpjJaExistenteException, CnpjInvalidoException, QtdEstrelasInvalidoException, NomeInvalidoException{
		validaNome(restauranteDto.getNome());
		validaCnpj(restauranteDto);
		validaQtdEstrelas(restauranteDto.getEstrelas());
	}

	private void validaCnpj(RestauranteDto restauranteDto) throws CnpjJaExistenteException, CnpjInvalidoException {
		if (restauranteDto.getCnpj().length() != 14 || !restauranteDto.getCnpj().matches("^\\d+$")) {
			throw new CnpjInvalidoException(restauranteDto.getCnpj());
		}
		
		Optional<RestauranteEntity> optionalRestaurante = repository.findByCnpj(restauranteDto.getCnpj());
		
		if (optionalRestaurante.isPresent() && optionalRestaurante.get().getId() != restauranteDto.getId()) {
			throw new CnpjJaExistenteException(restauranteDto.getCnpj());
		}
	}

	private void validaQtdEstrelas(Integer qtdEstrelas) throws QtdEstrelasInvalidoException {
		if (qtdEstrelas < 0 || qtdEstrelas > 3) {
			throw new QtdEstrelasInvalidoException();
		}
	}
	
	private void validaNome(String nome) throws NomeInvalidoException{
		if(nome.isEmpty() || nome.isBlank()) {
			throw new NomeInvalidoException();
		}
	}

}
