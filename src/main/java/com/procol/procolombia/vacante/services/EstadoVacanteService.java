package com.procol.procolombia.vacante.services;

import com.procol.procolombia.vacante.dto.EstadosVacanteDto;

import java.util.List;

public interface EstadoVacanteService {
    void deleteByEstadoVacanteId(Integer vacanteId);
    List<EstadosVacanteDto> getAllEstadosVacante();
    EstadosVacanteDto updateEstadoVacante(EstadosVacanteDto estadosVacanteDto);
    EstadosVacanteDto findById(Integer id);
}
