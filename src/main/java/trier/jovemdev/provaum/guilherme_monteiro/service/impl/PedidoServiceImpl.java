package trier.jovemdev.provaum.guilherme_monteiro.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import trier.jovemdev.provaum.guilherme_monteiro.dto.PedidoDto;
import trier.jovemdev.provaum.guilherme_monteiro.dto.ReservaDto;
import trier.jovemdev.provaum.guilherme_monteiro.entity.PedidoEntity;
import trier.jovemdev.provaum.guilherme_monteiro.enums.StatusReservaEnum;
import trier.jovemdev.provaum.guilherme_monteiro.exceptions.*;
import trier.jovemdev.provaum.guilherme_monteiro.repository.PedidoRepository;
import trier.jovemdev.provaum.guilherme_monteiro.repository.custom.PedidoRepositoryCustom;
import trier.jovemdev.provaum.guilherme_monteiro.service.PedidoService;
import trier.jovemdev.provaum.guilherme_monteiro.service.ReservaService;
import trier.jovemdev.provaum.guilherme_monteiro.service.RestauranteService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class PedidoServiceImpl implements PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private PedidoRepositoryCustom pedidoRepositoryCustom;

    @Autowired
    private ReservaService reservaService;

    @Autowired
    private RestauranteService restauranteService;

    public PedidoDto findById(Long id) throws PedidoNaoEncontradoException {
        return new PedidoDto(pedidoRepository.findById(id).orElseThrow(() -> new PedidoNaoEncontradoException(id)));
    }

    public List<PedidoDto> findAll() {
        return pedidoRepository.findAll().stream().map(PedidoDto::new).toList();
    }

    public List<PedidoDto> findAllByRestauranteId(Long idRestaurante) throws RestauranteNaoEncontradoException {
        return pedidoRepositoryCustom.findAllByRestaurante(restauranteService.findById(idRestaurante).getId());
    }

    public PedidoDto create(PedidoDto pedidoDto) throws ReservaNaoEncontradaException, DescricaoInvalidaException, ReservaFinalizadaException {
        validaCriacao(pedidoDto);

        return new PedidoDto(pedidoRepository.save(new PedidoEntity(pedidoDto)));
    }

    public PedidoDto update(PedidoDto pedidoDto) throws ReservaNaoEncontradaException {
        validaPedido(pedidoDto);

        PedidoEntity entity = new PedidoEntity(findById(pedidoDto.getId()));

        entity.atualizaCampos(pedidoDto);

        return new PedidoDto(pedidoRepository.save(entity));
    }

    public void delete(Long id) throws PedidoNaoEncontradoException {
        pedidoRepository.delete(new PedidoEntity(findById(id)));
    }

    public List<PedidoDto> findAllByParameters(LocalDate data, BigDecimal valor, StatusReservaEnum statusReserva, Long clienteId) throws ClienteNaoEncontradoException {
        return pedidoRepositoryCustom.findAllByParameters(data, valor, statusReserva, clienteId);
    }

    private void validaPedido(PedidoDto pedidoDto) throws DescricaoInvalidaException, ReservaNaoEncontradaException {
        pedidoDto.setReserva(reservaService.findById(pedidoDto.getReserva().getId()));

        if (pedidoDto.getDescricao().isBlank()) {
            throw new DescricaoInvalidaException();
        }
    }

    private void validaCriacao(PedidoDto pedidoDto) throws DescricaoInvalidaException, ReservaNaoEncontradaException, ReservaFinalizadaException {
        validaPedido(pedidoDto);

        ReservaDto reservaDto = reservaService.findById(pedidoDto.getReserva().getId());

        if(reservaDto.getStatus() != StatusReservaEnum.AGENDADA || reservaDto.getDataReserva().isBefore(LocalDate.now())){
            throw new ReservaFinalizadaException(pedidoDto.getReserva().getId());
        }

    }
}
