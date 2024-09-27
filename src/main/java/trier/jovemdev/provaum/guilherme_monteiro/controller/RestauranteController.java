package trier.jovemdev.provaum.guilherme_monteiro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import trier.jovemdev.provaum.guilherme_monteiro.dto.RestauranteDto;
import trier.jovemdev.provaum.guilherme_monteiro.service.RestauranteService;

@RestController
@RequestMapping("/restaurante")
public class RestauranteController {

    @Autowired
    private RestauranteService service;

    @GetMapping
    public Page<RestauranteDto> findAll(
            @RequestParam(defaultValue = "0", required = false) Integer page,
            @RequestParam(defaultValue = "10", required = false) Integer size,
            @RequestParam(required = false) String searchTerm
    ) {
        return service.findAll(Pageable.ofSize(size).withPage(page), searchTerm);
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
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
