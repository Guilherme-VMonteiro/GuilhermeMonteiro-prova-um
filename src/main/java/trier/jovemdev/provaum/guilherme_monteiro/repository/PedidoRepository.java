package trier.jovemdev.provaum.guilherme_monteiro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;
import trier.jovemdev.provaum.guilherme_monteiro.entity.PedidoEntity;

@Repository
public interface PedidoRepository extends JpaRepository<PedidoEntity, Long>, QuerydslPredicateExecutor<PedidoEntity> {
}
