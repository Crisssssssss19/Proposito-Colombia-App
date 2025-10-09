package com.procol.procolombia.vacante.services.Impl;

import com.procol.procolombia.vacante.dto.HistorialInteresDto;
import com.procol.procolombia.vacante.mappers.HistorialInteresMapper;
import com.procol.procolombia.vacante.repositories.HistorialInteresRepository;
import com.procol.procolombia.vacante.services.HistorialInteresService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistorialInteresImpl implements HistorialInteresService {

    private final HistorialInteresMapper historialInteresMapper;
    private final HistorialInteresRepository historialInteresRepository;

    public HistorialInteresImpl(HistorialInteresMapper historialInteresMapper, HistorialInteresRepository historialInteresRepository) {
        this.historialInteresMapper = historialInteresMapper;
        this.historialInteresRepository = historialInteresRepository;
    }

    @Override
    public HistorialInteresDto buscarHistorialInteresPorId(int id) {
        return historialInteresMapper.toHistorialInteresDto(historialInteresRepository.getReferenceById(id));
    }

    @Override
    public List<HistorialInteresDto> listarHistorialInteres() {
        return historialInteresMapper.toHistorialInteresDtos(historialInteresRepository.findAll());
    }
}
