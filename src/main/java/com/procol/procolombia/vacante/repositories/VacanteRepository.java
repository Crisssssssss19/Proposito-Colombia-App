package com.procol.procolombia.vacante.repositories;

import com.procol.procolombia.vacante.entities.Vacante;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface VacanteRepository extends JpaRepository<Vacante, Integer> {

  Page<Vacante> findByEstadoVacante(Short estadoVacante, Pageable pageable);

  Page<Vacante> findByRelUsuariosEmpresas_IdEmpresa_Id(Integer idEmpresa, Pageable pageable);

  Page<Vacante> findByIdUbicacion_Id(Integer idUbicacion, Pageable pageable);

  List<Vacante> findByRelUsuariosEmpresas_IdUsuario_Id(Integer idUsuario);

  @Query("SELECT v FROM Vacante v WHERE v.estadoVacante = 1 AND v.fechaFinVacante > :fechaActual")
  Page<Vacante> findVacantesActivas(@Param("fechaActual") Instant fechaActual, Pageable pageable);

  @Query("SELECT v FROM Vacante v JOIN v.palabrasClaves p WHERE p.textoPalabraClave IN :palabras AND v.estadoVacante = 1")
  Page<Vacante> findByPalabrasClave(@Param("palabras") List<String> palabras, Pageable pageable);

  @Query("SELECT v FROM Vacante v WHERE v.tituloVacante LIKE %:titulo% OR v.detalleVacante LIKE %:detalle%")
  Page<Vacante> findByTituloOrDetalle(@Param("titulo") String titulo, @Param("detalle") String detalle, Pageable pageable);

  @Modifying
  @Query("UPDATE Vacante v SET v.estadoVacante = :estado WHERE v.id = :id")
  void updateEstado(@Param("id") Integer id, @Param("estado") Short estado);

  @Query("SELECT COUNT(v) FROM Vacante v WHERE v.estadoVacante = 1")
  long countVacantesActivas();

  @Query("SELECT v FROM Vacante v WHERE v.fechaFinVacante < :fechaActual AND v.estadoVacante = 1")
  List<Vacante> findVacantesVencidas(@Param("fechaActual") Instant fechaActual);

  @Query("SELECT COUNT(p) FROM Postulacione p WHERE p.idVacante.id = :vacanteId")
  long countPostulacionesByVacante(@Param("vacanteId") Integer vacanteId);
}