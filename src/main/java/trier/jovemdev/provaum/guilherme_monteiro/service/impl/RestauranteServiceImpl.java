package trier.jovemdev.provaum.guilherme_monteiro.service.impl;

import java.util.List;
import java.util.Objects;
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
import trier.jovemdev.provaum.guilherme_monteiro.exceptions.ValorInvalidoEnumException;
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
	public RestauranteDto create(RestauranteDto restauranteDto) throws CnpjJaExistenteException, CnpjInvalidoException,
			QtdEstrelasInvalidoException, NomeInvalidoException, ValorInvalidoEnumException {

		validaCampos(restauranteDto);

		RestauranteEntity entity = new RestauranteEntity(restauranteDto);

		return new RestauranteDto(repository.save(entity));
	}

	@Override
	public RestauranteDto update(RestauranteDto restauranteAtualizadoDto)
			throws RestauranteNaoEncontradoException, CnpjJaExistenteException, CnpjInvalidoException,
			QtdEstrelasInvalidoException, NomeInvalidoException, ValorInvalidoEnumException {

		RestauranteEntity entity = new RestauranteEntity(findById(restauranteAtualizadoDto.getId()));

		validaCampos(restauranteAtualizadoDto);

		entity.atualizaCampos(restauranteAtualizadoDto);

		return new RestauranteDto(repository.save(entity));
	}

	@Override
	public void delete(Long id) throws RestauranteNaoEncontradoException {
		repository.delete(new RestauranteEntity(findById(id)));
	}

	private void validaCampos(RestauranteDto restauranteDto) throws CnpjJaExistenteException,
			CnpjInvalidoException, QtdEstrelasInvalidoException, NomeInvalidoException {
		validaNome(restauranteDto.getNome());
		validaCnpj(restauranteDto);
		validaQtdEstrelas(restauranteDto.getEstrelas());
	}

	private void validaCnpj(RestauranteDto restauranteDto) throws CnpjJaExistenteException, CnpjInvalidoException {

		if (restauranteDto.getCnpj().length() != 14 || !restauranteDto.getCnpj().matches("^\\d+$")) {
			if (restauranteDto.getCnpj().length() != 18
					|| !restauranteDto.getCnpj().matches("^\\d{2}\\.\\d{3}\\.\\d{3}/\\d{4}-\\d{2}$")) {
				throw new CnpjInvalidoException(restauranteDto.getCnpj());
			}
		}
		
		if (restauranteDto.getCnpj().length() == 18) {
			restauranteDto.setCnpj(restauranteDto.getCnpj().replace(".", "").replace("/", "").replace("-", ""));
		}

		Optional<RestauranteEntity> optionalRestaurante = repository.findByCnpj(restauranteDto.getCnpj());

		if (optionalRestaurante.isPresent() && !Objects.equals(optionalRestaurante.get().getId(), restauranteDto.getId())) {
			throw new CnpjJaExistenteException(restauranteDto.getCnpj());
		}
	}

	private void validaQtdEstrelas(Integer qtdEstrelas) throws QtdEstrelasInvalidoException {
		if (qtdEstrelas < 0 || qtdEstrelas > 3) {
			throw new QtdEstrelasInvalidoException();
		}
	}

	private void validaNome(String nome) throws NomeInvalidoException {
		if (nome.isEmpty() || nome.isBlank()) {
			throw new NomeInvalidoException();
		}
	}

}
