package com.procol.procolombia.vacante.services;

import com.procol.procolombia.vacante.dto.ContratoDto;

import java.util.List;

public interface ContratoService {
    ContratoDto buscarContratoPorId(int id);
    List<ContratoDto> listarContratos();
}
