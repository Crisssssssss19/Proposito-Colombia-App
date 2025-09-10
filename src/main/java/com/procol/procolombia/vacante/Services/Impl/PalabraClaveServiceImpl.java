package com.procol.procolombia.vacante.Services.Impl;

import com.procol.procolombia.vacante.Services.PalabraClaveService;
import com.procol.procolombia.vacante.dto.PalabrasClaveDto;
import com.procol.procolombia.vacante.mappers.PalabraClaveMapper;
import com.procol.procolombia.vacante.repositories.PalabraClaveRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PalabraClaveServiceImpl implements PalabraClaveService {
    private final PalabraClaveRepository palabraClaveRepository;
    private final PalabraClaveMapper palabrasClaveMapper;

    public PalabraClaveServiceImpl(PalabraClaveRepository palabraClaveRepository,
                                   PalabraClaveMapper palabrasClaveMapper) {
        this.palabraClaveRepository = palabraClaveRepository;
        this.palabrasClaveMapper = palabrasClaveMapper;
    }
    @Override
    @Transactional(readOnly = true)
    public PalabrasClaveDto findPalabraClaveById(Integer vacanteId) {
        return palabraClaveRepository.findById(vacanteId)
                .map(palabrasClaveMapper::toDto)
                .orElse(null);
    }
    @Override
    @Transactional(readOnly = true)
    public List<PalabrasClaveDto> findAllPalabrasClaves() {
        return palabraClaveRepository.findAll()
                .stream()
                .map(palabrasClaveMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deletePalabraClave(Integer vacanteId) {
        if (palabraClaveRepository.existsById(vacanteId)) {
            palabraClaveRepository.deleteById(vacanteId);
        }
    }
}
