package trier.jovemdev.provaum.guilherme_monteiro.repository.custom;

import trier.jovemdev.provaum.guilherme_monteiro.entity.MesaEntity;

import java.time.LocalDate;
import java.util.List;

public interface MesaRepositoryCustom {

	List<MesaEntity> findMesasDisponiveis(LocalDate data, Integer quantidadePessoas);
}
