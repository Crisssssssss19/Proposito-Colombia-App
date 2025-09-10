package com.procol.procolombia.vacante.repositories;

import com.procol.procolombia.vacante.entities.Interes;
import com.procol.procolombia.vacante.entities.InteresId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InteresRepository extends JpaRepository<Interes, InteresId> {

    List<Interes> findByIdEmpresa_Id(Integer idEmpresa);

    List<Interes> findByIdUsuario_Id(Integer idUsuario);

    List<Interes> findByTipoInteres(Short tipoInteres);

    @Query("SELECT i FROM Interes i WHERE i.idUsuario.id = :usuarioId AND i.tipoInteres IN (1, 3)")
    List<Interes> findEmpresasQueSigueUsuario(@Param("usuarioId") Integer usuarioId);

    @Query("SELECT i FROM Interes i WHERE i.idEmpresa.id = :empresaId AND i.tipoInteres IN (2, 3)")
    List<Interes> findUsuariosQueSigueEmpresa(@Param("empresaId") Integer empresaId);

    @Query("SELECT i FROM Interes i WHERE i.tipoInteres = 3")
    List<Interes> findInteresesMutuos();

    @Query("SELECT COUNT(i) FROM Interes i WHERE i.idEmpresa.id = :empresaId")
    long countByEmpresa(@Param("empresaId") Integer empresaId);

    @Query("SELECT COUNT(i) FROM Interes i WHERE i.idUsuario.id = :usuarioId")
    long countByUsuario(@Param("usuarioId") Integer usuarioId);

    boolean existsByIdEmpresa_IdAndIdUsuario_Id(Integer idEmpresa, Integer idUsuario);

}