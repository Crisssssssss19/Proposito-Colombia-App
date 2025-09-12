package com.procol.procolombia.postulacion.services.mensaje;

import com.procol.procolombia.postulacion.dto.MensajeDto;
import com.procol.procolombia.postulacion.entities.Mensaje;
import com.procol.procolombia.postulacion.entities.Postulacione;
import com.procol.procolombia.postulacion.mappers.MensajeMapper;
import com.procol.procolombia.postulacion.repositories.MensajeRepository;
import com.procol.procolombia.postulacion.repositories.PostulacioneRepository;
import com.procol.procolombia.auth.entities.Usuario;
import com.procol.procolombia.auth.repositories.UsuarioRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MensajeServiceImpl implements MensajeService {

    private MensajeRepository mensajeRepository;

    private PostulacioneRepository postulacioneRepository;

    private UsuarioRepository usuarioRepository;

    public MensajeServiceImpl(MensajeRepository mensajeRepository, PostulacioneRepository postulacioneRepository, UsuarioRepository usuarioRepository) {
        this.mensajeRepository = mensajeRepository;
        this.postulacioneRepository = postulacioneRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public List<MensajeDto> findAll() {
        List<Mensaje> mensajes = mensajeRepository.findAll();
        return MensajeMapper.toDtoList(mensajes);
    }

    @Override
    public Optional<MensajeDto> findById(Integer id) {
        Optional<Mensaje> mensaje = mensajeRepository.findById(id);
        return mensaje.map(MensajeMapper::toDto);
    }

    @Override
    public MensajeDto save(MensajeDto mensajeDto) {
        Mensaje mensaje = MensajeMapper.toEntity(mensajeDto);

        if (mensajeDto.getIdPostulacion() != null) {
            Postulacione postulacion = postulacioneRepository.findById(mensajeDto.getIdPostulacion())
                    .orElseThrow(() -> new RuntimeException("Postulación no encontrada con id: " + mensajeDto.getIdPostulacion()));
            mensaje.setIdPostulacion(postulacion);
        }

        if (mensajeDto.getIdUsuarioResponde() != null) {
            Usuario usuario = usuarioRepository.findById(mensajeDto.getIdUsuarioResponde())
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + mensajeDto.getIdUsuarioResponde()));
            mensaje.setIdUsuarioResponde(usuario);
        }

        Mensaje savedMensaje = mensajeRepository.save(mensaje);
        return MensajeMapper.toDto(savedMensaje);
    }

    @Override
    public MensajeDto update(Integer id, MensajeDto mensajeDto) {
        return mensajeRepository.findById(id)
                .map(existingMensaje -> {
                    existingMensaje.setTextoMensaje(mensajeDto.getTextoMensaje());
                    existingMensaje.setFechaMensaje(mensajeDto.getFechaMensaje());
                    existingMensaje.setEstadoMensaje(mensajeDto.getEstadoMensaje());

                    if (mensajeDto.getIdPostulacion() != null) {
                        Postulacione postulacion = postulacioneRepository.findById(mensajeDto.getIdPostulacion())
                                .orElseThrow(() -> new RuntimeException("Postulación no encontrada con id: " + mensajeDto.getIdPostulacion()));
                        existingMensaje.setIdPostulacion(postulacion);
                    }

                    if (mensajeDto.getIdUsuarioResponde() != null) {
                        Usuario usuario = usuarioRepository.findById(mensajeDto.getIdUsuarioResponde())
                                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + mensajeDto.getIdUsuarioResponde()));
                        existingMensaje.setIdUsuarioResponde(usuario);
                    }

                    return MensajeMapper.toDto(mensajeRepository.save(existingMensaje));
                })
                .orElseThrow(() -> new RuntimeException("Mensaje no encontrado con id: " + id));
    }

    @Override
    public void deleteById(Integer id) {
        mensajeRepository.deleteById(id);
    }

    @Override
    public List<MensajeDto> findByPostulacion(Integer idPostulacion) {
        List<Mensaje> mensajes = mensajeRepository.findByIdPostulacion_Id(idPostulacion);
        return MensajeMapper.toDtoList(mensajes);
    }

    @Override
    public List<MensajeDto> findByUsuario(Integer idUsuarioResponde) {
        List<Mensaje> mensajes = mensajeRepository.findByIdUsuarioResponde_Id(idUsuarioResponde);
        return MensajeMapper.toDtoList(mensajes);
    }

    @Override
    public List<MensajeDto> findByEstado(Short estadoMensaje) {
        List<Mensaje> mensajes = mensajeRepository.findByEstadoMensaje(estadoMensaje);
        return MensajeMapper.toDtoList(mensajes);
    }

    @Override
    public Page<MensajeDto> findByPostulacionPaginated(Integer idPostulacion, Pageable pageable) {
        Page<Mensaje> mensajes = mensajeRepository.findByIdPostulacion_Id(idPostulacion, pageable);
        return mensajes.map(MensajeMapper::toDto);
    }

    @Override
    public List<MensajeDto> findMensajesVisiblesByPostulacion(Integer postulacionId) {
        List<Mensaje> mensajes = mensajeRepository.findMensajesVisiblesByPostulacion(postulacionId);
        return MensajeMapper.toDtoList(mensajes);
    }

    @Override
    public long countByPostulacion(Integer postulacionId) {
        return mensajeRepository.countByPostulacion(postulacionId);
    }

    @Override
    public List<MensajeDto> findByFechaRange(Instant fechaInicio, Instant fechaFin) {
        List<Mensaje> mensajes = mensajeRepository.findByFechaRange(fechaInicio, fechaFin);
        return MensajeMapper.toDtoList(mensajes);
    }

    @Override
    public MensajeDto crearMensaje(Integer postulacionId, Integer usuarioId, String texto) {
        MensajeDto mensajeDto = new MensajeDto();
        mensajeDto.setIdPostulacion(postulacionId);
        mensajeDto.setIdUsuarioResponde(usuarioId);
        mensajeDto.setTextoMensaje(texto);
        mensajeDto.setFechaMensaje(Instant.now());
        mensajeDto.setEstadoMensaje((short) 1); // Estado activo
        return save(mensajeDto);
    }
}
