package com.procol.procolombia.postulacion.repositories;

import com.procol.procolombia.postulacion.entities.Mensaje;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface MensajeRepository extends JpaRepository<Mensaje, Integer> {

  List<Mensaje> findByIdPostulacion_Id(Integer idPostulacion);

  List<Mensaje> findByIdUsuarioResponde_Id(Integer idUsuarioResponde);

  List<Mensaje> findByEstadoMensaje(Short estadoMensaje);

  Page<Mensaje> findByIdPostulacion_Id(Integer idPostulacion, Pageable pageable);

  @Query("SELECT m FROM Mensaje m WHERE m.idPostulacion.id = :postulacionId AND m.estadoMensaje = 1 ORDER BY m.fechaMensaje ASC")
  List<Mensaje> findMensajesVisiblesByPostulacion(@Param("postulacionId") Integer postulacionId);

  @Query("SELECT COUNT(m) FROM Mensaje m WHERE m.idPostulacion.id = :postulacionId")
  long countByPostulacion(@Param("postulacionId") Integer postulacionId);

  @Query("SELECT m FROM Mensaje m WHERE m.fechaMensaje BETWEEN :fechaInicio AND :fechaFin")
  List<Mensaje> findByFechaRange(@Param("fechaInicio") Instant fechaInicio, @Param("fechaFin") Instant fechaFin);

}