package com.procol.procolombia.postulacion.services.EstadosPostulaciones;

import com.procol.procolombia.postulacion.dto.EstadosPostulacioneDto;

import java.util.List;
import java.util.Optional;

public interface EstadosPostulacionesServices {
    public List<EstadosPostulacioneDto> findAll();
    public Optional<EstadosPostulacioneDto> findById(Integer id) ;
    public EstadosPostulacioneDto save(EstadosPostulacioneDto estadosPostulacioneDto);
    public EstadosPostulacioneDto update(Integer id, EstadosPostulacioneDto estadosPostulacioneDto);
    public void deleteById(Integer id);
    public Optional<EstadosPostulacioneDto> findByNombre(String nombreEstado);
    public List<EstadosPostulacioneDto> findAllOrderByOrden();
    public Optional<EstadosPostulacioneDto> findByOrden(Short orden);
    public long countHistorialByEstado(Integer estadoId);
    public List<EstadosPostulacioneDto> searchByNombre(String nombre);
    public boolean existsByNombre(String nombreEstado);

}
