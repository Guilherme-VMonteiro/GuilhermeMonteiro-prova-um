package trier.jovemdev.provaum.guilherme_monteiro.repository.custom.impl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import trier.jovemdev.provaum.guilherme_monteiro.dto.RestauranteDto;
import trier.jovemdev.provaum.guilherme_monteiro.entity.QRestauranteEntity;
import trier.jovemdev.provaum.guilherme_monteiro.repository.custom.RestauranteRepositoryCustom;

import java.util.Objects;

@Repository
public class RestauranteRepositoryCustomImpl implements RestauranteRepositoryCustom {

    @PersistenceContext
    private EntityManager em;

    final QRestauranteEntity restaurante = QRestauranteEntity.restauranteEntity;

    public Page<RestauranteDto> findAll(Pageable pageable, String searchTerm) {
        BooleanBuilder condicoes = new BooleanBuilder();

        if(Objects.nonNull(searchTerm) && !searchTerm.isEmpty()) {
            condicoes.and(restaurante.nome.containsIgnoreCase(searchTerm));
        }

        JPAQuery<RestauranteDto> query = new JPAQuery<>(em);

        query
                .select(Projections.constructor(RestauranteDto.class, restaurante))
                .from(restaurante)
                .where(condicoes)
                .orderBy(restaurante.id.desc());

        query.limit(pageable.getPageSize());
        query.offset(pageable.getOffset());

        return new PageImpl<>(query.fetch(), pageable, query.fetchCount());
    }
}
