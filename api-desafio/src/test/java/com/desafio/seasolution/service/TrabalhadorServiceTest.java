package com.desafio.seasolution.service;

import com.desafio.seasolution.dto.CargoDTO;
import com.desafio.seasolution.dto.TrabalhadorDTO;
import com.desafio.seasolution.entity.Cargo;
import com.desafio.seasolution.entity.Setor;
import com.desafio.seasolution.entity.Trabalhador;
import com.desafio.seasolution.exception.CargoNaoEncontradoException;
import com.desafio.seasolution.repository.CargoRepository;
import com.desafio.seasolution.repository.SetorRepository;
import com.desafio.seasolution.repository.TrabalhadorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TrabalhadorServiceTest {

    @Mock
    private TrabalhadorRepository trabalhadorRepository;

    @Mock
    private CargoRepository cargoRepository;

    @InjectMocks
    private TrabalhadorService service;

    @Test
    void testCriarTrabalhadorComSucesso() throws Exception {
        var dto = new TrabalhadorDTO();
        dto.setNome("Jocimar");
        dto.setCpf("87700017077");
        dto.setCargoId(1L);
        dto.setSetor("Tecnologia");

        var cargo = new Cargo();
        cargo.setId(1L);
        cargo.setNome("Desenvolvedor");

        when(cargoRepository.findById(1L)).thenReturn(Optional.of(cargo));

        var novoTrabalhador = new Trabalhador();
        novoTrabalhador.setNome("Jose Maria");
        novoTrabalhador.setCpf("87700017077");
        novoTrabalhador.setCargo(cargo.getNome());
        novoTrabalhador.setSetor(dto.getSetor());

        when(trabalhadorRepository.save(any())).thenReturn(novoTrabalhador);

        var result = service.criarTrabalhador(dto);

        assertEquals("Jose Maria", result.getNome());
        assertEquals("87700017077", result.getCpf());
        assertEquals("Desenvolvedor", result.getCargo());
        assertEquals("Tecnologia", result.getSetor());
    }

    @Test
    void testFalhaAoCriarTrabalhadorCargoNaoEncontrado() {
        var dto = new TrabalhadorDTO();
        dto.setNome("Jocimar");
        dto.setCpf("123.456.789-00");
        dto.setCargoId(1L);

        when(cargoRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(CargoNaoEncontradoException.class, () -> {
            service.criarTrabalhador(dto);
        });
    }
}

