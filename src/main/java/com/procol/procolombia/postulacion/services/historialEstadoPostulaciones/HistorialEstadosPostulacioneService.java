package com.procol.procolombia.postulacion.services.historialEstadoPostulaciones;

import com.procol.procolombia.postulacion.dto.HistorialEstadosPostulacioneDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

public interface HistorialEstadosPostulacioneService {
    List<HistorialEstadosPostulacioneDto> findAll();
    Optional<HistorialEstadosPostulacioneDto> findById(Integer id);
    HistorialEstadosPostulacioneDto save(HistorialEstadosPostulacioneDto historialDto);
    HistorialEstadosPostulacioneDto update(Integer id, HistorialEstadosPostulacioneDto historialDto);
    void deleteById(Integer id);
    List<HistorialEstadosPostulacioneDto> findByPostulacion(Integer idPostulacion);
    List<HistorialEstadosPostulacioneDto> findByEstado(Integer idEstado);
    List<HistorialEstadosPostulacioneDto> findByPostulacionOrdenado(Integer postulacionId);
    Page<HistorialEstadosPostulacioneDto> findByFechaRange(Instant fechaInicio, Instant fechaFin, Pageable pageable);
    long countByPostulacion(Integer postulacionId);
    List<HistorialEstadosPostulacioneDto> searchByDetalle(String detalle);
    HistorialEstadosPostulacioneDto crearHistorial(Integer postulacionId, Integer estadoId, String detalle);
}
