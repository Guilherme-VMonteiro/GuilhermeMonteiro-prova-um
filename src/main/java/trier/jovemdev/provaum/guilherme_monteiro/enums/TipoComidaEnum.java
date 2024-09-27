package trier.jovemdev.provaum.guilherme_monteiro.enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonCreator;

import trier.jovemdev.provaum.guilherme_monteiro.exceptions.ValorInvalidoEnumException;

public enum TipoComidaEnum {
    BRASILEIRA, ITALIANA, FRANCESA, MEXICANA, CHINESA, JAPONESA, INDIANA, MEDITERRANEA, VEGANA, VEGETARIANA;

    @JsonCreator
    public static TipoComidaEnum fromString(String value) {

        List<String> valoresTipoComidaEnum = Arrays.stream(TipoComidaEnum.values()).map(Enum::toString).toList();

        if (value.matches("^\\d+$") && Objects.nonNull(TipoComidaEnum.values()[Integer.parseInt(value)])) {
            return TipoComidaEnum.values()[Integer.parseInt(value)];
        }

        if (valoresTipoComidaEnum.contains(value) && Objects.nonNull(TipoComidaEnum.values()[valoresTipoComidaEnum.indexOf(value)])) {
            return TipoComidaEnum.values()[valoresTipoComidaEnum.indexOf(value)];
        }

        throw new ValorInvalidoEnumException("TipoComida", value);
    }
}
