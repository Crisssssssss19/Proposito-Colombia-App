package com.procol.procolombia.repositories;

import com.procol.procolombia.entities.Empresa;
import com.procol.procolombia.entities.TipoEmpresa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmpresaRepository extends JpaRepository<Empresa, Integer> {
  List<Empresa> findByIdTipoEmpresa(TipoEmpresa tipoEmpresa);

  List<Empresa> findByNombreEmpresaContainingIgnoreCase(String nombre);

  Page<Empresa> findByNombreEmpresaContainingIgnoreCase(String nombre, Pageable pageable);

  @Query("SELECT e FROM Empresa e WHERE e.idTipoEmpresa.id = :tipoId")
  Page<Empresa> findByTipoEmpresa(@Param("tipoId") Integer tipoId, Pageable pageable);

  @Query("SELECT COUNT(v) FROM Vacante v WHERE v.relUsuariosEmpresas.idEmpresa.id = :empresaId AND v.estadoVacante = 1")
  long countVacantesActivasByEmpresa(@Param("empresaId") Integer empresaId);

  @Query("SELECT e FROM Empresa e JOIN e.relUsuariosEmpresas rue WHERE rue.idUsuario.id = :usuarioId")
  List<Empresa> findByUsuario(@Param("usuarioId") Integer usuarioId);

  boolean existsByNombreEmpresa(String nombreEmpresa);
  }