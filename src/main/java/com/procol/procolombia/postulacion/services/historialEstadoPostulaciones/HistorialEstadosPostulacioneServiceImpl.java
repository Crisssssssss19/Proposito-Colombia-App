package com.procol.procolombia.postulacion.services.historialEstadoPostulaciones;

import com.procol.procolombia.abtract.AbstractService;
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
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Service
@Transactional
public class HistorialEstadosPostulacioneServiceImpl extends AbstractService<HistorialEstadosPostulacione,HistorialEstadosPostulacioneDto,Integer> implements HistorialEstadosPostulacioneService {

    private final HistorialEstadosPostulacioneRepository historialRepository;
    private final PostulacioneRepository postulacioneRepository;
    private final EstadosPostulacioneRepository estadosPostulacioneRepository;

    public HistorialEstadosPostulacioneServiceImpl(HistorialEstadosPostulacioneRepository historialRepository,
                                                   PostulacioneRepository postulacioneRepository,
                                                   EstadosPostulacioneRepository estadosPostulacioneRepository) {
        this.historialRepository = historialRepository;
        this.postulacioneRepository = postulacioneRepository;
        this.estadosPostulacioneRepository = estadosPostulacioneRepository;
    }

    @Override
    protected JpaRepository<HistorialEstadosPostulacione, Integer> getEntityRepository() {
        return historialRepository;
    }

    @Override
    protected HistorialEstadosPostulacioneDto mapToDto(HistorialEstadosPostulacione entity) {
        return HistorialEstadosPostulacioneMapper.toDto(entity);
    }

    @Override
    protected HistorialEstadosPostulacione mapToEntity(HistorialEstadosPostulacioneDto dto) {
        HistorialEstadosPostulacione historial = HistorialEstadosPostulacioneMapper.toEntity(dto);

        if (dto.getIdPostulacionId() != null) {
            Postulacione postulacion = postulacioneRepository.findById(dto.getIdPostulacionId())
                    .orElseThrow(() -> new RuntimeException("Postulación no encontrada con id: " + dto.getIdPostulacionId()));
            historial.setIdPostulacion(postulacion);
        }

        if (dto.getIdEstadoPostulacion() != null) {
            EstadosPostulacione estado = estadosPostulacioneRepository.findById(dto.getIdEstadoPostulacion())
                    .orElseThrow(() -> new RuntimeException("Estado de postulación no encontrado con id: " + dto.getIdEstadoPostulacion()));
            historial.setIdEstadoPostulacion(estado);
        }

        return historial;
    }

    @Override
    protected List<HistorialEstadosPostulacioneDto> mapToDtoList(List<HistorialEstadosPostulacione> entities) {
        return HistorialEstadosPostulacioneMapper.toDtoList(entities);
    }

    @Override
    protected void updateEntityFromDto(HistorialEstadosPostulacione entity, HistorialEstadosPostulacioneDto dto) {
        entity.setFechaHistorialPostulacion(dto.getFechaHistorialPostulacion());
        entity.setDetalleHistorialPostulacion(dto.getDetalleHistorialPostulacion());

        if (dto.getIdPostulacionId() != null) {
            Postulacione postulacion = postulacioneRepository.findById(dto.getIdPostulacionId())
                    .orElseThrow(() -> new RuntimeException("Postulación no encontrada con id: " + dto.getIdPostulacionId()));
            entity.setIdPostulacion(postulacion);
        }

        if (dto.getIdEstadoPostulacion() != null) {
            EstadosPostulacione estado = estadosPostulacioneRepository.findById(dto.getIdEstadoPostulacion())
                    .orElseThrow(() -> new RuntimeException("Estado de postulación no encontrado con id: " + dto.getIdEstadoPostulacion()));
            entity.setIdEstadoPostulacion(estado);
        }
    }

    @Override
    public List<HistorialEstadosPostulacioneDto> findByPostulacion(Integer idPostulacion) {
        List<HistorialEstadosPostulacione> historiales = historialRepository.findByIdPostulacion_Id(idPostulacion);
        return mapToDtoList(historiales);
    }

    @Override
    public List<HistorialEstadosPostulacioneDto> findByEstado(Integer idEstado) {
        List<HistorialEstadosPostulacione> historiales = historialRepository.findByIdEstadoPostulacion_Id(idEstado);
        return mapToDtoList(historiales);
    }

    @Override
    public List<HistorialEstadosPostulacioneDto> findByPostulacionOrdenado(Integer postulacionId) {
        List<HistorialEstadosPostulacione> historiales = historialRepository.findByPostulacionOrdenado(postulacionId);
        return mapToDtoList(historiales);
    }

    @Override
    public Page<HistorialEstadosPostulacioneDto> findByFechaRange(Instant fechaInicio, Instant fechaFin, Pageable pageable) {
        Page<HistorialEstadosPostulacione> historiales = historialRepository.findByFechaRange(fechaInicio, fechaFin, pageable);
        return historiales.map(this::mapToDto);
    }

    @Override
    public long countByPostulacion(Integer postulacionId) {
        return historialRepository.countByPostulacion(postulacionId);
    }

    @Override
    public List<HistorialEstadosPostulacioneDto> searchByDetalle(String detalle) {
        List<HistorialEstadosPostulacione> historiales = historialRepository.findByDetalleContaining(detalle);
        return mapToDtoList(historiales);
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