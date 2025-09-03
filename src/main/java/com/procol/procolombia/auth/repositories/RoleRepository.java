package com.procol.procolombia.auth.repositories;

import com.procol.procolombia.auth.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByNombreRol(String nombreRol);

    List<Role> findByEstadoRol(Short estadoRol);

    boolean existsByNombreRol(String nombreRol);

    @Query("SELECT COUNT(ur) FROM UsuariosRole ur WHERE ur.idRol.id = :rolId")
    long countUsuariosByRol(@Param("rolId") Integer rolId);

    List<Role> findByNombreRolContainingIgnoreCase(String nombre);

}