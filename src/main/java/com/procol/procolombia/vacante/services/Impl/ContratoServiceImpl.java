package com.procol.procolombia.vacante.services.Impl;

import com.procol.procolombia.vacante.dto.ContratoDto;
import com.procol.procolombia.vacante.entities.Contrato;
import com.procol.procolombia.vacante.mappers.ContratoMapper;
import com.procol.procolombia.vacante.repositories.ContratoRepository;
import com.procol.procolombia.vacante.services.ContratoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContratoServiceImpl implements ContratoService {

    private final ContratoMapper contratoMapper;
    private final ContratoRepository contratoRepository;

    public ContratoServiceImpl(ContratoMapper contratoMapper, ContratoRepository contratoRepository) {
        this.contratoMapper = contratoMapper;
        this.contratoRepository = contratoRepository;
    }

    @Override
    public ContratoDto buscarContratoPorId(int id) {
        return contratoMapper.toContratoDto(contratoRepository.getReferenceById(id));
    }

    @Override
    public List<ContratoDto> listarContratos() {
        return contratoMapper.toContratoDtos(contratoRepository.findAll());
    }
}
