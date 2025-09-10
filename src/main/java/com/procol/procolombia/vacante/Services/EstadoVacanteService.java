package com.procol.procolombia.vacante.Services;

import com.procol.procolombia.vacante.dto.EstadosVacanteDto;

import java.util.List;

public interface EstadoVacanteService {
    void deleteByEstadoVacanteId(Integer vacanteId);
    List<EstadosVacanteDto> getAllEstadosVacante();
    List<EstadosVacanteDto> getAllEstadosVacanteById(Integer vacanteId);
    EstadosVacanteDto updateEstadoVacante(EstadosVacanteDto estadosVacanteDto);
}
