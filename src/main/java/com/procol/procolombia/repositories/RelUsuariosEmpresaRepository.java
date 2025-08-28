package com.procol.procolombia.repositories;

import com.procol.procolombia.entities.RelUsuariosEmpresa;
import com.procol.procolombia.entities.RelUsuariosEmpresaId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RelUsuariosEmpresaRepository extends JpaRepository<RelUsuariosEmpresa, RelUsuariosEmpresaId> {

    List<RelUsuariosEmpresa> findByIdEmpresa_Id(Integer idEmpresa);

    List<RelUsuariosEmpresa> findByIdUsuario_Id(Integer idUsuario);

    List<RelUsuariosEmpresa> findByPermisoRelUsuarioEmpresa(Short permiso);

    Optional<RelUsuariosEmpresa> findByIdUsuario_IdAndIdEmpresa_Id(Integer idUsuario, Integer idEmpresa);

    boolean existsByIdUsuario_IdAndIdEmpresa_Id(Integer idUsuario, Integer idEmpresa);

    @Query("SELECT r FROM RelUsuariosEmpresa r WHERE r.idUsuario.id = :usuarioId AND r.permisoRelUsuarioEmpresa = 1")
    List<RelUsuariosEmpresa> findEmpresasConPermisoCompleto(@Param("usuarioId") Integer usuarioId);

    @Query("SELECT COUNT(r) FROM RelUsuariosEmpresa r WHERE r.idEmpresa.id = :empresaId")
    long countByEmpresa(@Param("empresaId") Integer empresaId);

    @Query("SELECT COUNT(r) FROM RelUsuariosEmpresa r WHERE r.idUsuario.id = :usuarioId")
    long countByUsuario(@Param("usuarioId") Integer usuarioId);

    @Query("SELECT COUNT(v) FROM Vacante v WHERE v.relUsuariosEmpresas.idUsuario.id = :usuarioId AND v.relUsuariosEmpresas.idEmpresa.id = :empresaId")
    long countVacantesByUsuarioAndEmpresa(@Param("usuarioId") Integer usuarioId, @Param("empresaId") Integer empresaId);

}