package trier.jovemdev.provaum.guilherme_monteiro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import trier.jovemdev.provaum.guilherme_monteiro.dto.ReservaDto;
import trier.jovemdev.provaum.guilherme_monteiro.exceptions.ClienteInexistenteException;
import trier.jovemdev.provaum.guilherme_monteiro.exceptions.ExcessaoPersonalizada;
import trier.jovemdev.provaum.guilherme_monteiro.service.ReservaService;

@RestController
@RequestMapping("/reserva")
public class ReservaController {

	@Autowired
	private ReservaService service;

	@GetMapping
	public List<ReservaDto> findAll() {
		return service.findAll();
	}

	@GetMapping("/cliente/{clienteId}")
	public ResponseEntity<?> findAllByCliente(@PathVariable Long clienteId) {
		try {
			return ResponseEntity.ok(service.findAllByCliente(clienteId));
		} catch (ClienteInexistenteException e) {
			return ResponseEntity.badRequest().body(e.getErrorMessage());
		}
	}

	@GetMapping("/disponibilidade")
	public ResponseEntity<?> findDisponibility(@RequestBody ReservaDto reservaDto) {
		try {
			service.findDisponibility(reservaDto.getNumeroMesa(), reservaDto.getDataReserva());
			return ResponseEntity.ok(null);
		} catch (ExcessaoPersonalizada e) {
			return ResponseEntity.badRequest().body(e.getErrorMessage());
		}
	}

	@PostMapping
	public ResponseEntity<?> create(@RequestBody ReservaDto reservaDto) {
		try {
			return ResponseEntity.ok(service.create(reservaDto));
		} catch (ExcessaoPersonalizada e) {
			return ResponseEntity.badRequest().body(e.getErrorMessage());
		}
	}

	@PutMapping
	public ResponseEntity<?> updateStatus(@RequestBody ReservaDto reservaDto) {
		try {
			return ResponseEntity.ok(service.updateStatus(reservaDto.getId(), reservaDto.getStatus()));
		} catch (ExcessaoPersonalizada e) {
			return ResponseEntity.badRequest().body(e.getErrorMessage());
		}
	}
}
