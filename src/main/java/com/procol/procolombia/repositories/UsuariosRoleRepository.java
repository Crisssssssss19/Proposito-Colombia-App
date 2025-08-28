package com.procol.procolombia.repositories;

import com.procol.procolombia.entities.UsuariosRole;
import com.procol.procolombia.entities.UsuariosRoleId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuariosRoleRepository extends JpaRepository<UsuariosRole, UsuariosRoleId> {

    List<UsuariosRole> findByIdUsuario_Id(Integer idUsuario);

    List<UsuariosRole> findByIdRol_Id(Integer idRol);

    boolean existsByIdUsuario_IdAndIdRol_Id(Integer idUsuario, Integer idRol);

    @Query("SELECT COUNT(ur) FROM UsuariosRole ur WHERE ur.idUsuario.id = :usuarioId")
    long countByUsuario(@Param("usuarioId") Integer usuarioId);

    @Query("SELECT COUNT(ur) FROM UsuariosRole ur WHERE ur.idRol.id = :rolId")
    long countByRol(@Param("rolId") Integer rolId);

    void deleteByIdUsuario_IdAndIdRol_Id(Integer idUsuario, Integer idRol);

    @Query("SELECT ur FROM UsuariosRole ur JOIN ur.idRol r WHERE r.estadoRol = 1 AND ur.idUsuario.id = :usuarioId")
    List<UsuariosRole> findRolesActivosByUsuario(@Param("usuarioId") Integer usuarioId);

}