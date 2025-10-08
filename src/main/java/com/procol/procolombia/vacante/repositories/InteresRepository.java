package com.procol.procolombia.vacante.repositories;

import com.procol.procolombia.vacante.entities.Interes;
import com.procol.procolombia.vacante.entities.InteresId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InteresRepository extends JpaRepository<Interes, InteresId> {

    @Query("SELECT i.tipoInteres FROM Interes i WHERE i.id.idEmpresa = :idEmpresa AND i.id.idUsuario = :idUsuario")
    Optional<Short> findTipoInteresByEmpresaAndUsuario(@Param("idEmpresa") Integer idEmpresa,
                                                       @Param("idUsuario") Integer idUsuario);


    List<Interes> findByTipoInteres(Short tipoInteres);

    @Query("SELECT i.id.idEmpresa FROM Interes i WHERE i.id.idUsuario = :idUsuario AND i.tipoInteres = :tipoInteres")
    List<Integer> findEmpresasByUsuarioAndTipo(@Param("idUsuario") Integer idUsuario,
                                               @Param("tipoInteres") Short tipoInteres);

    @Query("SELECT i.id.idUsuario FROM Interes i WHERE i.id.idEmpresa = :idEmpresa AND i.tipoInteres = :tipoInteres")
    List<Integer> findUsuariosByEmpresaAndTipo(@Param("idEmpresa") Integer idEmpresa,
                                               @Param("tipoInteres") Short tipoInteres);
}