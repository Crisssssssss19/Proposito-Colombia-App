package com.procol.procolombia.vacante.repositories;

import com.procol.procolombia.vacante.entities.Modalidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModalidadRepository extends JpaRepository<Modalidad, Integer> {
}
