package com.procol.procolombia.repositories;

import com.procol.procolombia.entities.RelUsuariosEmpresa;
import com.procol.procolombia.entities.RelUsuariosEmpresaId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RelUsuariosEmpresaRepository extends JpaRepository<RelUsuariosEmpresa, RelUsuariosEmpresaId> {
}