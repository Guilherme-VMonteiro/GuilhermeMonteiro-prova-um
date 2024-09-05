package trier.jovemdev.provaum.guilherme_monteiro.repository.custom.impl;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import trier.jovemdev.provaum.guilherme_monteiro.dto.*;
import trier.jovemdev.provaum.guilherme_monteiro.entity.*;
import trier.jovemdev.provaum.guilherme_monteiro.enums.StatusReservaEnum;
import trier.jovemdev.provaum.guilherme_monteiro.repository.custom.ReservaRepositoryCustom;

import java.time.LocalDate;
import java.util.List;

@Repository
public class ReservaRepositoryImpl implements ReservaRepositoryCustom {

    @PersistenceContext
    private EntityManager em;

    final QRestauranteEntity restaurante = QRestauranteEntity.restauranteEntity;
    final QReservaEntity reserva = QReservaEntity.reservaEntity;
    final QClienteEntity cliente = QClienteEntity.clienteEntity;
    final QMesaEntity mesa = QMesaEntity.mesaEntity;
    final QPedidoEntity pedido = QPedidoEntity.pedidoEntity;

    public List<ReservaEntity> findAllByRestauranteId(Long restauranteId) {
        JPAQuery<ReservaEntity> query = new JPAQuery<>(em);

        query
                .select(reserva)
                .from(reserva)
                .join(reserva.mesa, mesa)
                .join(reserva.cliente, cliente)
                .where(cliente.restaurante.id.eq(restauranteId));

        return query.fetch();
    }

    public Long countInadimplenciasByCliente(Long clienteId) {
        JPAQuery<Long> query = new JPAQuery<>(em);

        query
                .select(reserva.count())
                .from(reserva)
                .where(reserva.cliente.id.eq(clienteId).and(reserva.status.eq(StatusReservaEnum.INADIMPLENTE)));

        return query.fetchOne();
    }

    public Long countReservasCanceladasNoMesByCliente(Long clienteId) {
        JPAQuery<Long> query = new JPAQuery<>(em);

        LocalDate mesAtual = LocalDate.now().minusDays(LocalDate.now().getDayOfMonth() - 1);

        query
                .select(reserva.count())
                .from(reserva)
                .where(reserva.cliente.id.eq(clienteId)
                        .and(reserva.dataReserva.between(mesAtual, mesAtual.plusMonths(1)))
                        .and(reserva.status.eq(StatusReservaEnum.CANCELADA)));

        return query.fetchOne();
    }

    public List<ReservaTotalEntity> findAllReservasTotal() {

        JPAQuery<ReservaTotalEntity> query = new JPAQuery<>(em);

        query
                .select(Projections.constructor(ReservaTotalEntity.class,
                        reserva,
                        pedido.valor.sum()))
                .from(reserva)
                .leftJoin(reserva.pedidos, pedido)
                .groupBy(reserva.id);

        return query.fetch();
    }

    public List<ReservaEntity> findByObervacao(String obervacao) {
        JPAQuery<ReservaEntity> query = new JPAQuery<>(em);

        query
                .select(reserva)
                .from(reserva)
                .leftJoin(reserva.cliente, cliente)
                .leftJoin(reserva.mesa, mesa)
                .leftJoin(reserva.pedidos, pedido)
                .where(reserva.observacao.like("%" + obervacao + "%"));

        return query.fetch();
    }
}
