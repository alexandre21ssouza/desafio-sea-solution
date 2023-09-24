package com.desafio.seasolution.service;

import static org.mockito.Mockito.*;

import java.util.Optional;

import com.desafio.seasolution.dto.SetorDTO;
import com.desafio.seasolution.entity.Setor;
import com.desafio.seasolution.exception.SetorExistenteException;
import com.desafio.seasolution.repository.SetorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class SetorServiceTest {

    @Mock
    private SetorRepository repository;

    @InjectMocks
    private SetorService service;

    @Test
    void testSalvaSetorComSucesso() throws SetorExistenteException {
        var dto = new SetorDTO();
        dto.setNome("Novo Setor");

        var setor = new Setor();
        setor.setNome("Novo Setor");

        when(repository.findByNome("Novo Setor")).thenReturn(Optional.empty());
        when(repository.save(any())).thenReturn(setor);

        Setor result = service.salva(dto);

        assertEquals("Novo Setor", result.getNome());
    }

    @Test
    void testFalhaAoSalvarSetorComNomeExistente() {
        SetorDTO dto = new SetorDTO();
        dto.setNome("Setor Existente");

        Setor setorExistente = new Setor();
        setorExistente.setNome("Setor Existente");

        when(repository.findByNome("Setor Existente")).thenReturn(Optional.of(setorExistente));

        assertThrows(SetorExistenteException.class, () -> {
            service.salva(dto);
        });
    }

    @Test
    void testBuscarPorId() {
        var setor = new Setor();
        setor.setId(1L);
        setor.setNome("Setor 1");

        when(repository.findById(1L)).thenReturn(Optional.of(setor));

        Setor result = service.buscarPorId(1L);

        assertEquals(1L, result.getId());
        assertEquals("Setor 1", result.getNome());
    }
}
