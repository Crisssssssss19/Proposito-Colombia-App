package com.procol.procolombia.repositories;

import com.procol.procolombia.entities.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolRepository extends JpaRepository<Rol, Integer> {
  }