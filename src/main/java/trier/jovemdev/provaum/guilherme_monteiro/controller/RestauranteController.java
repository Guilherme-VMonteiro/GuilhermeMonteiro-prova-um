package trier.jovemdev.provaum.guilherme_monteiro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import trier.jovemdev.provaum.guilherme_monteiro.dto.RestauranteDto;
import trier.jovemdev.provaum.guilherme_monteiro.exceptions.ExcessaoPersonalizada;
import trier.jovemdev.provaum.guilherme_monteiro.exceptions.RestauranteNaoEncontradoException;
import trier.jovemdev.provaum.guilherme_monteiro.exceptions.TipoDeComidaInvalidoException;
import trier.jovemdev.provaum.guilherme_monteiro.service.RestauranteService;

@RestController
@RequestMapping("/restaurante")
public class RestauranteController {

	@Autowired
	private RestauranteService service;

	@GetMapping
	public List<RestauranteDto> findAll() {
		return service.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id) {
		try {
			return ResponseEntity.ok(service.findById(id));
		} catch (RestauranteNaoEncontradoException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getErrorMessage());
		}
	}

	@PostMapping()
	public ResponseEntity<?> create(@RequestBody RestauranteDto restauranteDto) {
		try {
			return ResponseEntity.ok(service.create(restauranteDto));
		} catch (ExcessaoPersonalizada e) {
			return ResponseEntity.badRequest().body(e.getErrorMessage());
		}
	}

	@PutMapping
	public ResponseEntity<?> update(@RequestBody RestauranteDto restauranteAtualizadoDto) {
		try {
			return ResponseEntity.ok(service.update(restauranteAtualizadoDto));
		} catch (RestauranteNaoEncontradoException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getErrorMessage());
		} catch (ExcessaoPersonalizada e) {
			return ResponseEntity.badRequest().body(e.getErrorMessage());
		} catch (HttpMessageNotReadableException e) {
			return ResponseEntity.badRequest().body(new TipoDeComidaInvalidoException());
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id){
		try {
			service.delete(id);
			return ResponseEntity.ok(null);
		} catch (RestauranteNaoEncontradoException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getErrorMessage());
		}
	}
}
