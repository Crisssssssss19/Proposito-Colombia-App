package com.procol.procolombia.vacante.services;

import com.procol.procolombia.vacante.dto.ModalidadDto;

import java.util.List;

public interface ModalidadService {
    ModalidadDto obtenerModalidadPorId(int idModalidad);
    List<ModalidadDto> listarModalidades();
}
