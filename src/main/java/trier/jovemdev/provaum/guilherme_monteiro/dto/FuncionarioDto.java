package trier.jovemdev.provaum.guilherme_monteiro.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import trier.jovemdev.provaum.guilherme_monteiro.dto.superclasses.PessoaBaseDto;
import trier.jovemdev.provaum.guilherme_monteiro.enums.CargoEnum;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FuncionarioDto extends PessoaBaseDto{

	private Long id;
	private CargoEnum cargo;
	private LocalDate dataAdmisao;
	private BigDecimal salario;
	private Integer cargaHoraria; 
	private RestauranteDto restaurante;
}
