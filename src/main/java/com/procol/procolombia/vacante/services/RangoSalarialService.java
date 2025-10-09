package com.procol.procolombia.vacante.services;

import com.procol.procolombia.vacante.dto.RangoSalarialDto;

import java.util.List;

public interface RangoSalarialService {
    RangoSalarialDto obtenerRangoSalarialPorId(int id);
    List<RangoSalarialDto> listarRangoSalarial();
}
