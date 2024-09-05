package trier.jovemdev.provaum.guilherme_monteiro.service;

import trier.jovemdev.provaum.guilherme_monteiro.dto.ReservaDto;
import trier.jovemdev.provaum.guilherme_monteiro.dto.ReservaTotalDto;
import trier.jovemdev.provaum.guilherme_monteiro.enums.StatusReservaEnum;
import trier.jovemdev.provaum.guilherme_monteiro.exceptions.*;

import java.util.List;

public interface ReservaService {

    List<ReservaDto> findAll();

    List<ReservaDto> findAllByRestaurante(Long idRestaurante) throws RestauranteNaoEncontradoException;

    ReservaDto findById(Long id) throws ReservaNaoEncontradaException;

    ReservaDto create(ReservaDto reservaDto) throws ClienteNaoEncontradoException, MesaNaoEncontradaException, DataInvalidaException, ReservaBloqueadaException, ClienteBloqueadoException;

    ReservaDto update(ReservaDto reservaDto) throws ClienteNaoEncontradoException, MesaNaoEncontradaException, DataInvalidaException, ConclusaoInvalidaException, CancelamentoInvalidoException;

    void delete(Long id) throws ReservaNaoEncontradaException;

    ReservaDto updateStatus(Long idReserva, StatusReservaEnum status) throws ReservaNaoEncontradaException, ConclusaoInvalidaException, CancelamentoInvalidoException;

    List<ReservaTotalDto> findAllReservasTotalByRestaurante(Long idRestaurante) throws RestauranteNaoEncontradoException;

    List<ReservaTotalDto> findAllReservasTotal();

    List<ReservaDto> findByObservacao(String observacao);

    void concluirReservasNaoFinalizadas();


}
