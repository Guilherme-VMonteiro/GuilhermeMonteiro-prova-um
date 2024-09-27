package trier.jovemdev.provaum.guilherme_monteiro.dto;

import lombok.*;
import trier.jovemdev.provaum.guilherme_monteiro.enums.StatusReservaEnum;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateStatusDto {

    private Long idReserva;
    private StatusReservaEnum status;
}
