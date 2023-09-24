package com.desafio.seasolution.service;

import com.desafio.seasolution.dto.CargoDTO;
import com.desafio.seasolution.entity.Cargo;
import com.desafio.seasolution.entity.Setor;
import com.desafio.seasolution.exception.CargoNaoEncontradoException;
import com.desafio.seasolution.exception.SetorExistenteException;
import com.desafio.seasolution.repository.CargoRepository;
import com.desafio.seasolution.repository.SetorRepository;
import org.springframework.stereotype.Service;

@Service
public class CargoService {

    private final CargoRepository cargoRepository;
    private final SetorRepository setorRepository;

    public CargoService(CargoRepository cargoRepository,
                        SetorRepository setorRepository) {
        this.cargoRepository = cargoRepository;
        this.setorRepository = setorRepository;
    }

    public Cargo criarCargo(CargoDTO cargoDTO) throws CargoNaoEncontradoException {
        var setor = buscaSetor(cargoDTO.getSetorId());
        return cargoRepository.save(cargoParaDto(cargoDTO, setor));
    }

    private Setor buscaSetor(Long setorId) throws SetorExistenteException {
        return setorRepository.findById(setorId)
                .orElseThrow(() -> new SetorExistenteException("Setor não encontrado"));
    }

    private Cargo cargoParaDto(CargoDTO cargoDTO, Setor setor) {
        var novoCargo = new Cargo();
        novoCargo.setNome(cargoDTO.getNome());
        novoCargo.setSetor(setor.getNome());
        return novoCargo;
    }
}
