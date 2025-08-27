package com.procol.procolombia.repositories;

import com.procol.procolombia.entities.UsuariosRole;
import com.procol.procolombia.entities.UsuariosRoleId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuariosRoleRepository extends JpaRepository<UsuariosRole, UsuariosRoleId> {
}