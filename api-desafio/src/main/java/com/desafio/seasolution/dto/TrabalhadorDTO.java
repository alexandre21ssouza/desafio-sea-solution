package com.desafio.seasolution.dto;


import org.hibernate.validator.constraints.br.CPF;

public class TrabalhadorDTO {


	private String nome;

	@CPF(message = "CPF invalido")
	private String cpf;
	private Long cargoId;

	private String setor;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Long getCargoId() {
		return cargoId;
	}

	public void setCargoId(Long cargoId) {
		this.cargoId = cargoId;
	}

	public String getSetor() {
		return setor;
	}

	public void setSetor(String setor) {
		this.setor = setor;
	}
}
