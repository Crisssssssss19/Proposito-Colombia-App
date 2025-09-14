package com.procol.procolombia.vacante.repositories;

import com.procol.procolombia.vacante.entities.Empresa;
import com.procol.procolombia.vacante.entities.TipoEmpresa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Integer> {

  Page<Empresa> findBynombreEmpresaContainingIgnoreCase(String filtro, Pageable pageable);
  @Query("SELECT e FROM Empresa e WHERE e.idTipoEmpresa.id = :idTipoEmpresa")
  List<Empresa> findEmpresasByTipoEmpresaId(@Param("idTipoEmpresa") Integer idTipoEmpresa);

}