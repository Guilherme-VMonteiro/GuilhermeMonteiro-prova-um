package trier.jovemdev.provaum.guilherme_monteiro.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import trier.jovemdev.provaum.guilherme_monteiro.entity.ClienteEntity;
import trier.jovemdev.provaum.guilherme_monteiro.entity.ReservaEntity;
import trier.jovemdev.provaum.guilherme_monteiro.enums.StatusReserva;

@Repository
public interface ReservaRepository extends JpaRepository<ReservaEntity, Long> {
	
	Optional<ReservaEntity> findByNumeroMesaAndDataReservaAndStatus(Integer numeroMesa, LocalDate dataReserva, StatusReserva status);
	
	List<ReservaEntity> findAllByCliente(ClienteEntity cliente);
}
