package com.procol.procolombia.repositories;

import java.time.Instant;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.procol.procolombia.entities.Ingreso;

@Repository
public interface IngresoRepository extends JpaRepository<Ingreso, Integer> {

  List<Ingreso> findByIdUsuario_Id(Integer idUsuario);

  Page<Ingreso> findByIdUsuario_Id(Integer idUsuario, Pageable pageable);

  @Query("SELECT i FROM Ingreso i WHERE i.fechaIngreso BETWEEN :fechaInicio AND :fechaFin")
  List<Ingreso> findByFechaRange(@Param("fechaInicio") Instant fechaInicio, @Param("fechaFin") Instant fechaFin);

  @Query("SELECT COUNT(i) FROM Ingreso i WHERE i.idUsuario.id = :usuarioId")
  long countByUsuario(@Param("usuarioId") Integer usuarioId);

  @Query("SELECT i FROM Ingreso i WHERE i.idUsuario.id = :usuarioId ORDER BY i.fechaIngreso DESC")
  List<Ingreso> findLastIngresosByUsuario(@Param("usuarioId") Integer usuarioId, Pageable pageable);

  @Query("SELECT COUNT(i) FROM Ingreso i WHERE FUNCTION('DATE', i.fechaIngreso) = CURRENT_DATE")
  long countIngresosHoy();

}