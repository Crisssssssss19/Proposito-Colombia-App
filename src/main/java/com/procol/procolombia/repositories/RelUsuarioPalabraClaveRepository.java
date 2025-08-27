package com.procol.procolombia.repositories;

import com.procol.procolombia.entities.RelUsuarioPalabraClave;
import com.procol.procolombia.entities.idCompuestas.RelUsuarioPalabraClaveId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RelUsuarioPalabraClaveRepository extends JpaRepository<RelUsuarioPalabraClave, RelUsuarioPalabraClaveId> {
}