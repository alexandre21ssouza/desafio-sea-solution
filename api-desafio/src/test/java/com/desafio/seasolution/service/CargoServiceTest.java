package com.desafio.seasolution.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.desafio.seasolution.dto.CargoDTO;
import com.desafio.seasolution.entity.Cargo;
import com.desafio.seasolution.entity.Setor;
import com.desafio.seasolution.exception.SetorExistenteException;
import com.desafio.seasolution.repository.CargoRepository;
import com.desafio.seasolution.repository.SetorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class CargoServiceTest {

    @Mock
    private CargoRepository cargoRepository;

    @Mock
    private SetorRepository setorRepository;

    @InjectMocks
    private CargoService service;

    @Test
    void testCriarCargoComSucesso() throws Exception {
        var cargoDTO = new CargoDTO();
        cargoDTO.setNome("Desenvolvedor");
        cargoDTO.setSetorId(1L);

        var setor = new Setor();
        setor.setId(1L);
        setor.setNome("TI");

        when(setorRepository.findById(1L)).thenReturn(Optional.of(setor));

        var novoCargo = new Cargo();
        novoCargo.setNome("Desenvolvedor");
        novoCargo.setSetor(setor.getNome());

        when(cargoRepository.save(any())).thenReturn(novoCargo);

        var result = service.criarCargo(cargoDTO);

        assertEquals("Desenvolvedor", result.getNome());
        assertEquals("TI", result.getSetor());
    }

    @Test
    void testFalhaAoCriarCargoSetorNaoEncontrado() {
        var cargoDTO = new CargoDTO();
        cargoDTO.setNome("Desenvolvedor");
        cargoDTO.setSetorId(1L);

        when(setorRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(SetorExistenteException.class, () -> {
            service.criarCargo(cargoDTO);
        });
    }
}
