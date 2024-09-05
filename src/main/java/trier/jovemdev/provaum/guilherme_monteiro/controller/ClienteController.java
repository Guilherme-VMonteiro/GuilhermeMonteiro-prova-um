package trier.jovemdev.provaum.guilherme_monteiro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import trier.jovemdev.provaum.guilherme_monteiro.dto.ClienteDto;
import trier.jovemdev.provaum.guilherme_monteiro.service.ClienteService;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

	@Autowired
	private ClienteService service;

	@GetMapping
	public List<ClienteDto> findAll() {
		return service.findAll();
	}

	@GetMapping("/{id}")
	public ClienteDto findById(@PathVariable Long id) {
		return service.findById(id);
	}

	@PostMapping
	public ClienteDto create(@RequestBody ClienteDto ClienteDto) {
		return service.create(ClienteDto);
	}

	@PutMapping
	public ClienteDto update(@RequestBody ClienteDto ClienteDto) {
		return service.update(ClienteDto);
	}
	
	@PutMapping("/permissaoFlag/{id}")
	public void switchBlockClienteFlag(@PathVariable Long id) {
		service.switchBlockClienteFlag(id);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		service.delete(id);
	}
	
}
