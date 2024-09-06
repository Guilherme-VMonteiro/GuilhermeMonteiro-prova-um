package trier.jovemdev.provaum.guilherme_monteiro.repository.custom;

import trier.jovemdev.provaum.guilherme_monteiro.dto.ClienteDto;
import trier.jovemdev.provaum.guilherme_monteiro.dto.PedidoDto;
import trier.jovemdev.provaum.guilherme_monteiro.enums.StatusReservaEnum;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface PedidoRepositoryCustom {

    List<PedidoDto> findAllByParameters(LocalDate data, BigDecimal valor, StatusReservaEnum statusReserva, Long clienteId);

    List<PedidoDto> findAllByRestaurante(Long idRestaurante);

    List<PedidoDto> findAllByIdReserva(Long idReserva);
}
