package trier.jovemdev.provaum.guilherme_monteiro.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import trier.jovemdev.provaum.guilherme_monteiro.dto.superclasses.PessoaBaseDto;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDto extends PessoaBaseDto{

	private Long id;
	private LocalDate dataCadastro;
	private Integer quantidadeReservas;
	private BigDecimal valorGasto; 
	private RestauranteDto restaurante;
}
