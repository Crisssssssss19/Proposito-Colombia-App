package com.procol.procolombia.vacante.services;

import com.procol.procolombia.vacante.dto.PalabrasClaveDto;

import java.util.List;

public interface PalabraClaveService {
    PalabrasClaveDto findPalabraClaveById(Integer vacanteId);
    List<PalabrasClaveDto> findAllPalabrasClaves();
    void deletePalabraClave(Integer vacanteId);
}
