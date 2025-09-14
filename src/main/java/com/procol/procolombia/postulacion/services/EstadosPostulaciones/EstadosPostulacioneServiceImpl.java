package com.procol.procolombia.postulacion.services.EstadosPostulaciones;

import com.procol.procolombia.abtract.AbstractService;
import com.procol.procolombia.postulacion.dto.EstadosPostulacioneDto;
import com.procol.procolombia.postulacion.entities.EstadosPostulacione;
import com.procol.procolombia.postulacion.mappers.EstadosPostulacioneMapper;
import com.procol.procolombia.postulacion.repositories.EstadosPostulacioneRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EstadosPostulacioneServiceImpl extends AbstractService<EstadosPostulacione, EstadosPostulacioneDto, Integer> implements EstadosPostulacionesServices {

    private final EstadosPostulacioneRepository estadosPostulacioneRepository;

    public EstadosPostulacioneServiceImpl(EstadosPostulacioneRepository estadosPostulacioneRepository) {
        this.estadosPostulacioneRepository = estadosPostulacioneRepository;
    }

    @Override
    protected JpaRepository<EstadosPostulacione, Integer> getEntityRepository() {
        return estadosPostulacioneRepository;
    }

    @Override
    protected EstadosPostulacioneDto mapToDto(EstadosPostulacione entity) {
        return EstadosPostulacioneMapper.toDto(entity);
    }

    @Override
    protected EstadosPostulacione mapToEntity(EstadosPostulacioneDto dto) {
        return EstadosPostulacioneMapper.toEntity(dto);
    }

    @Override
    protected List<EstadosPostulacioneDto> mapToDtoList(List<EstadosPostulacione> entities) {
        return EstadosPostulacioneMapper.toDtoList(entities);
    }

    @Override
    protected void updateEntityFromDto(EstadosPostulacione entity, EstadosPostulacioneDto dto) {
        entity.setNombreEstadoPostulacion(dto.getNombreEstadoPostulacion());
        entity.setOrdenEstadoPostulacion(dto.getOrdenEstadoPostulacion());
    }

    @Override
    public Optional<EstadosPostulacioneDto> findByNombre(String nombreEstado) {
        Optional<EstadosPostulacione> estado = estadosPostulacioneRepository.findByNombreEstadoPostulacion(nombreEstado);
        return estado.map(this::mapToDto);
    }

    @Override
    public List<EstadosPostulacioneDto> findAllOrderByOrden() {
        List<EstadosPostulacione> estados = estadosPostulacioneRepository.findByOrderByOrdenEstadoPostulacionAsc();
        return mapToDtoList(estados);
    }

    @Override
    public Optional<EstadosPostulacioneDto> findByOrden(Short orden) {
        Optional<EstadosPostulacione> estado = estadosPostulacioneRepository.findByOrdenEstadoPostulacion(orden);
        return estado.map(this::mapToDto);
    }

    @Override
    public long countHistorialByEstado(Integer estadoId) {
        return estadosPostulacioneRepository.countHistorialByEstado(estadoId);
    }

    @Override
    public List<EstadosPostulacioneDto> searchByNombre(String nombre) {
        List<EstadosPostulacione> estados = estadosPostulacioneRepository.findByNombreContaining(nombre);
        return mapToDtoList(estados);
    }

    @Override
    public boolean existsByNombre(String nombreEstado) {
        return estadosPostulacioneRepository.existsByNombreEstadoPostulacion(nombreEstado);
    }
}