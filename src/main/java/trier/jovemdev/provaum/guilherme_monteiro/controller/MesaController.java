package trier.jovemdev.provaum.guilherme_monteiro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import trier.jovemdev.provaum.guilherme_monteiro.dto.MesaDto;
import trier.jovemdev.provaum.guilherme_monteiro.service.MesaService;

import java.util.List;

@RestController
@RequestMapping("/mesa")
public class MesaController {

    @Autowired
    private MesaService mesaService;

    @GetMapping("/restaurante/{id}")
    public List<MesaDto> findAllByRestaurante(@PathVariable("id") Long idRestaurante) {
        return mesaService.findAllByRestaurante(idRestaurante);
    }

    @GetMapping("/{id}")
    public MesaDto findById(@PathVariable("id") Long id) {
        return mesaService.findById(id);
    }

    @PostMapping
    public MesaDto create(@RequestBody MesaDto mesaDto) {
        return mesaService.create(mesaDto);
    }

    @PutMapping
    public MesaDto update(@RequestBody MesaDto mesaDto) {
        return mesaService.update(mesaDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        mesaService.delete(id);
    }
}
