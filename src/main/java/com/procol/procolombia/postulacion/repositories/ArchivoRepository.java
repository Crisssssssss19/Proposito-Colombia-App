package com.procol.procolombia.postulacion.repositories;

import com.procol.procolombia.postulacion.dto.ArchivoDto;
import com.procol.procolombia.postulacion.entities.Archivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArchivoRepository extends JpaRepository<Archivo, Integer> {

  @Query("SELECT a FROM Archivo a LEFT JOIN FETCH a.idUsuario")
  List<Archivo> findAllWithUsuario();

  List<Archivo> findByIdUsuario_Id(Integer idUsuario);

  List<Archivo> findByGrupoArchivo(Short grupoArchivo);

  List<Archivo> findByTipoArchivo(String tipoArchivo);

  @Query("SELECT a FROM Archivo a WHERE a.idUsuario.id = :usuarioId AND a.grupoArchivo = :grupo")
  List<Archivo> findByUsuarioAndGrupo(@Param("usuarioId") Integer usuarioId, @Param("grupo") Short grupo);

  @Query("SELECT COUNT(a) FROM Archivo a WHERE a.idUsuario.id = :usuarioId")
  long countByUsuario(@Param("usuarioId") Integer usuarioId);

  List<Archivo> findByNombrePublicoArchivoContainingIgnoreCase(String nombre);

}