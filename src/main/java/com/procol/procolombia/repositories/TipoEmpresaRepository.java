package com.procol.procolombia.repositories;

import com.procol.procolombia.entities.TipoEmpresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TipoEmpresaRepository extends JpaRepository<TipoEmpresa, Integer> {
  Optional<TipoEmpresa> findByNombreTipoEmpresa(String nombreTipoEmpresa);

  List<TipoEmpresa> findByEstadoTipoEmpresa(Short estadoTipoEmpresa);

  boolean existsByNombreTipoEmpresa(String nombreTipoEmpresa);

  @Query("SELECT COUNT(e) FROM Empresa e WHERE e.idTipoEmpresa.id = :tipoId")
  long countEmpresasByTipo(@Param("tipoId") Integer tipoId);

  List<TipoEmpresa> findByNombreTipoEmpresaContainingIgnoreCase(String nombre);

}