package com.procol.procolombia.repositories;

import com.procol.procolombia.entities.UsuarioRol;
import com.procol.procolombia.entities.idCompuestas.UsuarioRolId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRolRepository extends JpaRepository<UsuarioRol, UsuarioRolId> {
}