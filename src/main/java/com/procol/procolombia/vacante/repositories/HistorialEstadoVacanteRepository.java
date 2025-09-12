package com.procol.procolombia.vacante.repositories;

import com.procol.procolombia.vacante.dto.HistorialEstadoVacanteDto;
import com.procol.procolombia.vacante.entities.HistorialEstadoVacante;
import com.procol.procolombia.vacante.entities.Vacante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HistorialEstadoVacanteRepository extends JpaRepository<HistorialEstadoVacante, Integer> {
    Optional<HistorialEstadoVacante> findById(Integer id);
    List<HistorialEstadoVacante> findByIdVacante_Id(Integer vacanteId);

}