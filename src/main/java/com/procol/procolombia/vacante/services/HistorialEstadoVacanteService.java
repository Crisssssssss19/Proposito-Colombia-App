package com.procol.procolombia.vacante.services;

import com.procol.procolombia.vacante.dto.HistorialEstadoVacanteDto;

import java.util.List;

public interface HistorialEstadoVacanteService {
    void deleteHistorialEstadoVacante(HistorialEstadoVacanteDto historialEstadoVacanteDto);
    HistorialEstadoVacanteDto findById(Integer id);
    List<HistorialEstadoVacanteDto> findByVacanteId(Integer vacanteId);
}
