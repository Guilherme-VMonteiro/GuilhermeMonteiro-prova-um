package trier.jovemdev.provaum.guilherme_monteiro.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import trier.jovemdev.provaum.guilherme_monteiro.dto.ClienteDto;
import trier.jovemdev.provaum.guilherme_monteiro.entity.ClienteEntity;
import trier.jovemdev.provaum.guilherme_monteiro.exceptions.ClienteInexistenteException;
import trier.jovemdev.provaum.guilherme_monteiro.exceptions.EmailExistenteException;
import trier.jovemdev.provaum.guilherme_monteiro.repository.ClienteRepository;
import trier.jovemdev.provaum.guilherme_monteiro.service.ClienteService;

@Service
public class ClienteServiceImpl implements ClienteService {

	@Autowired
	private ClienteRepository repository;

	public Optional<ClienteDto> findById(Long id) {
		
		Optional<ClienteEntity> clienteOptional = repository.findById(id);
		
		return clienteOptional.isPresent() ? Optional.of(new ClienteDto(clienteOptional.get())) : Optional.empty();
	}
	
	public ClienteDto create(ClienteDto clienteDto) throws EmailExistenteException {
		validaEmail(clienteDto.getEmail());

		return new ClienteDto(repository.save(new ClienteEntity(clienteDto)));
	}

	public ClienteDto update(ClienteDto clienteDto) throws EmailExistenteException, ClienteInexistenteException {
		Optional<ClienteEntity> clienteOptional = repository.findById(clienteDto.getId());

		ClienteEntity cliente = clienteOptional.orElseThrow(() -> new ClienteInexistenteException(clienteDto.getId()));

		validaEmail(clienteDto.getEmail());

		cliente.atualizaCliente(clienteDto);

		return new ClienteDto(repository.save(cliente));
	}

	private void validaEmail(String email) throws EmailExistenteException {
		if (repository.findByEmail(email).isPresent()) {
			throw new EmailExistenteException(email);
		}
	}

}
