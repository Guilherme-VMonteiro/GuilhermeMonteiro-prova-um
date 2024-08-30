package trier.jovemdev.provaum.guilherme_monteiro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import trier.jovemdev.provaum.guilherme_monteiro.dto.ClienteDto;
import trier.jovemdev.provaum.guilherme_monteiro.exceptions.ExcessaoPersonalizada;
import trier.jovemdev.provaum.guilherme_monteiro.service.ClienteService;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

	@Autowired
	private ClienteService service;

	@PostMapping
	public ResponseEntity<?> create(@RequestBody ClienteDto clienteDto) {
		try {
			return ResponseEntity.ok(service.create(clienteDto));
		} catch (ExcessaoPersonalizada e) {
			return ResponseEntity.badRequest().body(e.getErrorMessage());
		}
	}

	@PutMapping
	public ResponseEntity<?> update(@RequestBody ClienteDto clienteDto) {
		try {
			return ResponseEntity.ok(service.update(clienteDto));
		} catch (ExcessaoPersonalizada e) {
			return ResponseEntity.badRequest().body(e.getErrorMessage());
		}
	}
}
