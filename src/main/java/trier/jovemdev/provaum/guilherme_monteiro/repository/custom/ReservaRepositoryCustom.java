package trier.jovemdev.provaum.guilherme_monteiro.repository.custom;

import trier.jovemdev.provaum.guilherme_monteiro.dto.ReservaDto;
import trier.jovemdev.provaum.guilherme_monteiro.dto.ReservaTotalDto;
import trier.jovemdev.provaum.guilherme_monteiro.entity.ReservaEntity;
import trier.jovemdev.provaum.guilherme_monteiro.entity.ReservaTotalEntity;

import java.util.List;

public interface ReservaRepositoryCustom {

    List<ReservaEntity> findAllByRestauranteId(Long restauranteId);

    Long countInadimplenciasByCliente(Long clienteId);

    Long countReservasCanceladasNoMesByCliente(Long clienteId);

    List<ReservaTotalEntity> findAllReservasTotal();

    List<ReservaEntity> findByObervacao(String obervacao);

    List<ReservaDto> atualizarAutomaticamenteReservasParaInadimplente();

    List<ReservaDto> atualizarAutomaticamenteReservasParaConcluida();
}
