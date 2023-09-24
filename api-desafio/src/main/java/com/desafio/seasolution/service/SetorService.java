package com.desafio.seasolution.service;


import com.desafio.seasolution.dto.SetorDTO;
import com.desafio.seasolution.entity.Setor;
import com.desafio.seasolution.exception.SetorExistenteException;
import com.desafio.seasolution.exception.SetorNaoEncontradoException;
import com.desafio.seasolution.repository.SetorRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SetorService {

	private final SetorRepository repository;
	public SetorService(SetorRepository repository) {
		this.repository = repository;
	}

	public Setor salva(SetorDTO dto) throws SetorExistenteException {
		verificaSeNomeExiste(dto.getNome());
		return repository.save(setorParaDto(dto));
	}

	public Setor atualizar(Long id, SetorDTO dto) throws SetorNaoEncontradoException {
		var setorExistente = buscarPorId(id);
		setorExistente.setNome(dto.getNome());
		return repository.save(setorExistente);
	}

	public Setor buscarPorId(Long id) throws SetorNaoEncontradoException {
		return repository.findById(id)
				.orElseThrow(() -> new SetorNaoEncontradoException("Setor não encontrado"));
	}

	private void verificaSeNomeExiste(String nome) throws SetorExistenteException {
		Optional<Setor> nomeExistente = repository.findByNome(nome);
		if (nomeExistente.isPresent()) {
			throw new SetorExistenteException("Setor com este nome já existe");
		}
	}

	private Setor setorParaDto(SetorDTO dto) {
		var novoSetor = new Setor();
		novoSetor.setNome(dto.getNome());
		return novoSetor;
	}
}

