package trier.jovemdev.provaum.guilherme_monteiro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import trier.jovemdev.provaum.guilherme_monteiro.entity.FuncionarioEntity;

@Repository
public interface FuncionarioRepository extends JpaRepository<FuncionarioEntity, Long>{

}
