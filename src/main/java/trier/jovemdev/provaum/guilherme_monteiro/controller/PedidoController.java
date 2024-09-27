package trier.jovemdev.provaum.guilherme_monteiro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import trier.jovemdev.provaum.guilherme_monteiro.dto.PedidoDto;
import trier.jovemdev.provaum.guilherme_monteiro.enums.StatusReservaEnum;
import trier.jovemdev.provaum.guilherme_monteiro.service.PedidoService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping("/{id}")
    public PedidoDto findById(@PathVariable Long id) {
        return pedidoService.findById(id);
    }

    @GetMapping
    public List<PedidoDto> findAll() {
        return pedidoService.findAll();
    }

    @GetMapping("/restaurante/{id}")
    public List<PedidoDto> findAllByRestaurante(@PathVariable Long id) {
        return pedidoService.findAllByRestauranteId(id);
    }

    @PostMapping
    public PedidoDto create(@RequestBody PedidoDto pedidoDto) {
        return pedidoService.create(pedidoDto);
    }

    @PutMapping
    public PedidoDto update(@RequestBody PedidoDto pedidoDto) {
        return pedidoService.update(pedidoDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        pedidoService.delete(id);
    }

    @GetMapping("/busca")
    public List<PedidoDto> findAllByParameters(@RequestParam LocalDate data,
                                               @RequestParam(required = false) BigDecimal valor,
                                               @RequestParam(required = false) StatusReservaEnum status,
                                               @RequestParam(required = false) Long clienteId) {
        return pedidoService.findAllByParameters(data, valor, status, clienteId);
    }
}
