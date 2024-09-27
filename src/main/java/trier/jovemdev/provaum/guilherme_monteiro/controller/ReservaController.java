package trier.jovemdev.provaum.guilherme_monteiro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import trier.jovemdev.provaum.guilherme_monteiro.dto.ReservaDto;
import trier.jovemdev.provaum.guilherme_monteiro.dto.ReservaTotalDto;
import trier.jovemdev.provaum.guilherme_monteiro.dto.UpdateStatusDto;
import trier.jovemdev.provaum.guilherme_monteiro.service.ReservaService;

import java.util.List;

@RestController
@RequestMapping("/reserva")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    @GetMapping
    public List<ReservaDto> findAll(){
        return reservaService.findAll();
    }

    @GetMapping("/restaurante/{id}")
    public List<ReservaDto> findAllByRestaurante(@PathVariable Long id){
        return reservaService.findAllByRestaurante(id);
    }

    @GetMapping("/{id}")
    public ReservaDto findById(@PathVariable Long id){
        return reservaService.findById(id);
    }

    @PostMapping
    public ReservaDto create(@RequestBody ReservaDto reservaDto){
        return reservaService.create(reservaDto);
    }

    @PutMapping
    public ReservaDto update(@RequestBody ReservaDto reservaDto){
        return reservaService.update(reservaDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        reservaService.delete(id);
    }

    @PutMapping("/status")
    public ReservaDto updateStatus(@RequestBody UpdateStatusDto updateStatusDto){
        return reservaService.updateStatus(updateStatusDto.getIdReserva(), updateStatusDto.getStatus());
    }

    @GetMapping("/restaurante/{id}/total")
    public List<ReservaTotalDto> findAllReservasTotalByRestaurante(@PathVariable Long id){
        return reservaService.findAllReservasTotalByRestaurante(id);
    }

    @GetMapping("/restaurante/total")
    public List<ReservaTotalDto> findAllReservasTotal(){
        return reservaService.findAllReservasTotal();
    }

    @GetMapping("/observacao/{observacao}")
    public List<ReservaDto> findByObservacao(@PathVariable String observacao){
        return reservaService.findByObservacao(observacao);
    }
}
