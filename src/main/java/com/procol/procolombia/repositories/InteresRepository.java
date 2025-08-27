package com.procol.procolombia.repositories;

import com.procol.procolombia.entities.Interes;
import com.procol.procolombia.entities.idCompuestas.InteresId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InteresRepository extends JpaRepository<Interes, InteresId> {
}