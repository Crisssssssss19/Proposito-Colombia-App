package com.procol.procolombia.postulacion.services.postulacion;

import com.procol.procolombia.postulacion.dto.PostulacioneDto;
import com.procol.procolombia.postulacion.entities.Postulacione;
import com.procol.procolombia.postulacion.mappers.PostulacioneMapper;
import com.procol.procolombia.postulacion.repositories.PostulacioneRepository;
import com.procol.procolombia.auth.entities.Usuario;
import com.procol.procolombia.auth.repositories.UsuarioRepository;
import com.procol.procolombia.vacante.entities.Vacante;
import com.procol.procolombia.vacante.repositories.VacanteRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PostulacioneServiceImpl implements PostulacioneService {

    private final PostulacioneRepository postulacioneRepository;

    private final UsuarioRepository usuarioRepository;

    private final VacanteRepository vacanteRepository;

    public PostulacioneServiceImpl(PostulacioneRepository postulacioneRepository, UsuarioRepository usuarioRepository, VacanteRepository vacanteRepository) {
        this.postulacioneRepository = postulacioneRepository;
        this.usuarioRepository = usuarioRepository;
        this.vacanteRepository = vacanteRepository;
    }

    public List<PostulacioneDto> findAll() {
        List<Postulacione> postulaciones = postulacioneRepository.findAll();
        return PostulacioneMapper.toDtoList(postulaciones);
    }

    public Optional<PostulacioneDto> findById(Integer id) {
        Optional<Postulacione> postulacion = postulacioneRepository.findById(id);
        return postulacion.map(PostulacioneMapper::toDto);
    }

    public PostulacioneDto save(PostulacioneDto postulacioneDto) {
        Postulacione postulacion = PostulacioneMapper.toEntity(postulacioneDto);

        // Establecer las relaciones
        if (postulacioneDto.getIdUsuario() != null) {
            Usuario usuario = usuarioRepository.findById(postulacioneDto.getIdUsuario())
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + postulacioneDto.getIdUsuario()));
            postulacion.setIdUsuario(usuario);
        }

        if (postulacioneDto.getIdVacante() != null) {
            Vacante vacante = vacanteRepository.findById(postulacioneDto.getIdVacante())
                    .orElseThrow(() -> new RuntimeException("Vacante no encontrada con id: " + postulacioneDto.getIdVacante()));
            postulacion.setIdVacante(vacante);
        }

        Postulacione savedPostulacion = postulacioneRepository.save(postulacion);
        return PostulacioneMapper.toDto(savedPostulacion);
    }

    public PostulacioneDto update(Integer id, PostulacioneDto postulacioneDto) {
        return postulacioneRepository.findById(id)
                .map(existingPostulacion -> {
                    existingPostulacion.setFechaPostulacion(postulacioneDto.getFechaPostulacion());
                    existingPostulacion.setCorrespondenciaPostulacion(postulacioneDto.getCorrespondenciaPostulacion());
                    existingPostulacion.setEstadoPostulacion(postulacioneDto.getEstadoPostulacion());

                    if (postulacioneDto.getIdUsuario() != null) {
                        Usuario usuario = usuarioRepository.findById(postulacioneDto.getIdUsuario())
                                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + postulacioneDto.getIdUsuario()));
                        existingPostulacion.setIdUsuario(usuario);
                    }

                    if (postulacioneDto.getIdVacante() != null) {
                        Vacante vacante = vacanteRepository.findById(postulacioneDto.getIdVacante())
                                .orElseThrow(() -> new RuntimeException("Vacante no encontrada con id: " + postulacioneDto.getIdVacante()));
                        existingPostulacion.setIdVacante(vacante);
                    }

                    return PostulacioneMapper.toDto(postulacioneRepository.save(existingPostulacion));
                })
                .orElseThrow(() -> new RuntimeException("Postulación no encontrada con id: " + id));
    }

    public void deleteById(Integer id) {
        postulacioneRepository.deleteById(id);
    }

    public List<PostulacioneDto> findByUsuario(Integer idUsuario) {
        List<Postulacione> postulaciones = postulacioneRepository.findByIdUsuario_Id(idUsuario);
        return PostulacioneMapper.toDtoList(postulaciones);
    }

    public List<PostulacioneDto> findByVacante(Integer idVacante) {
        List<Postulacione> postulaciones = postulacioneRepository.findByIdVacante_Id(idVacante);
        return PostulacioneMapper.toDtoList(postulaciones);
    }

    public List<PostulacioneDto> findByEstado(Short estadoPostulacion) {
        List<Postulacione> postulaciones = postulacioneRepository.findByEstadoPostulacion(estadoPostulacion);
        return PostulacioneMapper.toDtoList(postulaciones);
    }

    public Page<PostulacioneDto> findByUsuarioPaginated(Integer idUsuario, Pageable pageable) {
        Page<Postulacione> postulaciones = postulacioneRepository.findByIdUsuario_Id(idUsuario, pageable);
        return postulaciones.map(PostulacioneMapper::toDto);
    }

    public Page<PostulacioneDto> findByVacantePaginated(Integer idVacante, Pageable pageable) {
        Page<Postulacione> postulaciones = postulacioneRepository.findByIdVacante_Id(idVacante, pageable);
        return postulaciones.map(PostulacioneMapper::toDto);
    }

    public Optional<PostulacioneDto> findByUsuarioAndVacante(Integer idUsuario, Integer idVacante) {
        Optional<Postulacione> postulacion = postulacioneRepository.findByIdUsuario_IdAndIdVacante_Id(idUsuario, idVacante);
        return postulacion.map(PostulacioneMapper::toDto);
    }

    public boolean existsByUsuarioAndVacante(Integer idUsuario, Integer idVacante) {
        return postulacioneRepository.existsByIdUsuario_IdAndIdVacante_Id(idUsuario, idVacante);
    }

    public List<PostulacioneDto> findByCorrespondencia(Short correspondencia) {
        List<Postulacione> postulaciones = postulacioneRepository.findByCorrespondencia(correspondencia);
        return PostulacioneMapper.toDtoList(postulaciones);
    }

    public void updateCorrespondencia(Integer id, Short correspondencia) {
        postulacioneRepository.updateCorrespondencia(id, correspondencia);
    }

    public void updateEstado(Integer id, Short estado) {
        postulacioneRepository.updateEstado(id, estado);
    }

    public long countByVacante(Integer vacanteId) {
        return postulacioneRepository.countByVacante(vacanteId);
    }

    public long countByUsuario(Integer usuarioId) {
        return postulacioneRepository.countByUsuario(usuarioId);
    }

    public Page<PostulacioneDto> findByEmpresa(Integer empresaId, Pageable pageable) {
        Page<Postulacione> postulaciones = postulacioneRepository.findByEmpresa(empresaId, pageable);
        return postulaciones.map(PostulacioneMapper::toDto);
    }

    public PostulacioneDto crearPostulacion(Integer usuarioId, Integer vacanteId) {
        if (existsByUsuarioAndVacante(usuarioId, vacanteId)) {
            throw new RuntimeException("Ya existe una postulación para este usuario y vacante");
        }

        PostulacioneDto postulacionDto = new PostulacioneDto();
        postulacionDto.setIdUsuario(usuarioId);
        postulacionDto.setIdVacante(vacanteId);
        postulacionDto.setFechaPostulacion(Instant.now());
        postulacionDto.setCorrespondenciaPostulacion((short) 4); // Valor por defecto
        postulacionDto.setEstadoPostulacion((short) 1); // Estado inicial

        return save(postulacionDto);
    }
}