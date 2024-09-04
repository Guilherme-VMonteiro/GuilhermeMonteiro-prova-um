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

import trier.jovemdev.provaum.guilherme_monteiro.dto.RestauranteDto;
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
	public RestauranteDto findById(@PathVariable Long id) {
		return service.findById(id);
	}

	@PostMapping()
	public RestauranteDto create(@RequestBody RestauranteDto restauranteDto) {
		return service.create(restauranteDto);
	}

	@PutMapping
	public RestauranteDto update(@RequestBody RestauranteDto restauranteAtualizadoDto) {
		return service.update(restauranteAtualizadoDto);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id){
		service.delete(id);
	}
}
