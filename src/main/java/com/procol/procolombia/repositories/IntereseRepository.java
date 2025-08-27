package com.procol.procolombia.repositories;

import com.procol.procolombia.entities.Interese;
import com.procol.procolombia.entities.IntereseId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IntereseRepository extends JpaRepository<Interese, IntereseId> {
}