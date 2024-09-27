package trier.jovemdev.provaum.guilherme_monteiro.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import trier.jovemdev.provaum.guilherme_monteiro.dto.MesaDto;
import trier.jovemdev.provaum.guilherme_monteiro.entity.MesaEntity;
import trier.jovemdev.provaum.guilherme_monteiro.exceptions.MesaNaoEncontradaException;
import trier.jovemdev.provaum.guilherme_monteiro.exceptions.RestauranteNaoEncontradoException;
import trier.jovemdev.provaum.guilherme_monteiro.repository.MesaRepository;
import trier.jovemdev.provaum.guilherme_monteiro.service.MesaService;
import trier.jovemdev.provaum.guilherme_monteiro.service.RestauranteService;

@Service
public class MesaServiceImpl implements MesaService {

    @Autowired
    private MesaRepository repository;

    @Autowired
    private RestauranteService restauranteService;

    public List<MesaDto> findAllByRestaurante(Long idRestaurante) throws RestauranteNaoEncontradoException {
        return repository.findAllByRestauranteId(idRestaurante).stream().map(MesaDto::new).toList();
    }

    public MesaDto findById(Long idMesa) throws MesaNaoEncontradaException {
        return new MesaDto(repository.findById(idMesa).orElseThrow(() -> new MesaNaoEncontradaException(idMesa)));
    }

    public MesaDto create(MesaDto mesaDto) throws RestauranteNaoEncontradoException {
        restauranteService.findById(mesaDto.getRestaurante().getId());
        return new MesaDto(repository.save(new MesaEntity(mesaDto)));
    }

    public MesaDto update(MesaDto mesaDto) throws RestauranteNaoEncontradoException, MesaNaoEncontradaException {
        MesaEntity entity = new MesaEntity(findById(mesaDto.getId()));

        entity.atualizaCampos(mesaDto);

        return new MesaDto(repository.save(entity));
    }

    public void delete(Long idMesa) throws MesaNaoEncontradaException {
        repository.delete(new MesaEntity(findById(idMesa)));
    }

    public List<MesaDto> findAllAvailableByRestaurante(Long idRestaurante) throws RestauranteNaoEncontradoException {
        // TODO Auto-generated method stub
        return null;
    }

}
