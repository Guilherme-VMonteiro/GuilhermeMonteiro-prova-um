package trier.jovemdev.provaum.guilherme_monteiro.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import trier.jovemdev.provaum.guilherme_monteiro.dto.ClienteDto;
import trier.jovemdev.provaum.guilherme_monteiro.dto.PedidoDto;
import trier.jovemdev.provaum.guilherme_monteiro.dto.ReservaDto;
import trier.jovemdev.provaum.guilherme_monteiro.dto.ReservaTotalDto;
import trier.jovemdev.provaum.guilherme_monteiro.entity.ReservaEntity;
import trier.jovemdev.provaum.guilherme_monteiro.enums.StatusReservaEnum;
import trier.jovemdev.provaum.guilherme_monteiro.exceptions.*;
import trier.jovemdev.provaum.guilherme_monteiro.repository.ReservaRepository;
import trier.jovemdev.provaum.guilherme_monteiro.repository.custom.ReservaRepositoryCustom;
import trier.jovemdev.provaum.guilherme_monteiro.service.ClienteService;
import trier.jovemdev.provaum.guilherme_monteiro.service.MesaService;
import trier.jovemdev.provaum.guilherme_monteiro.service.ReservaService;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Service
public class ReservaServiceImpl implements ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private ReservaRepositoryCustom reservaRepositoryCustom;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private MesaService mesaService;

    public List<ReservaDto> findAll() {
        return reservaRepository.findAll().stream().map(ReservaDto::new).toList();
    }

    public List<ReservaDto> findAllByRestaurante(Long idRestaurante) throws RestauranteNaoEncontradoException {
        return reservaRepositoryCustom.findAllByRestauranteId(idRestaurante).stream().map(ReservaDto::new).toList();
    }

    public ReservaDto findById(Long id) throws ReservaNaoEncontradaException {
        return new ReservaDto(reservaRepository.findById(id).orElseThrow(() -> new ReservaNaoEncontradaException(id)));
    }

    public ReservaDto create(ReservaDto reservaDto) throws ClienteNaoEncontradoException, MesaNaoEncontradaException, DataInvalidaException, ReservaBloqueadaException, ClienteBloqueadoException {

        reservaDto.setCliente(clienteService.findById(reservaDto.getCliente().getId()));

        if (reservaDto.getCliente().getBloqueado()) {
            throw new ClienteBloqueadoException(reservaDto.getCliente().getNome());
        }

        reservaDto.setStatus(StatusReservaEnum.AGENDADA);
        validaDataReserva(reservaDto.getDataReserva());
        reservaDto.setMesa(mesaService.findById(reservaDto.getMesa().getId()));

        validaCriacaoReserva(reservaDto.getCliente());

        return new ReservaDto(reservaRepository.save(new ReservaEntity(reservaDto)));
    }

    public ReservaDto update(ReservaDto reservaDto) throws ClienteNaoEncontradoException, MesaNaoEncontradaException, DataInvalidaException, ConclusaoInvalidaException, CancelamentoInvalidoException {

        validaDataReserva(reservaDto.getDataReserva());
        reservaDto.setCliente(clienteService.findById(reservaDto.getCliente().getId()));
        reservaDto.setMesa(mesaService.findById(reservaDto.getMesa().getId()));

        ReservaEntity reservaEntity = new ReservaEntity(findById(reservaDto.getId()));
        reservaEntity.atualizaCampos(reservaDto);

        return new ReservaDto(reservaRepository.save(reservaEntity));
    }

    public void delete(Long id) throws ReservaNaoEncontradaException {
        reservaRepository.delete(new ReservaEntity(findById(id)));
    }

    public ReservaDto updateStatus(Long idReserva, StatusReservaEnum status) throws ReservaNaoEncontradaException, ConclusaoInvalidaException, CancelamentoInvalidoException {

        ReservaDto reservaDto = findById(idReserva);

        if (status == StatusReservaEnum.AGENDADA) {
            reservaDto.setStatus(status);
        } else if (status == StatusReservaEnum.CONCLUIDA) {
            if (LocalDate.now().isEqual(reservaDto.getDataReserva()) || LocalDate.now().isAfter(reservaDto.getDataReserva())) {
                reservaDto.setStatus(StatusReservaEnum.CONCLUIDA);
                clienteService.atualizaValorGastoCliente(findById(idReserva).getCliente(), reservaRepository.findById(idReserva).get().getPedidos().stream().map(PedidoDto::new).toList());
            } else {
                throw new ConclusaoInvalidaException();
            }
        } else if (status == StatusReservaEnum.INADIMPLENTE) {
            Long qtdInadimplencias = reservaRepositoryCustom.countInadimplenciasByCliente(reservaDto.getCliente().getId());

            if (qtdInadimplencias >= 2) {
                clienteService.switchBlockClienteFlag(reservaDto.getCliente().getId());
            }
            reservaDto.setStatus(StatusReservaEnum.INADIMPLENTE);
        } else if (status == StatusReservaEnum.CANCELADA) {
            if (reservaDto.getDataReserva().isAfter(LocalDate.now())) {
                reservaDto.setStatus(StatusReservaEnum.CANCELADA);
            } else {
                throw new CancelamentoInvalidoException();
            }
        }

        return new ReservaDto(reservaRepository.save(new ReservaEntity(reservaDto)));
    }

    public List<ReservaTotalDto> findAllReservasTotalByRestaurante(Long idRestaurante) throws RestauranteNaoEncontradoException {
        return List.of();
    }

    public List<ReservaTotalDto> findAllReservasTotal() {
        return reservaRepositoryCustom.findAllReservasTotal().stream().map(ReservaTotalDto::new).toList();
    }

    public List<ReservaDto> findByObservacao(String observacao) {
        return reservaRepositoryCustom.findByObervacao(observacao).stream().map(ReservaDto::new).toList();
    }

    @Transactional
    public void concluirReservasNaoFinalizadas() {
        List<ReservaDto> reservasSemPedidos = reservaRepositoryCustom.atualizarAutomaticamenteReservasParaInadimplente();
        reservasSemPedidos.forEach(reserva -> updateStatus(reserva.getId(), StatusReservaEnum.INADIMPLENTE));

        List<ReservaDto> reservasComPedidos = reservaRepositoryCustom.atualizarAutomaticamenteReservasParaConcluida();
        reservasComPedidos.forEach(reserva -> {
            updateStatus(reserva.getId(), StatusReservaEnum.CONCLUIDA);
            clienteService.atualizaValorGastoCliente(findById(reserva.getId()).getCliente(), reservaRepository.findById(reserva.getId()).get().getPedidos().stream().map(PedidoDto::new).toList());
        });
    }

    private void validaDataReserva(LocalDate dataReserva) throws DataInvalidaException {
        if (Objects.isNull(dataReserva) || dataReserva.isBefore(LocalDate.now())) {
            throw new DataInvalidaException(dataReserva);
        }
    }

    private void validaCriacaoReserva(ClienteDto clienteDto) throws ReservaBloqueadaException {
        Long qtdReservasCanceladasNoMes = reservaRepositoryCustom.countReservasCanceladasNoMesByCliente(clienteDto.getId());

        if (qtdReservasCanceladasNoMes >= 2) {
            throw new ReservaBloqueadaException(clienteDto.getNome(), LocalDate.now().minusDays(LocalDate.now().getDayOfMonth() - 1).plusMonths(1));
        }
    }
}
