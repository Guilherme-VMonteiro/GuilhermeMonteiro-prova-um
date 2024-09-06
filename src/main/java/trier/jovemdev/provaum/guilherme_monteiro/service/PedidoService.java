package trier.jovemdev.provaum.guilherme_monteiro.service;

import trier.jovemdev.provaum.guilherme_monteiro.dto.ClienteDto;
import trier.jovemdev.provaum.guilherme_monteiro.dto.PedidoDto;
import trier.jovemdev.provaum.guilherme_monteiro.enums.StatusReservaEnum;
import trier.jovemdev.provaum.guilherme_monteiro.exceptions.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface PedidoService {

    PedidoDto findById(Long id) throws PedidoNaoEncontradoException;

    List<PedidoDto> findAll();

    List<PedidoDto> findAllByRestauranteId(Long idRestaurante) throws RestauranteNaoEncontradoException;

    PedidoDto create(PedidoDto pedidoDto) throws ReservaNaoEncontradaException, DescricaoInvalidaException, ReservaFinalizadaException;

    PedidoDto update(PedidoDto pedidoDto) throws ReservaNaoEncontradaException, DescricaoInvalidaException;

    void delete(Long id) throws PedidoNaoEncontradoException;

    List<PedidoDto> findAllByParameters(LocalDate data, BigDecimal valor, StatusReservaEnum statusReserva, Long clienteId) throws ClienteNaoEncontradoException;

    List<PedidoDto> findAllByIdReserva(Long idReserva) throws ReservaNaoEncontradaException;
}
