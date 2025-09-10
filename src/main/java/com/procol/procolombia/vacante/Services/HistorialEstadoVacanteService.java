package com.procol.procolombia.vacante.Services;

import com.procol.procolombia.vacante.entities.HistorialEstadoVacante;

import java.util.List;

public interface HistorialEstadoVacanteService {
    HistorialEstadoVacante findEstadoByVacanteId(Integer vacanteId);
    List<HistorialEstadoVacante> findAllEstadosByVacanteId(Integer vacanteId);
    List<HistorialEstadoVacante> findAllVacantesByEstadoId(Integer estadoId);
}
