package trier.jovemdev.provaum.guilherme_monteiro.repository.custom.impl;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import trier.jovemdev.provaum.guilherme_monteiro.dto.ClienteDto;
import trier.jovemdev.provaum.guilherme_monteiro.dto.ClienteTotalConcluidoDto;
import trier.jovemdev.provaum.guilherme_monteiro.entity.ClienteEntity;
import trier.jovemdev.provaum.guilherme_monteiro.entity.QClienteEntity;
import trier.jovemdev.provaum.guilherme_monteiro.entity.QPedidoEntity;
import trier.jovemdev.provaum.guilherme_monteiro.entity.QReservaEntity;
import trier.jovemdev.provaum.guilherme_monteiro.enums.StatusReservaEnum;
import trier.jovemdev.provaum.guilherme_monteiro.repository.custom.ClienteRepositoryCustom;

import java.util.List;

@Repository
public class ClienteRepositoryCustomImpl implements ClienteRepositoryCustom {

    @PersistenceContext
    private EntityManager em;

    final QClienteEntity cliente = QClienteEntity.clienteEntity;
    final QReservaEntity reserva = QReservaEntity.reservaEntity;
    final QPedidoEntity pedido = QPedidoEntity.pedidoEntity;

    public List<ClienteDto> findClientesWithMostReservas() {
        JPAQuery<ClienteEntity> query = new JPAQuery<>(em);

        return query
                .select(Projections.constructor(ClienteDto.class, cliente))
                .from(cliente)
                .join(cliente.reservas, reserva)
                .where(reserva.status.eq(StatusReservaEnum.CONCLUIDA))
                .groupBy(cliente.id)
                .orderBy(cliente.count().desc())
                .limit(10)
                .fetch();
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

    public List<ClienteTotalConcluidoDto> findAllClienteTotalConcluidoDto(Long idRestaurante, String nomeCliente) {
        JPAQuery<ClienteTotalConcluidoDto> query = new JPAQuery<>(em);

        query
                .select(Projections.constructor(
                        ClienteTotalConcluidoDto.class,
                        Projections.constructor(ClienteDto.class, cliente),
                        reserva.cliente.count(),
                        cliente.valorGasto
                ))
                .from(cliente)
                .innerJoin(cliente.reservas, reserva)
                .leftJoin(reserva.pedidos, pedido)
                .where(cliente.restaurante.id.eq(idRestaurante).and(cliente.nome.likeIgnoreCase("%" + nomeCliente + "%")))
                .groupBy(cliente.id);

        return query.fetch();
    }
}
