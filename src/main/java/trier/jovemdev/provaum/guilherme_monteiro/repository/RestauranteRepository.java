package trier.jovemdev.provaum.guilherme_monteiro.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import trier.jovemdev.provaum.guilherme_monteiro.entity.RestauranteEntity;

@Repository
public interface RestauranteRepository extends JpaRepository<RestauranteEntity, Long>{

	Optional<RestauranteEntity> findByCnpj(String cnpj);

}
