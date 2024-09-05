package trier.jovemdev.provaum.guilherme_monteiro.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import trier.jovemdev.provaum.guilherme_monteiro.dto.ClienteDto;
import trier.jovemdev.provaum.guilherme_monteiro.entity.ClienteEntity;
import trier.jovemdev.provaum.guilherme_monteiro.exceptions.ClienteNaoEncontradoException;
import trier.jovemdev.provaum.guilherme_monteiro.exceptions.DataDeNascimentoInvalidaException;
import trier.jovemdev.provaum.guilherme_monteiro.exceptions.NomeInvalidoException;
import trier.jovemdev.provaum.guilherme_monteiro.exceptions.RestauranteNaoEncontradoException;
import trier.jovemdev.provaum.guilherme_monteiro.repository.ClienteRepository;
import trier.jovemdev.provaum.guilherme_monteiro.repository.custom.ClienteRepositoryCustom;
import trier.jovemdev.provaum.guilherme_monteiro.repository.custom.impl.ClienteRepositoryCustomImpl;
import trier.jovemdev.provaum.guilherme_monteiro.service.ClienteService;
import trier.jovemdev.provaum.guilherme_monteiro.service.RestauranteService;

@Service
public class ClienteServiceImpl implements ClienteService {

	@Autowired
	private ClienteRepository repository;

	@Autowired
	private RestauranteService restauranteService;
    @Autowired
    private ClienteRepositoryCustom clienteRepositoryCustom;

	public List<ClienteDto> findAll() {
		return repository.findAll().stream().map(ClienteDto::new).toList();
	}

	public ClienteDto findById(Long id) throws ClienteNaoEncontradoException {
		return new ClienteDto(repository.findById(id).orElseThrow(() -> new ClienteNaoEncontradoException(id)));
	}

	public ClienteDto create(ClienteDto clienteDto) throws DataDeNascimentoInvalidaException {

		validaDataNascimento(clienteDto.getDataNascimento());

		clienteDto.setDataCadastro(LocalDate.now());
		clienteDto.setQuantidadeReservas(0);
		clienteDto.setValorGasto(BigDecimal.ZERO);
		clienteDto.setBloqueado(false);

		return new ClienteDto(repository.save(new ClienteEntity(clienteDto)));
	}

	public ClienteDto update(ClienteDto clienteAtualizadoDto)
			throws ClienteNaoEncontradoException, DataDeNascimentoInvalidaException {

		validaDataNascimento(clienteAtualizadoDto.getDataNascimento());

		ClienteEntity entity = new ClienteEntity(findById(clienteAtualizadoDto.getId()));

		entity.atualizaCampos(clienteAtualizadoDto);

		return new ClienteDto(repository.save(entity));
	}

	public void delete(Long id) throws ClienteNaoEncontradoException {
		repository.delete(new ClienteEntity(findById(id)));
	}

	public List<ClienteDto> findClientesWithMostReservas(Long idRestaurante) throws RestauranteNaoEncontradoException {
		restauranteService.findById(idRestaurante);

		return clienteRepositoryCustom.findClientesWithMostReservas();
	}

	public List<ClienteDto> findClientesWithMostValueSpent(Long idRestaurante) throws RestauranteNaoEncontradoException{
		restauranteService.findById(idRestaurante);

		return clienteRepositoryCustom.findClientesWithMostValueSpent();
	}

	// PAGINADO
	public List<ClienteDto> findReservasConcluidasAndTotalSpentByName(Long idRestaurante) throws NomeInvalidoException, RestauranteNaoEncontradoException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<ClienteDto> findAllWithOurBestPedido(Long idRestaurante) throws RestauranteNaoEncontradoException{
		// TODO Auto-generated method stub
		return null;
	}

	public void switchBlockClienteFlag(Long id) throws ClienteNaoEncontradoException {
		ClienteDto clienteDto = findById(id);
		clienteDto.setBloqueado(!clienteDto.getBloqueado());
		repository.save(new ClienteEntity(clienteDto));
	}

	private void validaDataNascimento(LocalDate dataNascimento) throws DataDeNascimentoInvalidaException {
		int idade = Period.between(dataNascimento, LocalDate.now()).getYears();
		if (idade > 100 || idade < 12) {
			throw new DataDeNascimentoInvalidaException(dataNascimento);
		}
	}

}
