package trier.jovemdev.provaum.guilherme_monteiro.enums;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonCreator;

import trier.jovemdev.provaum.guilherme_monteiro.exceptions.ValorInvalidoEnumException;

public enum CargoEnum {
	DONO, GERENTE, FUNCIONARIO, FREELANCE;

	@JsonCreator
	public static CargoEnum fromString(String value) {
		
		if (value.matches("^\\d+$") && Integer.parseInt(value) < CargoEnum.values().length
				&& Objects.nonNull(CargoEnum.values()[Integer.parseInt(value)])) {
			return CargoEnum.values()[Integer.parseInt(value)];
		}

		throw new ValorInvalidoEnumException("Cargo", value);
	}
}
