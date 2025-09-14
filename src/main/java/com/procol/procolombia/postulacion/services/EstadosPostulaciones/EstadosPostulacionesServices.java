package com.procol.procolombia.postulacion.services.EstadosPostulaciones;

import com.procol.procolombia.postulacion.dto.EstadosPostulacioneDto;

import java.util.List;
import java.util.Optional;

public interface EstadosPostulacionesServices {
    Optional<EstadosPostulacioneDto> findByNombre(String nombreEstado);
    List<EstadosPostulacioneDto> findAllOrderByOrden();
    Optional<EstadosPostulacioneDto> findByOrden(Short orden);
    long countHistorialByEstado(Integer estadoId);
    List<EstadosPostulacioneDto> searchByNombre(String nombre);
    boolean existsByNombre(String nombreEstado);

}
