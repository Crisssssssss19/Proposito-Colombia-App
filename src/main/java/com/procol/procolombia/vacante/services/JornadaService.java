package com.procol.procolombia.vacante.services;

import com.procol.procolombia.vacante.dto.JornadaDto;

public interface JornadaService {
    JornadaDto createJornada(JornadaDto jornadaDto);
    JornadaDto updateJornada(JornadaDto jornadaDto);
    JornadaDto getJornadaById(int id);
    void deleteJornada(Integer id);

}
