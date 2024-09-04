package trier.jovemdev.provaum.guilherme_monteiro.enums;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonCreator;

import trier.jovemdev.provaum.guilherme_monteiro.exceptions.ValorInvalidoEnumException;

public enum StatusReservaEnum {
	AGENDADA, CANCELADA, CONCLUIDA, INADIMPLENTE;

	@JsonCreator
	public static StatusReservaEnum fromString(String value) {

		if (value.matches("^\\d+$") && Integer.parseInt(value) < StatusReservaEnum.values().length
				&& Objects.nonNull(StatusReservaEnum.values()[Integer.parseInt(value)])) {
			return StatusReservaEnum.values()[Integer.parseInt(value)];
		}

		throw new ValorInvalidoEnumException("StatusReserva", value);
	}
}
