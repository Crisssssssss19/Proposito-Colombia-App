package com.procol.procolombia.vacante.services.Impl;

import com.procol.procolombia.auth.entities.Ubicacione;
import com.procol.procolombia.auth.repositories.UbicacioneRepository;
import com.procol.procolombia.vacante.services.VacanteService;
import com.procol.procolombia.vacante.dto.VacanteDto;
import com.procol.procolombia.vacante.entities.*;
import com.procol.procolombia.vacante.mappers.VacanteMapper;
import com.procol.procolombia.vacante.repositories.AnuncioRepository;
import com.procol.procolombia.vacante.repositories.PalabraClaveRepository;
import com.procol.procolombia.vacante.repositories.VacanteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class VacanteServiceImpl implements VacanteService {
    private final VacanteRepository vacanteRepository;
    private final VacanteMapper vacanteMapper;
    private final UbicacioneRepository ubicacioneRepository;
    private final AnuncioRepository anuncioRepository;
    private final PalabraClaveRepository palabraClaveRepository;

    public VacanteServiceImpl(VacanteRepository vacanteRepository,
                              VacanteMapper vacanteMapper, UbicacioneRepository ubicacioneRepository, AnuncioRepository anuncioRepository, PalabraClaveRepository palabraClaveRepository) {
        this.vacanteRepository = vacanteRepository;
        this.vacanteMapper = vacanteMapper;
        this.ubicacioneRepository = ubicacioneRepository;
        this.anuncioRepository = anuncioRepository;
        this.palabraClaveRepository = palabraClaveRepository;
    }

    @Override
    @Transactional
    public VacanteDto getVacanteById(Integer id) {
        return vacanteRepository.findById(id)
                .map(vacanteMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Vacante con id " + id + " no existe"));
    }

    @Override
    public VacanteDto createVacante(VacanteDto vacanteDto) {
        Vacante vacante = vacanteMapper.toEntity(vacanteDto);

        if (vacanteDto.palabrasClaveIds() != null && !vacanteDto.palabrasClaveIds().isEmpty()) {
            Set<PalabraClave> palabras = vacanteDto.palabrasClaveIds().stream()
                    .map(id -> palabraClaveRepository.findById(id)
                            .orElseThrow(() -> new RuntimeException("PalabraClave no encontrada con id: " + id)))
                    .collect(Collectors.toSet());

            vacante.setPalabrasClaves(palabras);
        }

        Vacante guardada = vacanteRepository.save(vacante);

        return vacanteMapper.toDto(guardada);
    }

    @Override
    @Transactional
    public VacanteDto updateVacante(Integer id, VacanteDto vacanteDto) {
        Vacante existing = vacanteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vacante no encontrada: " + id));

        Ubicacione ubicacione = ubicacioneRepository.findById(vacanteDto.idUbicacion())
                .orElseThrow(() -> new RuntimeException("Ubicación no encontrada con id: " + vacanteDto.idUbicacion()));

        existing.setIdUbicacion(ubicacione);
        existing.setTituloVacante(vacanteDto.tituloVacante());
        existing.setDetalleVacante(vacanteDto.detalleVacante());
        existing.setFechaInicioVacante(vacanteDto.fechaInicioVacante());
        existing.setFechaFinVacante(vacanteDto.fechaFinVacante());
        existing.setEstadoVacante(vacanteDto.estadoVacante());
        existing.getPalabrasClaves().clear();
        if (vacanteDto.palabrasClaveIds() != null) {
            Set<PalabraClave> nuevasPalabras = vacanteDto.palabrasClaveIds().stream()
                    .map(idPalabra -> {
                        PalabraClave p = new PalabraClave();
                        p.setId(idPalabra);
                        return p;
                    })
                    .collect(Collectors.toSet());
            existing.getPalabrasClaves().addAll(nuevasPalabras);
        }
        existing.getRequisitos().clear();
        if (vacanteDto.requisitoIds() != null) {
            Set<Requisito> nuevosRequisitos = vacanteDto.requisitoIds().stream()
                    .map(idReq -> {
                        Requisito r = new Requisito();
                        r.setId(idReq);
                        return r;
                    })
                    .collect(Collectors.toSet());
            existing.getRequisitos().addAll(nuevosRequisitos);
        }


        Vacante updated = vacanteRepository.save(existing);
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
    @Transactional
    public List<VacanteDto> findAllVacantes() {
        return vacanteRepository.findAll()
                .stream()
                .map(vacanteMapper::toDto)
                .toList();
    }

    @Override
    @Transactional
    public List<VacanteDto> getVacantesByEmpresa(Integer empresaId) {
        return vacanteRepository.findByRelUsuarioEmpresasIdEmpresaId(empresaId)
                .stream()
                .map(vacanteMapper::toDto)
                .toList();
    }
    @Override
    @Transactional
    public List<VacanteDto> getVacantesByUsuarioAndEmpresa(Integer idUsuario, Integer idEmpresa) {
        return vacanteRepository.findByUsuarioAndEmpresa(idUsuario, idEmpresa)
                .stream()
                .map(vacanteMapper::toDto)
                .toList();
    }
}
