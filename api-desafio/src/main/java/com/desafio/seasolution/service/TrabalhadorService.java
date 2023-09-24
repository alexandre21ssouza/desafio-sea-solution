package com.desafio.seasolution.service;

import com.desafio.seasolution.dto.TrabalhadorDTO;
import com.desafio.seasolution.entity.Cargo;
import com.desafio.seasolution.entity.Trabalhador;
import com.desafio.seasolution.exception.CargoNaoEncontradoException;
import com.desafio.seasolution.exception.TrabalhadorNaoEncontradoException;
import com.desafio.seasolution.repository.CargoRepository;
import com.desafio.seasolution.repository.TrabalhadorRepository;
import org.springframework.stereotype.Service;

@Service
public class TrabalhadorService {

    private final TrabalhadorRepository trabalhadorRepository;
    private final CargoRepository cargoRepository;

    public TrabalhadorService(TrabalhadorRepository trabalhadorRepository,
                              CargoRepository cargoRepository) {
        this.trabalhadorRepository = trabalhadorRepository;
        this.cargoRepository = cargoRepository;
    }

    public Trabalhador criarTrabalhador(TrabalhadorDTO dto) throws TrabalhadorNaoEncontradoException {
        var cargo = buscaCargo(dto.getCargoId());
        return trabalhadorRepository.save(trabalhadorParaDto(dto, cargo));
    }

    private Cargo buscaCargo(Long cargoId) throws CargoNaoEncontradoException {
        return cargoRepository.findById(cargoId)
                .orElseThrow(() -> new CargoNaoEncontradoException("Cargo não encontrado"));
    }

    private Trabalhador trabalhadorParaDto(TrabalhadorDTO dto, Cargo cargo) {
        Trabalhador novoTrabalhador = new Trabalhador();
        novoTrabalhador.setNome(dto.getNome());
        novoTrabalhador.setCpf(dto.getCpf());
        novoTrabalhador.setCargo(cargo.getNome());
        novoTrabalhador.setSetor(cargo.getNome());
        return novoTrabalhador;
    }
}
