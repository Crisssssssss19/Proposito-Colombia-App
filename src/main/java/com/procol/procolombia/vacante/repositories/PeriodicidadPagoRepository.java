package com.procol.procolombia.vacante.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.procol.procolombia.vacante.entities.PeriodicidadPago;
import org.springframework.stereotype.Repository;

@Repository
public interface PeriodicidadPagoRepository extends JpaRepository<PeriodicidadPago, Integer> {

}
