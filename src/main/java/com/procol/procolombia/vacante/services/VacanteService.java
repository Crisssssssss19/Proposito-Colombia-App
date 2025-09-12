package com.procol.procolombia.vacante.services;

import com.procol.procolombia.vacante.dto.VacanteDto;

import java.util.List;

public interface VacanteService {
    VacanteDto getVacanteById(Integer id);
    VacanteDto createVacante(VacanteDto vacanteDto);
    VacanteDto updateVacante(Integer vacanteId, VacanteDto vacanteDto);
    void deleteVacante(Integer id);
    List<VacanteDto> findAllVacantes();
    List<VacanteDto> getVacantesByEmpresa(Integer empresa);
    List<VacanteDto> getVacantesByUsuarioAndEmpresa(Integer idUsuario, Integer idEmpresa);
}
