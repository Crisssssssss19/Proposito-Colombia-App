package com.procol.procolombia.repositories;

import com.procol.procolombia.entities.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpresaRepository extends JpaRepository<Empresa, Integer> {
  }