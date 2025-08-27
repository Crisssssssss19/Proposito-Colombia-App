package com.procol.procolombia.repositories;

import com.procol.procolombia.entities.Requisito;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequisitoRepository extends JpaRepository<Requisito, Integer> {
  }