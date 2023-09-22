package com.desafio.apidesafio.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.desafio.apidesafio.entity.Setor;
import com.desafio.apidesafio.service.SetorService;

@RestController
public class EmpresaController {
	
	private SetorService service;
	
	@PostMapping("/setor")
	public Setor criaSetor(@RequestBody Setor setor){
		return service.salvar(setor);
		
	}
}
