package com.procol.procolombia.vacante.services;

import com.procol.procolombia.vacante.dto.HistorialInteresDto;

import java.util.List;

public interface HistorialInteresService {
    HistorialInteresDto buscarHistorialInteresPorId(int id);
    List<HistorialInteresDto> listarHistorialInteres();
}
