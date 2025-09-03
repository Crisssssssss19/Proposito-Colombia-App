package com.procol.procolombia.auth.repositories;

import com.procol.procolombia.auth.entities.Auditoria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface AuditoriaRepository extends JpaRepository<Auditoria, Integer> {

  List<Auditoria> findByIdUsuarioAuditoria(Integer idUsuarioAuditoria);

  List<Auditoria> findByNombreEntidadAuditoria(String nombreEntidad);

  List<Auditoria> findByIdReferenciaAuditoria(Integer idReferencia);

  List<Auditoria> findByTipoCambioAuditoria(String tipoCambio);

  Page<Auditoria> findByIdUsuarioAuditoria(Integer idUsuarioAuditoria, Pageable pageable);

  @Query("SELECT a FROM Auditoria a WHERE a.fechaAuditoria BETWEEN :fechaInicio AND :fechaFin")
  Page<Auditoria> findByFechaRange(@Param("fechaInicio") Instant fechaInicio, @Param("fechaFin") Instant fechaFin, Pageable pageable);

  @Query("SELECT a FROM Auditoria a WHERE a.nombreEntidadAuditoria = :entidad AND a.idReferenciaAuditoria = :referencia ORDER BY a.fechaAuditoria DESC")
  List<Auditoria> findHistorialEntidad(@Param("entidad") String entidad, @Param("referencia") Integer referencia);

  @Query("SELECT COUNT(a) FROM Auditoria a WHERE a.idUsuarioAuditoria = :usuarioId")
  long countByUsuario(@Param("usuarioId") Integer usuarioId);

  @Query("SELECT a FROM Auditoria a WHERE a.comentarioAuditoria LIKE %:texto%")
  List<Auditoria> findByComentarioContaining(@Param("texto") String texto);

}
