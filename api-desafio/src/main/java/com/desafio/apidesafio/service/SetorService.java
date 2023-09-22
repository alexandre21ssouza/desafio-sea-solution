package com.desafio.apidesafio.service;

import com.desafio.apidesafio.dto.SetorDto;
import com.desafio.apidesafio.entity.Setor;
import com.desafio.apidesafio.repository.SetorRepository;

public class SetorService {
	private SetorRepository repository;
	public Setor salvar(Setor model) {
		 return repository.save(model);
	}
}
