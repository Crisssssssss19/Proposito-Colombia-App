package com.procol.procolombia.postulacion.services.EstadosPostulaciones;

import com.procol.procolombia.postulacion.dto.EstadosPostulacioneDto;
import com.procol.procolombia.postulacion.entities.EstadosPostulacione;
import com.procol.procolombia.postulacion.mappers.EstadosPostulacioneMapper;
import com.procol.procolombia.postulacion.repositories.EstadosPostulacioneRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EstadosPostulacioneServiceImpl implements EstadosPostulacionesServices {

    private final EstadosPostulacioneRepository estadosPostulacioneRepository;

    public EstadosPostulacioneServiceImpl(EstadosPostulacioneRepository estadosPostulacioneRepository) {
        this.estadosPostulacioneRepository = estadosPostulacioneRepository;
    }

    @Override
    public List<EstadosPostulacioneDto> findAll() {
        List<EstadosPostulacione> estados = estadosPostulacioneRepository.findAll();
        return EstadosPostulacioneMapper.toDtoList(estados);
    }

    @Override
    public Optional<EstadosPostulacioneDto> findById(Integer id) {
        Optional<EstadosPostulacione> estado = estadosPostulacioneRepository.findById(id);
        return estado.map(EstadosPostulacioneMapper::toDto);
    }

    @Override
    public EstadosPostulacioneDto save(EstadosPostulacioneDto estadosPostulacioneDto) {
        EstadosPostulacione estado = EstadosPostulacioneMapper.toEntity(estadosPostulacioneDto);
        EstadosPostulacione savedEstado = estadosPostulacioneRepository.save(estado);
        return EstadosPostulacioneMapper.toDto(savedEstado);
    }

    @Override
    public EstadosPostulacioneDto update(Integer id, EstadosPostulacioneDto estadosPostulacioneDto) {
        return estadosPostulacioneRepository.findById(id)
                .map(existingEstado -> {
                    existingEstado.setNombreEstadoPostulacion(estadosPostulacioneDto.getNombreEstadoPostulacion());
                    existingEstado.setOrdenEstadoPostulacion(estadosPostulacioneDto.getOrdenEstadoPostulacion());
                    return EstadosPostulacioneMapper.toDto(estadosPostulacioneRepository.save(existingEstado));
                })
                .orElseThrow(() -> new RuntimeException("Estado de postulación no encontrado con id: " + id));
    }

    @Override
    public void deleteById(Integer id) {
        estadosPostulacioneRepository.deleteById(id);
    }

    @Override
    public Optional<EstadosPostulacioneDto> findByNombre(String nombreEstado) {
        Optional<EstadosPostulacione> estado = estadosPostulacioneRepository.findByNombreEstadoPostulacion(nombreEstado);
        return estado.map(EstadosPostulacioneMapper::toDto);
    }

    @Override
    public List<EstadosPostulacioneDto> findAllOrderByOrden() {
        List<EstadosPostulacione> estados = estadosPostulacioneRepository.findByOrderByOrdenEstadoPostulacionAsc();
        return EstadosPostulacioneMapper.toDtoList(estados);
    }

    @Override
    public Optional<EstadosPostulacioneDto> findByOrden(Short orden) {
        Optional<EstadosPostulacione> estado = estadosPostulacioneRepository.findByOrdenEstadoPostulacion(orden);
        return estado.map(EstadosPostulacioneMapper::toDto);
    }

    @Override
    public long countHistorialByEstado(Integer estadoId) {
        return estadosPostulacioneRepository.countHistorialByEstado(estadoId);
    }

    @Override
    public List<EstadosPostulacioneDto> searchByNombre(String nombre) {
        List<EstadosPostulacione> estados = estadosPostulacioneRepository.findByNombreContaining(nombre);
        return EstadosPostulacioneMapper.toDtoList(estados);
    }

    @Override
    public boolean existsByNombre(String nombreEstado) {
        return estadosPostulacioneRepository.existsByNombreEstadoPostulacion(nombreEstado);
    }
}