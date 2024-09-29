package trier.jovemdev.provaum.guilherme_monteiro.repository.custom.impl;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import trier.jovemdev.provaum.guilherme_monteiro.dto.FuncionarioDto;
import trier.jovemdev.provaum.guilherme_monteiro.entity.QFuncionarioEntity;
import trier.jovemdev.provaum.guilherme_monteiro.entity.QRestauranteEntity;
import trier.jovemdev.provaum.guilherme_monteiro.repository.custom.FuncionarioRepositoryCustom;

import java.util.List;

@Repository
public class FuncionarioRepositoryCustomImpl implements FuncionarioRepositoryCustom {

    @PersistenceContext
    private EntityManager em;

    final QFuncionarioEntity funcionario = QFuncionarioEntity.funcionarioEntity;
    final QRestauranteEntity restaurante = QRestauranteEntity.restauranteEntity;

    public List<FuncionarioDto> findAllByRestauranteId(Long restauranteId) {
        JPAQuery<FuncionarioDto> query = new JPAQuery<>(em);

        return query
                .select(Projections.constructor(FuncionarioDto.class, funcionario))
                .from(funcionario)
                .join(funcionario.restaurante, restaurante)
                .where(restaurante.id.eq(restauranteId))
                .fetch();
    }
}
