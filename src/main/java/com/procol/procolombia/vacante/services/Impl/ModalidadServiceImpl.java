package com.procol.procolombia.vacante.services.Impl;

import com.procol.procolombia.vacante.dto.ModalidadDto;
import com.procol.procolombia.vacante.mappers.ModalidadMapper;
import com.procol.procolombia.vacante.repositories.ModalidadRepository;
import com.procol.procolombia.vacante.services.ModalidadService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModalidadServiceImpl implements ModalidadService {
    private final ModalidadMapper modalidadMapper;
    private final ModalidadRepository modalidadRepository;

    public ModalidadServiceImpl(ModalidadMapper modalidadMapper, ModalidadRepository modalidadRepository) {
        this.modalidadMapper = modalidadMapper;
        this.modalidadRepository = modalidadRepository;
    }

    @Override
    public ModalidadDto obtenerModalidadPorId(int idModalidad) {
        return modalidadMapper.toModalidadDto(modalidadRepository.getReferenceById(idModalidad));
    }

    @Override
    public List<ModalidadDto> listarModalidades() {
        return modalidadMapper.toModalidadeDtos(modalidadRepository.findAll());
    }
}
