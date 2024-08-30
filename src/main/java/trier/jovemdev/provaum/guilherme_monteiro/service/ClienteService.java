package trier.jovemdev.provaum.guilherme_monteiro.service;

import java.util.Optional;

import trier.jovemdev.provaum.guilherme_monteiro.dto.ClienteDto;
import trier.jovemdev.provaum.guilherme_monteiro.exceptions.ClienteInexistenteException;
import trier.jovemdev.provaum.guilherme_monteiro.exceptions.EmailExistenteException;

public interface ClienteService {
	
	Optional<ClienteDto> findById(Long id);

	ClienteDto create(ClienteDto clienteDto) throws EmailExistenteException;

	ClienteDto update(ClienteDto clienteDto) throws EmailExistenteException, ClienteInexistenteException;
}
