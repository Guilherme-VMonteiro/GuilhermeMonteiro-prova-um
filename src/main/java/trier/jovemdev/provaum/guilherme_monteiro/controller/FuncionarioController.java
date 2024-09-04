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

import trier.jovemdev.provaum.guilherme_monteiro.dto.FuncionarioDto;
import trier.jovemdev.provaum.guilherme_monteiro.service.FuncionarioService;

@RestController
@RequestMapping("/funcionario")
public class FuncionarioController {

	@Autowired
	private FuncionarioService service;

	@GetMapping
	public List<FuncionarioDto> findAll() {
		return service.findAll();
	}

	@GetMapping("/{id}")
	public FuncionarioDto findById(@PathVariable Long id) {
		return service.findById(id);
	}

	@PostMapping
	public FuncionarioDto create(@RequestBody FuncionarioDto funcionarioDto) {
		return service.create(funcionarioDto);
	}

	@PutMapping
	public FuncionarioDto update(@RequestBody FuncionarioDto funcionarioDto) {
		return service.update(funcionarioDto);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		service.delete(id);
	}
}
