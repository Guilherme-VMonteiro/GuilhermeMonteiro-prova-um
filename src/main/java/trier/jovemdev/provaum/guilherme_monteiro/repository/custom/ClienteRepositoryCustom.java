package trier.jovemdev.provaum.guilherme_monteiro.repository.custom;

import trier.jovemdev.provaum.guilherme_monteiro.dto.ClienteDto;
import trier.jovemdev.provaum.guilherme_monteiro.dto.ClienteTotalConcluidoDto;

import java.util.List;

public interface ClienteRepositoryCustom {

    List<ClienteDto> findClientesWithMostReservas();

    List<ClienteDto> findClientesWithMostValueSpent();

    List<ClienteTotalConcluidoDto> findAllClienteTotalConcluidoDto(Long idRestaurante, String nomeCliente);
}
