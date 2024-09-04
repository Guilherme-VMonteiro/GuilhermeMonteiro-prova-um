package trier.jovemdev.provaum.guilherme_monteiro.enums;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonCreator;

import trier.jovemdev.provaum.guilherme_monteiro.exceptions.ValorInvalidoEnumException;

public enum TipoComidaEnum {
	BRASILEIRA, ITALIANA, FRANCESA, MEXICANA, CHINESA, JAPONESA, INDIANA, MEDITERRANEA, VEGANA, VEGETARIANA;

	@JsonCreator
	public static TipoComidaEnum fromString(String value) {

		if (value.matches("^\\d+$") && Integer.parseInt(value) < TipoComidaEnum.values().length
				&& Objects.nonNull(TipoComidaEnum.values()[Integer.parseInt(value)])) {
			return TipoComidaEnum.values()[Integer.parseInt(value)];
		}

		throw new ValorInvalidoEnumException("TipoComida", value);
	}
}
