package com.procol.procolombia.postulacion.services.mensaje;

import com.procol.procolombia.postulacion.dto.MensajeDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

public interface MensajeService {
    List<MensajeDto> findAll();
    Optional<MensajeDto> findById(Integer id);
    MensajeDto save(MensajeDto mensajeDto);
    void deleteById(Integer id);
    MensajeDto update(Integer id, MensajeDto mensajeDto);
    List<MensajeDto> findByPostulacion(Integer idPostulacion);
    List<MensajeDto> findByUsuario(Integer idUsuarioResponde);
    List<MensajeDto> findByEstado(Short estadoMensaje);
    Page<MensajeDto> findByPostulacionPaginated(Integer idPostulacion, Pageable pageable);
    List<MensajeDto> findMensajesVisiblesByPostulacion(Integer postulacionId);
    long countByPostulacion(Integer postulacionId);
    List<MensajeDto> findByFechaRange(Instant fechaInicio, Instant fechaFin);
    MensajeDto crearMensaje(Integer postulacionId, Integer usuarioId, String texto);
}
