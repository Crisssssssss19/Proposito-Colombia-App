package com.procol.procolombia.vacante.services;

import com.procol.procolombia.vacante.dto.PeriodicidadPagoDto;

import java.util.List;

public interface PeriodicidadPagoService {
    PeriodicidadPagoDto obtenerPeriodicidadPagoPorId(int id);
    List<PeriodicidadPagoDto> listarPeriodicidadPagos();
}
