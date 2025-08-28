package com.procol.procolombia.repositories;

import com.procol.procolombia.entities.Postulacione;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PostulacioneRepository extends JpaRepository<Postulacione, Integer> {
    List<Postulacione> findByIdUsuario_Id(Integer idUsuario);

    List<Postulacione> findByIdVacante_Id(Integer idVacante);

    List<Postulacione> findByEstadoPostulacion(Short estadoPostulacion);

    Page<Postulacione> findByIdUsuario_Id(Integer idUsuario, Pageable pageable);

    Page<Postulacione> findByIdVacante_Id(Integer idVacante, Pageable pageable);

    Optional<Postulacione> findByIdUsuario_IdAndIdVacante_Id(Integer idUsuario, Integer idVacante);

    boolean existsByIdUsuario_IdAndIdVacante_Id(Integer idUsuario, Integer idVacante);

    @Query("SELECT p FROM Postulacione p WHERE p.correspondenciaPostulacion = :correspondencia")
    List<Postulacione> findByCorrespondencia(@Param("correspondencia") Short correspondencia);

    @Modifying
    @Query("UPDATE Postulacione p SET p.correspondenciaPostulacion = :correspondencia WHERE p.id = :id")
    void updateCorrespondencia(@Param("id") Integer id, @Param("correspondencia") Short correspondencia);

    @Modifying
    @Query("UPDATE Postulacione p SET p.estadoPostulacion = :estado WHERE p.id = :id")
    void updateEstado(@Param("id") Integer id, @Param("estado") Short estado);

    @Query("SELECT COUNT(p) FROM Postulacione p WHERE p.idVacante.id = :vacanteId")
    long countByVacante(@Param("vacanteId") Integer vacanteId);

    @Query("SELECT COUNT(p) FROM Postulacione p WHERE p.idUsuario.id = :usuarioId")
    long countByUsuario(@Param("usuarioId") Integer usuarioId);

    @Query("SELECT p FROM Postulacione p JOIN p.idVacante v WHERE v.relUsuariosEmpresas.idEmpresa.id = :empresaId")
    Page<Postulacione> findByEmpresa(@Param("empresaId") Integer empresaId, Pageable pageable);

}