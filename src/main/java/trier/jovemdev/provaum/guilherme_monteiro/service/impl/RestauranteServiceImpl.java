package trier.jovemdev.provaum.guilherme_monteiro.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import trier.jovemdev.provaum.guilherme_monteiro.dto.RestauranteDto;
import trier.jovemdev.provaum.guilherme_monteiro.entity.RestauranteEntity;
import trier.jovemdev.provaum.guilherme_monteiro.exceptions.*;
import trier.jovemdev.provaum.guilherme_monteiro.repository.RestauranteRepository;
import trier.jovemdev.provaum.guilherme_monteiro.repository.custom.RestauranteRepositoryCustom;
import trier.jovemdev.provaum.guilherme_monteiro.service.RestauranteService;

import java.util.Objects;
import java.util.Optional;

@Service
public class RestauranteServiceImpl implements RestauranteService {

	@Autowired
	private RestauranteRepository repository;

	@Autowired
	private RestauranteRepositoryCustom repositoryCustom;

	@Override
	public Page<RestauranteDto> findAll(Pageable pageable, String searchTerm) {
		return repositoryCustom.findAll(pageable, searchTerm);
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
