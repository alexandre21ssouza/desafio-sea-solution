package com.desafio.seasolution.controller;

import com.desafio.seasolution.dto.CargoDTO;
import com.desafio.seasolution.dto.SetorDTO;
import com.desafio.seasolution.dto.TrabalhadorDTO;
import com.desafio.seasolution.entity.Cargo;
import com.desafio.seasolution.entity.Setor;
import com.desafio.seasolution.entity.Trabalhador;
import com.desafio.seasolution.service.CargoService;
import com.desafio.seasolution.service.SetorService;
import com.desafio.seasolution.service.TrabalhadorService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EmpresaController.class)
class EmpresaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SetorService setorService;

    @MockBean
    private CargoService cargoService;

    @MockBean
    private TrabalhadorService trabalhadorService;

    @Test
    void criarSetor() throws Exception {
        SetorDTO dto = new SetorDTO();
        dto.setNome("Financeiro");
        Setor setor = new Setor();
        setor.setNome("Financeiro");

        when(setorService.salva(any())).thenReturn(setor);

        mockMvc.perform(post("/v1/setor")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(dto)))
                .andExpect(status().isCreated());
    }

    @Test
    void atualizarSetor() throws Exception {
        SetorDTO dto = new SetorDTO();
        dto.setNome("Financeiro");
        Setor setor = new Setor();
        setor.setId(1L);
        setor.setNome("Financeiro");

        when(setorService.atualizar(anyLong(), any())).thenReturn(setor);

        mockMvc.perform(put("/v1/setor/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(dto)))
                .andExpect(status().isOk());
    }

    @Test
    void criarCargo() throws Exception {
        CargoDTO dto = new CargoDTO();
        dto.setNome("Advogado");
        dto.setSetorId(1L);
        dto.setSetorId(1L);
        Cargo cargo = new Cargo();
        cargo.setNome("Advogado");
        cargo.setSetor("Jurídico");

        when(cargoService.criarCargo(any())).thenReturn(cargo);

        mockMvc.perform(post("/v1/cargo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(dto)))
                .andExpect(status().isCreated());
    }

    @Test
    void testaCriarTrabalhador() {
        var dto = new TrabalhadorDTO();
        dto.setNome("Joao");
        dto.setCpf("87700017077");
        dto.setCargoId(1L);
        dto.setSetor("Desenvolvedor");

        var novoTrabalhador = new Trabalhador();
        novoTrabalhador.setNome("Jose Maria");
        novoTrabalhador.setCpf("87700017077");
        novoTrabalhador.setCargo("Advogado");
        novoTrabalhador.setSetor("Jurídico");

        when(trabalhadorService.criarTrabalhador(any())).thenReturn(novoTrabalhador);
        var result = trabalhadorService.criarTrabalhador(dto);

        assertEquals("Jose Maria", result.getNome());
    }

}
