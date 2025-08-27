package com.procol.procolombia.repositories;

import com.procol.procolombia.entities.RelVacantePalabraClave;
import com.procol.procolombia.entities.idCompuestas.RelVacantePalabraClaveId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RelVacantePalabraClaveRepository extends JpaRepository<RelVacantePalabraClave, RelVacantePalabraClaveId> {
}