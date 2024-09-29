package trier.jovemdev.provaum.guilherme_monteiro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import trier.jovemdev.provaum.guilherme_monteiro.dto.FuncionarioDto;
import trier.jovemdev.provaum.guilherme_monteiro.service.FuncionarioService;

import java.util.List;

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

    @GetMapping("/restaurante/{id}")
    public List<FuncionarioDto> findAllByRestauranteId(@PathVariable Long id) {
        return service.findAllByRestauranteId(id);
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
