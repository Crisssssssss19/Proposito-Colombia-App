package com.procol.procolombia.vacante.services.Impl;

import com.procol.procolombia.vacante.dto.RangoSalarialDto;
import com.procol.procolombia.vacante.mappers.RangoSalarialMapper;
import com.procol.procolombia.vacante.repositories.RangoSalarialRepository;
import com.procol.procolombia.vacante.services.RangoSalarialService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RangoSalarialServiceImpl implements RangoSalarialService {
    private final RangoSalarialMapper rangoSalarialMapper;
    private final RangoSalarialRepository rangoSalarialRepository;

    public RangoSalarialServiceImpl(RangoSalarialMapper rangoSalarialMapper, RangoSalarialRepository rangoSalarialRepository) {
        this.rangoSalarialMapper = rangoSalarialMapper;
        this.rangoSalarialRepository = rangoSalarialRepository;
    }

    @Override
    public RangoSalarialDto obtenerRangoSalarialPorId(int id) {
        return rangoSalarialMapper.toRangoSalarialDto(rangoSalarialRepository.getReferenceById(id));
    }

    @Override
    public List<RangoSalarialDto> listarRangoSalarial() {
        return rangoSalarialMapper.toRangosSalarialDto(rangoSalarialRepository.findAll());
    }
}
