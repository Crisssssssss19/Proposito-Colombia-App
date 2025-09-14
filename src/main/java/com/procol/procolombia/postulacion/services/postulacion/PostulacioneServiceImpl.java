package com.procol.procolombia.postulacion.services.postulacion;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import com.procol.procolombia.abtract.AbstractService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.procol.procolombia.auth.entities.Usuario;
import com.procol.procolombia.auth.repositories.UsuarioRepository;
import com.procol.procolombia.postulacion.dto.PostulacioneDto;
import com.procol.procolombia.postulacion.entities.Postulacione;
import com.procol.procolombia.postulacion.mappers.PostulacioneMapper;
import com.procol.procolombia.postulacion.repositories.PostulacioneRepository;
import com.procol.procolombia.vacante.entities.Vacante;
import com.procol.procolombia.vacante.repositories.VacanteRepository;

@Service
@Transactional
public class PostulacioneServiceImpl extends AbstractService<Postulacione, PostulacioneDto, Integer> implements PostulacioneService {

    private final PostulacioneRepository postulacioneRepository;
    private final UsuarioRepository usuarioRepository;
    private final VacanteRepository vacanteRepository;

    public PostulacioneServiceImpl(PostulacioneRepository postulacioneRepository,
                                   UsuarioRepository usuarioRepository,
                                   VacanteRepository vacanteRepository) {
        this.postulacioneRepository = postulacioneRepository;
        this.usuarioRepository = usuarioRepository;
        this.vacanteRepository = vacanteRepository;
    }

    @Override
    protected JpaRepository<Postulacione, Integer> getEntityRepository() {
        return postulacioneRepository;
    }

    @Override
    protected PostulacioneDto mapToDto(Postulacione entity) {
        return PostulacioneMapper.toDto(entity);
    }

    @Override
    protected Postulacione mapToEntity(PostulacioneDto dto) {
        if (dto.getIdUsuario() != null && dto.getIdVacante() != null) {
            boolean exists = postulacioneRepository.existsByIdUsuario_IdAndIdVacante_Id(
                    dto.getIdUsuario(),
                    dto.getIdVacante()
            );
            if (exists) {
                throw new RuntimeException("El usuario ya está postulado a esta vacante.");
            }
        }

        Postulacione postulacion = PostulacioneMapper.toEntity(dto);

        if (dto.getIdUsuario() != null) {
            Usuario usuario = usuarioRepository.findById(dto.getIdUsuario())
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + dto.getIdUsuario()));
            postulacion.setIdUsuario(usuario);
        }

        if (dto.getIdVacante() != null) {
            Vacante vacante = vacanteRepository.findById(dto.getIdVacante())
                    .orElseThrow(() -> new RuntimeException("Vacante no encontrada con id: " + dto.getIdVacante()));
            postulacion.setIdVacante(vacante);
        }

        return postulacion;
    }

    @Override
    protected List<PostulacioneDto> mapToDtoList(List<Postulacione> entities) {
        return PostulacioneMapper.toDtoList(entities);
    }

    @Override
    protected void updateEntityFromDto(Postulacione entity, PostulacioneDto dto) {
        entity.setFechaPostulacion(dto.getFechaPostulacion());
        entity.setCorrespondenciaPostulacion(dto.getCorrespondenciaPostulacion());
        entity.setEstadoPostulacion(dto.getEstadoPostulacion());

        if (dto.getIdUsuario() != null) {
            Usuario usuario = usuarioRepository.findById(dto.getIdUsuario())
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + dto.getIdUsuario()));
            entity.setIdUsuario(usuario);
        }

        if (dto.getIdVacante() != null) {
            Vacante vacante = vacanteRepository.findById(dto.getIdVacante())
                    .orElseThrow(() -> new RuntimeException("Vacante no encontrada con id: " + dto.getIdVacante()));
            entity.setIdVacante(vacante);
        }
    }

    // Métodos específicos del servicio
    @Override
    public List<PostulacioneDto> findByUsuario(Integer idUsuario) {
        List<Postulacione> postulaciones = postulacioneRepository.findByIdUsuario_Id(idUsuario);
        return mapToDtoList(postulaciones);
    }

    @Override
    public List<PostulacioneDto> findByVacante(Integer idVacante) {
        List<Postulacione> postulaciones = postulacioneRepository.findByIdVacante_Id(idVacante);
        return mapToDtoList(postulaciones);
    }

    @Override
    public List<PostulacioneDto> findByEstado(Short estadoPostulacion) {
        List<Postulacione> postulaciones = postulacioneRepository.findByEstadoPostulacion(estadoPostulacion);
        return mapToDtoList(postulaciones);
    }

    @Override
    public Page<PostulacioneDto> findByUsuarioPaginated(Integer idUsuario, Pageable pageable) {
        Page<Postulacione> postulaciones = postulacioneRepository.findByIdUsuario_Id(idUsuario, pageable);
        return postulaciones.map(this::mapToDto);
    }

    @Override
    public Page<PostulacioneDto> findByVacantePaginated(Integer idVacante, Pageable pageable) {
        Page<Postulacione> postulaciones = postulacioneRepository.findByIdVacante_Id(idVacante, pageable);
        return postulaciones.map(this::mapToDto);
    }

    @Override
    public Optional<PostulacioneDto> findByUsuarioAndVacante(Integer idUsuario, Integer idVacante) {
        Optional<Postulacione> postulacion = postulacioneRepository.findByIdUsuario_IdAndIdVacante_Id(idUsuario, idVacante);
        return postulacion.map(this::mapToDto);
    }

    @Override
    public boolean existsByUsuarioAndVacante(Integer idUsuario, Integer idVacante) {
        return postulacioneRepository.existsByIdUsuario_IdAndIdVacante_Id(idUsuario, idVacante);
    }

    @Override
    public List<PostulacioneDto> findByCorrespondencia(Short correspondencia) {
        List<Postulacione> postulaciones = postulacioneRepository.findByCorrespondencia(correspondencia);
        return mapToDtoList(postulaciones);
    }

    @Override
    public void updateCorrespondencia(Integer id, Short correspondencia) {
        postulacioneRepository.updateCorrespondencia(id, correspondencia);
    }

    @Override
    public void updateEstado(Integer id, Short estado) {
        postulacioneRepository.updateEstado(id, estado);
    }

    @Override
    public long countByVacante(Integer vacanteId) {
        return postulacioneRepository.countByVacante(vacanteId);
    }

    @Override
    public long countByUsuario(Integer usuarioId) {
        return postulacioneRepository.countByUsuario(usuarioId);
    }

    @Override
    public Page<PostulacioneDto> findByEmpresa(Integer empresaId, Pageable pageable) {
        Page<Postulacione> postulaciones = postulacioneRepository.findByEmpresa(empresaId, pageable);
        return postulaciones.map(this::mapToDto);
    }

    @Override
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