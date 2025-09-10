package com.procol.procolombia.vacante.repositories;

import com.procol.procolombia.vacante.entities.PalabraClave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PalabraClaveRepository extends JpaRepository<PalabraClave, Integer> {
}