package trier.jovemdev.provaum.guilherme_monteiro.enums;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonCreator;

import trier.jovemdev.provaum.guilherme_monteiro.exceptions.ValorInvalidoEnumException;

public enum SexoEnum {
	MASCULINO, FEMININO, INDEFINIDO;

	@JsonCreator
	public static SexoEnum fromString(String value) {
		
		if (value.matches("^\\d+$") && Integer.parseInt(value) < SexoEnum.values().length
				&& Objects.nonNull(SexoEnum.values()[Integer.parseInt(value)])) {
			return SexoEnum.values()[Integer.parseInt(value)];
		}

		throw new ValorInvalidoEnumException("Sexo", value);
	}
}
