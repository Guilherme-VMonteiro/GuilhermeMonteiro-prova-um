package trier.jovemdev.provaum.guilherme_monteiro.repository.custom.impl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import trier.jovemdev.provaum.guilherme_monteiro.dto.PedidoDto;
import trier.jovemdev.provaum.guilherme_monteiro.entity.QClienteEntity;
import trier.jovemdev.provaum.guilherme_monteiro.entity.QPedidoEntity;
import trier.jovemdev.provaum.guilherme_monteiro.entity.QReservaEntity;
import trier.jovemdev.provaum.guilherme_monteiro.entity.QRestauranteEntity;
import trier.jovemdev.provaum.guilherme_monteiro.enums.StatusReservaEnum;
import trier.jovemdev.provaum.guilherme_monteiro.repository.custom.PedidoRepositoryCustom;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Repository
public class PedidoRepositoryImpl implements PedidoRepositoryCustom {

    @PersistenceContext
    private EntityManager em;

    final QPedidoEntity pedido = QPedidoEntity.pedidoEntity;
    final QReservaEntity reserva = QReservaEntity.reservaEntity;
    final QClienteEntity cliente = QClienteEntity.clienteEntity;
    final QRestauranteEntity restaurante = QRestauranteEntity.restauranteEntity;

    public List<PedidoDto> findAllByParameters(LocalDate data, BigDecimal valor, StatusReservaEnum statusReserva, Long clienteId) {
        BooleanBuilder condicoes = new BooleanBuilder();

        condicoes.and(reserva.dataReserva.eq(data));

        if(Objects.nonNull(valor)) {
            condicoes.and(pedido.valor.loe(valor));
        }

        if(Objects.nonNull(statusReserva)) {
            condicoes.and(pedido.reserva.status.eq(statusReserva));
        }

        if(Objects.nonNull(clienteId)) {
            condicoes.and(pedido.reserva.cliente.id.eq(clienteId));
        }

        JPAQuery<PedidoDto> query = new JPAQuery<>(em);

        query
                .select(Projections.constructor(PedidoDto.class, pedido))
                .from(pedido)
                .join(pedido.reserva, reserva)
                .join(pedido.reserva.cliente, cliente)
                .where(condicoes)
                .orderBy(pedido.valor.desc());

        return query.fetch();
    }

    public List<PedidoDto> findAllByRestaurante(Long idRestaurante) {
        JPAQuery<PedidoDto> query = new JPAQuery<>(em);

        query
                .select(Projections.constructor(PedidoDto.class, pedido))
                .from(pedido)
                .join(pedido.reserva, reserva)
                .join(reserva.cliente, cliente)
                .join(cliente.restaurante, restaurante)
                .where(restaurante.id.eq(idRestaurante))
                .orderBy(pedido.id.asc());

        return query.fetch();
    }
}
