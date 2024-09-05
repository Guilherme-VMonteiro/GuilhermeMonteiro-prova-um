package trier.jovemdev.provaum.guilherme_monteiro.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import trier.jovemdev.provaum.guilherme_monteiro.entity.MesaEntity;

@Repository
public interface MesaRepository extends JpaRepository<MesaEntity, Long>, QuerydslPredicateExecutor<MesaEntity> {

	List<MesaEntity> findAllByRestauranteId(Long restauranteId);
	
	Optional<MesaEntity> findByIdAndRestauranteId(Long id, Long restauranteId);
}
