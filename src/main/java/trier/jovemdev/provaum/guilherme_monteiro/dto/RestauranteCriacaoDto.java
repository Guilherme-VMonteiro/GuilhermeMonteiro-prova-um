package trier.jovemdev.provaum.guilherme_monteiro.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import trier.jovemdev.provaum.guilherme_monteiro.entity.ClienteEntity;
import trier.jovemdev.provaum.guilherme_monteiro.entity.FuncionarioEntity;
import trier.jovemdev.provaum.guilherme_monteiro.entity.MesaEntity;
import trier.jovemdev.provaum.guilherme_monteiro.entity.RestauranteEntity;
import trier.jovemdev.provaum.guilherme_monteiro.entity.security.Permissoes;
import trier.jovemdev.provaum.guilherme_monteiro.enums.TipoComidaEnum;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RestauranteCriacaoDto {

	private Long id;
	private String nome;
	private String email;
	private String password;
	private String cnpj;
	private Integer estrelas;
	private TipoComidaEnum tipoComida;
}
