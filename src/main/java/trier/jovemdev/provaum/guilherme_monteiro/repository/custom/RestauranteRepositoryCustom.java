package trier.jovemdev.provaum.guilherme_monteiro.repository.custom;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import trier.jovemdev.provaum.guilherme_monteiro.dto.RestauranteDto;

public interface RestauranteRepositoryCustom {

    Page<RestauranteDto> findAll(Pageable pageable, String searchTerm);
}
