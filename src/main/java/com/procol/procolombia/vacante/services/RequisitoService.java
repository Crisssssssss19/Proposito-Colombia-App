package com.procol.procolombia.vacante.services;

import com.procol.procolombia.vacante.dto.RequisitoDto;

import java.util.List;

public interface RequisitoService {
    RequisitoDto createRequisito(RequisitoDto requisitoDto);
    RequisitoDto updateRequisito(Integer id, RequisitoDto requisitoDto);
    void deleteRequisito(Integer id);
    List<RequisitoDto> findAllRequisitos();
    List<RequisitoDto> getRequisitosByIdVacante(Integer idVacante);
}
