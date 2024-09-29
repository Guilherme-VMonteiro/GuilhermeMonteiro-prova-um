package trier.jovemdev.provaum.guilherme_monteiro.repository.custom;

import trier.jovemdev.provaum.guilherme_monteiro.dto.FuncionarioDto;

import java.util.List;

public interface FuncionarioRepositoryCustom {

    List<FuncionarioDto> findAllByRestauranteId(Long restauranteId);
}
