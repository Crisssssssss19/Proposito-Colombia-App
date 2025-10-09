package com.procol.procolombia.vacante.services.Impl;

import com.procol.procolombia.vacante.dto.PeriodicidadPagoDto;
import com.procol.procolombia.vacante.mappers.PeriodicidadPagoMapper;
import com.procol.procolombia.vacante.repositories.PeriodicidadPagoRepository;
import com.procol.procolombia.vacante.services.PeriodicidadPagoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PeriodicidadPagoServiceImpl implements PeriodicidadPagoService {
    private final PeriodicidadPagoMapper periodicidadPagoMapper;
    private final PeriodicidadPagoRepository periodicidadPagoRepository;

    public PeriodicidadPagoServiceImpl(PeriodicidadPagoMapper periodicidadPagoMapper, PeriodicidadPagoRepository periodicidadPagoRepository) {
        this.periodicidadPagoMapper = periodicidadPagoMapper;
        this.periodicidadPagoRepository = periodicidadPagoRepository;
    }

    @Override
    public PeriodicidadPagoDto obtenerPeriodicidadPagoPorId(int id) {
        return periodicidadPagoMapper.toPeriodicidadPagoDto(periodicidadPagoRepository.getReferenceById(id));
    }

    @Override
    public List<PeriodicidadPagoDto> listarPeriodicidadPagos() {
        return periodicidadPagoMapper.toPeriodicidadPagoDtos(periodicidadPagoRepository.findAll());
    }
}
