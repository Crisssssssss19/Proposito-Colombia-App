package com.procol.procolombia.vacante.Services.Impl;

import com.procol.procolombia.vacante.Services.VacanteService;
import com.procol.procolombia.vacante.dto.VacanteDto;
import com.procol.procolombia.vacante.entities.Vacante;
import com.procol.procolombia.vacante.mappers.VacanteMapper;
import com.procol.procolombia.vacante.repositories.VacanteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VacanteServiceImpl implements VacanteService {
    private final VacanteRepository vacanteRepository;
    private final VacanteMapper vacanteMapper;

    public VacanteServiceImpl(VacanteRepository vacanteRepository,
                              VacanteMapper vacanteMapper) {
        this.vacanteRepository = vacanteRepository;
        this.vacanteMapper = vacanteMapper;
    }

    @Override
    public VacanteDto getVacanteById(Integer id) {
        return vacanteRepository.findById(id)
                .map(vacanteMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Vacante con id " + id + " no existe"));
    }

    @Override
    public VacanteDto createVacante(VacanteDto vacanteDto) {
        Vacante entity = vacanteMapper.toEntity(vacanteDto);
        Vacante saved = vacanteRepository.save(entity);
        return vacanteMapper.toDto(saved);
    }

    @Override
    public VacanteDto updateVacante(VacanteDto vacanteDto) {
        if (vacanteDto.id() == null || !vacanteRepository.existsById(vacanteDto.id())) {
            throw new EntityNotFoundException(
                    "No se puede actualizar, Vacante con id " + vacanteDto.id() + " no existe");
        }
        Vacante entity = vacanteMapper.toEntity(vacanteDto);
        Vacante updated = vacanteRepository.save(entity);
        return vacanteMapper.toDto(updated);
    }

    @Override
    public void deleteVacante(Integer id) {
        if (vacanteRepository.existsById(id)) {
            vacanteRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Vacante con id " + id + " no existe");
        }
    }

    @Override
    public List<VacanteDto> findAllVacantes() {
        return vacanteRepository.findAll()
                .stream()
                .map(vacanteMapper::toDto)
                .toList();
    }

    @Override
    public List<VacanteDto> getVacantesByEmpresa(Integer empresaId) {
        return vacanteRepository.findByRelUsuarioEmpresasIdEmpresaId(empresaId)
                .stream()
                .map(vacanteMapper::toDto)
                .toList();
    }
}
