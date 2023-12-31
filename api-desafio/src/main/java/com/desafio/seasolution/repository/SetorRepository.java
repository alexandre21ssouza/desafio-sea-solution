package com.desafio.seasolution.repository;


import com.desafio.seasolution.entity.Setor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SetorRepository extends JpaRepository<Setor, Long > {
    Optional<Setor> findByNome(String nome);
}

