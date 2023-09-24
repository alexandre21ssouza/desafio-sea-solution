package com.desafio.seasolution.controller;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.desafio.seasolution.dto.CargoDTO;
import com.desafio.seasolution.dto.SetorDTO;
import com.desafio.seasolution.dto.TrabalhadorDTO;
import com.desafio.seasolution.entity.Cargo;
import com.desafio.seasolution.entity.Setor;
import com.desafio.seasolution.entity.Trabalhador;
import com.desafio.seasolution.service.CargoService;
import com.desafio.seasolution.service.SetorService;
import com.desafio.seasolution.service.TrabalhadorService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/v1")
@Api(value = "Gerenciamento de Empresa", description = "Operações para gerenciamento de Setores, Cargos e Trabalhadores")
public class EmpresaController {
	
	private final SetorService setorService;
	private final CargoService cargoService;
	private final TrabalhadorService trabalhadorService;

	public EmpresaController(SetorService setorService,
							 CargoService cargoService,
							 TrabalhadorService trabalhadorService
	) {
		this.setorService = setorService;
		this.cargoService = cargoService;
		this.trabalhadorService = trabalhadorService;
	}

	@ApiOperation(value = "Adicionar um novo setor")
	@PostMapping("/setor")
	public ResponseEntity<?> criarSetor(@RequestBody SetorDTO dto) {
		try {
			var criarCargo = setorService.salva(dto);
			return new ResponseEntity<>(criarCargo, CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>("Setor com este nome ja existe", BAD_REQUEST);
		}
	}

	@ApiOperation(value = "Atualizar um setor por id")

	@PutMapping("/setor/{id}")
	public ResponseEntity<Setor> atualizarSetor(@PathVariable Long id, @RequestBody SetorDTO dto) {
		try {
			Setor setorAtualizado = setorService.atualizar(id, dto);
			return new ResponseEntity<>(setorAtualizado, OK);
		} catch (Exception e) {
			return new ResponseEntity<>(BAD_REQUEST);

		}
	}

	@ApiOperation(value = "Adicionar um novo cargo")
	@PostMapping("/cargo")
	public ResponseEntity<Cargo> criarCargo(@RequestBody CargoDTO dto) {
		try {
			var criarCargo = cargoService.criarCargo(dto);
			return new ResponseEntity<>(criarCargo, CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(BAD_REQUEST);
		}
	}

	@ApiOperation(value = "Adicionar um novo trabalhador")

	@PostMapping("/trabalhador")
	public ResponseEntity<Trabalhador> criarTrabalhador(@Valid @RequestBody TrabalhadorDTO dto) {
		try {
			var criarTrabalhador = trabalhadorService.criarTrabalhador(dto);
			return new ResponseEntity<>(criarTrabalhador, CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(BAD_REQUEST);
		}
	}


	@ApiOperation(value = "Buscar um setor por id")
	@GetMapping("/setor/{id}")
	public ResponseEntity<Setor> buscarSetorPorId(@PathVariable Long id) {
		try {
			var setor = setorService.buscarPorId(id);
			return new ResponseEntity<>(setor, OK);
		} catch (Exception e) {
			return new ResponseEntity<>(BAD_REQUEST);
		}
	}


}
