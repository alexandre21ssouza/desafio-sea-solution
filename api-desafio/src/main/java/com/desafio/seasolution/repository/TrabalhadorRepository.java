package com.desafio.seasolution.repository;

import com.desafio.seasolution.entity.Trabalhador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrabalhadorRepository extends JpaRepository<Trabalhador, Long> {
}
