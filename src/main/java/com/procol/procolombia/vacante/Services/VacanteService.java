package com.procol.procolombia.vacante.Services;

import com.procol.procolombia.vacante.dto.VacanteDto;

import java.util.List;

public interface VacanteService {
    VacanteDto getVacanteById(Integer id);
    VacanteDto createVacante(VacanteDto vacanteDto);
    VacanteDto updateVacante(VacanteDto vacanteDto);
    void deleteVacante(Integer id);
    List<VacanteDto> findAllVacantes();
    List<VacanteDto> getVacantesByEmpresa(Integer empresa);
}
