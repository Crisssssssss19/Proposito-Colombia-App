package com.procol.procolombia.vacante.Services.Impl;


import com.procol.procolombia.vacante.Services.HistorialEstadoVacanteService;
import com.procol.procolombia.vacante.entities.HistorialEstadoVacante;
import com.procol.procolombia.vacante.repositories.HistorialEstadoVacanteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistorialEstadoVacanteServiceImpl implements HistorialEstadoVacanteService {
    private final HistorialEstadoVacanteRepository historialEstadoVacanteRepository;

    public HistorialEstadoVacanteServiceImpl(HistorialEstadoVacanteRepository historialEstadoVacanteRepository) {
        this.historialEstadoVacanteRepository = historialEstadoVacanteRepository;
    }

    @Override
    public HistorialEstadoVacante findEstadoByVacanteId(Integer vacanteId) {
        return historialEstadoVacanteRepository.findTopByVacanteIdOrderByFechaDesc(vacanteId)
                .orElseThrow(() -> new RuntimeException("No se encontró historial para la vacante con ID: " + vacanteId));
    }

    @Override
    public List<HistorialEstadoVacante> findAllEstadosByVacanteId(Integer vacanteId) {
        return historialEstadoVacanteRepository.findByVacanteIdOrderByFechaDesc(vacanteId);
    }

    @Override
    public List<HistorialEstadoVacante> findAllVacantesByEstadoId(Integer estadoId) {
        return historialEstadoVacanteRepository.findByEstadoId(estadoId);
    }
}
