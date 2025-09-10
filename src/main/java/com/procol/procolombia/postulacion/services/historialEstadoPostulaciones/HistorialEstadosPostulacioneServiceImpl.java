package com.procol.procolombia.postulacion.services.historialEstadoPostulaciones;

import com.procol.procolombia.postulacion.dto.HistorialEstadosPostulacioneDto;
import com.procol.procolombia.postulacion.entities.HistorialEstadosPostulacione;
import com.procol.procolombia.postulacion.entities.Postulacione;
import com.procol.procolombia.postulacion.entities.EstadosPostulacione;
import com.procol.procolombia.postulacion.mappers.HistorialEstadosPostulacioneMapper;
import com.procol.procolombia.postulacion.repositories.HistorialEstadosPostulacioneRepository;
import com.procol.procolombia.postulacion.repositories.PostulacioneRepository;
import com.procol.procolombia.postulacion.repositories.EstadosPostulacioneRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class HistorialEstadosPostulacioneServiceImpl implements HistorialEstadosPostulacioneService {

    private final HistorialEstadosPostulacioneRepository historialRepository;

    private final PostulacioneRepository postulacioneRepository;

    private final EstadosPostulacioneRepository estadosPostulacioneRepository;

    public HistorialEstadosPostulacioneServiceImpl(HistorialEstadosPostulacioneRepository historialRepository, PostulacioneRepository postulacioneRepository, EstadosPostulacioneRepository estadosPostulacioneRepository) {
        this.historialRepository = historialRepository;
        this.postulacioneRepository = postulacioneRepository;
        this.estadosPostulacioneRepository = estadosPostulacioneRepository;
    }

    @Override
    public List<HistorialEstadosPostulacioneDto> findAll() {
        List<HistorialEstadosPostulacione> historiales = historialRepository.findAll();
        return HistorialEstadosPostulacioneMapper.toDtoList(historiales);
    }

    @Override
    public Optional<HistorialEstadosPostulacioneDto> findById(Integer id) {
        Optional<HistorialEstadosPostulacione> historial = historialRepository.findById(id);
        return historial.map(HistorialEstadosPostulacioneMapper::toDto);
    }

    @Override
    public HistorialEstadosPostulacioneDto save(HistorialEstadosPostulacioneDto historialDto) {
        HistorialEstadosPostulacione historial = HistorialEstadosPostulacioneMapper.toEntity(historialDto);

        if (historialDto.getIdPostulacionId() != null) {
            Postulacione postulacion = postulacioneRepository.findById(historialDto.getIdPostulacionId())
                    .orElseThrow(() -> new RuntimeException("Postulación no encontrada con id: " + historialDto.getIdPostulacionId()));
            historial.setIdPostulacion(postulacion);
        }

        if (historialDto.getIdEstadoPostulacion() != null) {
            EstadosPostulacione estado = estadosPostulacioneRepository.findById(historialDto.getIdEstadoPostulacion())
                    .orElseThrow(() -> new RuntimeException("Estado de postulación no encontrado con id: " + historialDto.getIdEstadoPostulacion()));
            historial.setIdEstadoPostulacion(estado);
        }

        HistorialEstadosPostulacione savedHistorial = historialRepository.save(historial);
        return HistorialEstadosPostulacioneMapper.toDto(savedHistorial);
    }

    @Override
    public HistorialEstadosPostulacioneDto update(Integer id, HistorialEstadosPostulacioneDto historialDto) {
        return historialRepository.findById(id)
                .map(existingHistorial -> {
                    existingHistorial.setFechaHistorialPostulacion(historialDto.getFechaHistorialPostulacion());
                    existingHistorial.setDetalleHistorialPostulacion(historialDto.getDetalleHistorialPostulacion());

                    if (historialDto.getIdPostulacionId() != null) {
                        Postulacione postulacion = postulacioneRepository.findById(historialDto.getIdPostulacionId())
                                .orElseThrow(() -> new RuntimeException("Postulación no encontrada con id: " + historialDto.getIdPostulacionId()));
                        existingHistorial.setIdPostulacion(postulacion);
                    }

                    if (historialDto.getIdEstadoPostulacion() != null) {
                        EstadosPostulacione estado = estadosPostulacioneRepository.findById(historialDto.getIdEstadoPostulacion())
                                .orElseThrow(() -> new RuntimeException("Estado de postulación no encontrado con id: " + historialDto.getIdEstadoPostulacion()));
                        existingHistorial.setIdEstadoPostulacion(estado);
                    }

                    return HistorialEstadosPostulacioneMapper.toDto(historialRepository.save(existingHistorial));
                })
                .orElseThrow(() -> new RuntimeException("Historial no encontrado con id: " + id));
    }

    @Override
    public void deleteById(Integer id) {
        historialRepository.deleteById(id);
    }

    @Override
    public List<HistorialEstadosPostulacioneDto> findByPostulacion(Integer idPostulacion) {
        List<HistorialEstadosPostulacione> historiales = historialRepository.findByIdPostulacion_Id(idPostulacion);
        return HistorialEstadosPostulacioneMapper.toDtoList(historiales);
    }

    @Override
    public List<HistorialEstadosPostulacioneDto> findByEstado(Integer idEstado) {
        List<HistorialEstadosPostulacione> historiales = historialRepository.findByIdEstadoPostulacion_Id(idEstado);
        return HistorialEstadosPostulacioneMapper.toDtoList(historiales);
    }

    @Override
    public List<HistorialEstadosPostulacioneDto> findByPostulacionOrdenado(Integer postulacionId) {
        List<HistorialEstadosPostulacione> historiales = historialRepository.findByPostulacionOrdenado(postulacionId);
        return HistorialEstadosPostulacioneMapper.toDtoList(historiales);
    }

    @Override
    public Page<HistorialEstadosPostulacioneDto> findByFechaRange(Instant fechaInicio, Instant fechaFin, Pageable pageable) {
        Page<HistorialEstadosPostulacione> historiales = historialRepository.findByFechaRange(fechaInicio, fechaFin, pageable);
        return historiales.map(HistorialEstadosPostulacioneMapper::toDto);
    }

    @Override
    public long countByPostulacion(Integer postulacionId) {
        return historialRepository.countByPostulacion(postulacionId);
    }

    @Override
    public List<HistorialEstadosPostulacioneDto> searchByDetalle(String detalle) {
        List<HistorialEstadosPostulacione> historiales = historialRepository.findByDetalleContaining(detalle);
        return HistorialEstadosPostulacioneMapper.toDtoList(historiales);
    }

    @Override
    public HistorialEstadosPostulacioneDto crearHistorial(Integer postulacionId, Integer estadoId, String detalle) {
        HistorialEstadosPostulacioneDto historialDto = new HistorialEstadosPostulacioneDto();
        historialDto.setIdPostulacionId(postulacionId);
        historialDto.setIdEstadoPostulacion(estadoId);
        historialDto.setFechaHistorialPostulacion(Instant.now());
        historialDto.setDetalleHistorialPostulacion(detalle);
        return save(historialDto);
    }
}