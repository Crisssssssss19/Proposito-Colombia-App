package com.procol.procolombia.repositories;

import com.procol.procolombia.entities.Interese;
import com.procol.procolombia.entities.IntereseId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IntereseRepository extends JpaRepository<Interese, IntereseId> {

    List<Interese> findByIdEmpresa_Id(Integer idEmpresa);

    List<Interese> findByIdUsuario_Id(Integer idUsuario);

    List<Interese> findByTipoInteres(Short tipoInteres);

    @Query("SELECT i FROM Interese i WHERE i.idUsuario.id = :usuarioId AND i.tipoInteres IN (1, 3)")
    List<Interese> findEmpresasQueSigueUsuario(@Param("usuarioId") Integer usuarioId);

    @Query("SELECT i FROM Interese i WHERE i.idEmpresa.id = :empresaId AND i.tipoInteres IN (2, 3)")
    List<Interese> findUsuariosQueSigueEmpresa(@Param("empresaId") Integer empresaId);

    @Query("SELECT i FROM Interese i WHERE i.tipoInteres = 3")
    List<Interese> findInteresesMutuos();

    @Query("SELECT COUNT(i) FROM Interese i WHERE i.idEmpresa.id = :empresaId")
    long countByEmpresa(@Param("empresaId") Integer empresaId);

    @Query("SELECT COUNT(i) FROM Interese i WHERE i.idUsuario.id = :usuarioId")
    long countByUsuario(@Param("usuarioId") Integer usuarioId);

    boolean existsByIdEmpresa_IdAndIdUsuario_Id(Integer idEmpresa, Integer idUsuario);

}