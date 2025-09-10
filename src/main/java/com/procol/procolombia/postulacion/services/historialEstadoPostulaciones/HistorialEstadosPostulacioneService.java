package com.procol.procolombia.postulacion.services.historialEstadoPostulaciones;

import com.procol.procolombia.postulacion.dto.HistorialEstadosPostulacioneDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

public interface HistorialEstadosPostulacioneService {
    public List<HistorialEstadosPostulacioneDto> findAll();
    public Optional<HistorialEstadosPostulacioneDto> findById(Integer id);
    public HistorialEstadosPostulacioneDto save(HistorialEstadosPostulacioneDto historialDto);
    public HistorialEstadosPostulacioneDto update(Integer id, HistorialEstadosPostulacioneDto historialDto);
    public void deleteById(Integer id);
    public List<HistorialEstadosPostulacioneDto> findByPostulacion(Integer idPostulacion);
    public List<HistorialEstadosPostulacioneDto> findByEstado(Integer idEstado);
    public List<HistorialEstadosPostulacioneDto> findByPostulacionOrdenado(Integer postulacionId);
    public Page<HistorialEstadosPostulacioneDto> findByFechaRange(Instant fechaInicio, Instant fechaFin, Pageable pageable);
    public long countByPostulacion(Integer postulacionId);
    public List<HistorialEstadosPostulacioneDto> searchByDetalle(String detalle);
    public HistorialEstadosPostulacioneDto crearHistorial(Integer postulacionId, Integer estadoId, String detalle);
}
