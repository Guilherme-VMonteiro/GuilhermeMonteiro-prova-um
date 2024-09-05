package trier.jovemdev.provaum.guilherme_monteiro.repository.custom.impl;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import trier.jovemdev.provaum.guilherme_monteiro.dto.ClienteDto;
import trier.jovemdev.provaum.guilherme_monteiro.entity.ClienteEntity;
import trier.jovemdev.provaum.guilherme_monteiro.entity.QClienteEntity;
import trier.jovemdev.provaum.guilherme_monteiro.entity.QPedidoEntity;
import trier.jovemdev.provaum.guilherme_monteiro.entity.QReservaEntity;
import trier.jovemdev.provaum.guilherme_monteiro.repository.custom.ClienteRepositoryCustom;

import java.util.List;

@Repository
public class ClienteRepositoryCustomImpl implements ClienteRepositoryCustom {

    @PersistenceContext
    private EntityManager em;

    final QClienteEntity cliente = QClienteEntity.clienteEntity;
    final QReservaEntity reserva = QReservaEntity.reservaEntity;


    public List<ClienteDto> findClientesWithMostReservas() {
        JPAQuery<ClienteEntity> firstQuery = new JPAQuery<>(em);

        List<Long> clientes = firstQuery
                .select(cliente.id)
                .from(cliente)
                .innerJoin(reserva.cliente, cliente)
                .groupBy(cliente.id)
                .orderBy(cliente.count().desc())
                .limit(10)
                .fetch();

        JPAQuery<ClienteDto> secondQuery = new JPAQuery<>(em);

        return secondQuery
                .select(Projections.constructor(ClienteDto.class, cliente))
                .from(cliente)
                .where(cliente.id.in(clientes)).fetch();
    }

    public List<ClienteDto> findClientesWithMostValueSpent() {
        JPAQuery<ClienteDto> query = new JPAQuery<>(em);

        query
                .select(Projections.constructor(ClienteDto.class, cliente))
                .from(cliente)
                .orderBy(cliente.valorGasto.desc())
                .limit(10);

        return query.fetch();

    }
}
