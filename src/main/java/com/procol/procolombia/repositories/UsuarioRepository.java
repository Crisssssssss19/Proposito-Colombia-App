package com.procol.procolombia.repositories;

import com.procol.procolombia.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
  }