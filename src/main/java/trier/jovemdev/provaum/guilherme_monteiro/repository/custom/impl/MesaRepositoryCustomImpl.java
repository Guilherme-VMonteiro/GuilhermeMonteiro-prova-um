package trier.jovemdev.provaum.guilherme_monteiro.repository.custom.impl;

import com.querydsl.jpa.impl.JPAQuery;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import trier.jovemdev.provaum.guilherme_monteiro.entity.MesaEntity;
import trier.jovemdev.provaum.guilherme_monteiro.entity.QMesaEntity;
import trier.jovemdev.provaum.guilherme_monteiro.entity.QReservaEntity;
import trier.jovemdev.provaum.guilherme_monteiro.entity.QRestauranteEntity;
import trier.jovemdev.provaum.guilherme_monteiro.repository.custom.MesaRepositoryCustom;

import java.time.LocalDate;
import java.util.List;

public class MesaRepositoryCustomImpl implements MesaRepositoryCustom {

	@PersistenceContext
	private EntityManager em;

	final QRestauranteEntity restaurante = QRestauranteEntity.restauranteEntity;
	final QReservaEntity reserva = QReservaEntity.reservaEntity;
	final QMesaEntity mesa = QMesaEntity.mesaEntity;

	public List<MesaEntity> findMesasDisponiveis(LocalDate data, Integer quantidadePessoas) {

		JPAQuery<MesaEntity> query = new JPAQuery<>();

		query
				.select(mesa)
				.from(restaurante)
				.join(restaurante.mesas, mesa)
				.where(restaurante.id.eq(restaurante.id)
				.and(mesa.capacidadePessoas.goe(quantidadePessoas)));

		return query.fetch();
	}
}
