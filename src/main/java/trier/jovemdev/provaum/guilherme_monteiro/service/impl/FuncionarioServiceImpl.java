package trier.jovemdev.provaum.guilherme_monteiro.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import trier.jovemdev.provaum.guilherme_monteiro.dto.FuncionarioDto;
import trier.jovemdev.provaum.guilherme_monteiro.dto.RestauranteDto;
import trier.jovemdev.provaum.guilherme_monteiro.entity.FuncionarioEntity;
import trier.jovemdev.provaum.guilherme_monteiro.enums.CargoEnum;
import trier.jovemdev.provaum.guilherme_monteiro.exceptions.*;
import trier.jovemdev.provaum.guilherme_monteiro.repository.FuncionarioRepository;
import trier.jovemdev.provaum.guilherme_monteiro.repository.custom.FuncionarioRepositoryCustom;
import trier.jovemdev.provaum.guilherme_monteiro.service.FuncionarioService;
import trier.jovemdev.provaum.guilherme_monteiro.service.RestauranteService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Objects;

@Service
public class FuncionarioServiceImpl implements FuncionarioService {

    @Autowired
    private FuncionarioRepository repository;

    @Autowired
    private FuncionarioRepositoryCustom repositoryCustom;

    @Autowired
    private RestauranteService restauranteService;

    public List<FuncionarioDto> findAll() {
        return repository.findAll().stream().map(FuncionarioDto::new).toList();
    }

    public List<FuncionarioDto> findAllByRestauranteId(Long restauranteId) {
        return repositoryCustom.findAllByRestauranteId(restauranteId);
    }

    public FuncionarioDto findById(Long id) throws FuncionarioNaoEncontradoException {
        return new FuncionarioDto(repository.findById(id).orElseThrow(() -> new FuncionarioNaoEncontradoException(id)));
    }

    public FuncionarioDto create(FuncionarioDto funcionarioDto) throws DataDeNascimentoInvalidaException,
            CargaHorariaInvalidaException, SalarioInvalidoException, RestauranteNaoEncontradoException {

        validaCampos(funcionarioDto);

        return new FuncionarioDto(repository.save(new FuncionarioEntity(funcionarioDto)));
    }

    public FuncionarioDto update(FuncionarioDto funcionarioDto) throws DataDeNascimentoInvalidaException,
            CargaHorariaInvalidaException, SalarioInvalidoException, RestauranteNaoEncontradoException {
        validaCampos(funcionarioDto);

        FuncionarioEntity entity = new FuncionarioEntity(findById(funcionarioDto.getId()));

        entity.atualizaCampos(funcionarioDto);

        return new FuncionarioDto(repository.save(entity));
    }

    public void delete(Long id) throws FuncionarioNaoEncontradoException {
        repository.delete(new FuncionarioEntity(findById(id)));
    }

    private void validaCampos(FuncionarioDto funcionarioDto) throws DataDeNascimentoInvalidaException,
            CargaHorariaInvalidaException, SalarioInvalidoException, RestauranteNaoEncontradoException {
        validaDataNascimento(funcionarioDto.getDataNascimento());
        validaCargaHoraria(funcionarioDto.getCargaHoraria());
        validaSalario(funcionarioDto.getSalario(), funcionarioDto.getCargo());
        validaRestaurante(funcionarioDto.getRestaurante().getId());
    }

    private void validaDataNascimento(LocalDate dataNascimento) throws DataDeNascimentoInvalidaException {
        int idade = Period.between(dataNascimento, LocalDate.now()).getYears();
        if (idade > 100 || idade < 12) {
            throw new DataDeNascimentoInvalidaException(dataNascimento);
        }
    }

    private void validaCargaHoraria(Integer cargaHoraria) throws CargaHorariaInvalidaException {
        if (cargaHoraria > 220) {
            throw new CargaHorariaInvalidaException(cargaHoraria);
        }
    }

    private void validaSalario(BigDecimal salario, CargoEnum cargo) throws SalarioInvalidoException {
        if (cargo == CargoEnum.FREELANCE) {
            if (salario.compareTo(BigDecimal.valueOf(1412.00)) > 0) {
                throw new SalarioFreelancerInvalidoException();
            }
        } else {
            if (salario.compareTo(BigDecimal.valueOf(1412.00)) < 1) {
                throw new SalarioInvalidoException();
            }
        }
    }

    private void validaRestaurante(long id) throws RestauranteNaoEncontradoException {
        RestauranteDto restaurante = restauranteService.findById(id);

        if (Objects.isNull(restaurante)) {
            throw new RestauranteNaoEncontradoException(id);
        }
    }
}
