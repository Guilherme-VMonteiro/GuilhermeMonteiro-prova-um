package trier.jovemdev.provaum.guilherme_monteiro.schedulers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import trier.jovemdev.provaum.guilherme_monteiro.service.ReservaService;

@Component
public class ReservaScheduler {

    @Autowired
    private ReservaService reservaService;

    @Scheduled(cron = "0 06 * * * *")
    public void concluirReservaNaoFinalizada(){
        reservaService.concluirReservasNaoFinalizadas();
    }
}
