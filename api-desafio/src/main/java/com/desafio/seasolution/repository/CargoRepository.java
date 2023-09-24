package com.desafio.seasolution.repository;

import com.desafio.seasolution.entity.Cargo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CargoRepository extends JpaRepository<Cargo, Long> {
}
