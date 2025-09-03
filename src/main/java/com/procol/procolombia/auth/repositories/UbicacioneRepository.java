package com.procol.procolombia.auth.repositories;

import com.procol.procolombia.auth.entities.Ubicacione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UbicacioneRepository extends JpaRepository<Ubicacione, Integer> {

    Optional<Ubicacione> findByIdDaneUbicacion(String idDaneUbicacion);

    List<Ubicacione> findByIdPadreUbicacion_Id(Integer idPadreUbicacion);

    List<Ubicacione> findByIdPadreUbicacionIsNull(); // Ubicaciones padre (países, departamentos)

    List<Ubicacione> findByNombreUbicacionContainingIgnoreCase(String nombre);

    @Query("SELECT u FROM Ubicacione u WHERE u.idPadreUbicacion.id = :padreId")
    List<Ubicacione> findByPadre(@Param("padreId") Integer padreId);

    @Query("SELECT COUNT(us) FROM Usuario us WHERE us.idUbicacion.id = :ubicacionId")
    long countUsuariosByUbicacion(@Param("ubicacionId") Integer ubicacionId);

    @Query("SELECT COUNT(v) FROM Vacante v WHERE v.idUbicacion.id = :ubicacionId")
    long countVacantesByUbicacion(@Param("ubicacionId") Integer ubicacionId);

}