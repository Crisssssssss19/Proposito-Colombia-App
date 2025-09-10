package com.procol.procolombia.vacante.Services;

import com.procol.procolombia.vacante.dto.PalabrasClaveDto;
import com.procol.procolombia.vacante.entities.PalabraClave;

import java.util.List;

public interface PalabraClaveService {
    PalabrasClaveDto findPalabraClaveById(Integer vacanteId);
    List<PalabrasClaveDto> findAllPalabrasClaves();
    void deletePalabraClave(Integer vacanteId);
}
