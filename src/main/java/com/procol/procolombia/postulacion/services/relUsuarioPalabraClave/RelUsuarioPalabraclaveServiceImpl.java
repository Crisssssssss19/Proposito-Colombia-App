package com.procol.procolombia.postulacion.services.relUsuarioPalabraClave;

import com.procol.procolombia.abtract.AbstractService;
import com.procol.procolombia.postulacion.dto.RelUsuarioPalabraclaveDto;
import com.procol.procolombia.postulacion.entities.RelUsuarioPalabraclave;
import com.procol.procolombia.postulacion.entities.RelUsuarioPalabraclaveId;
import com.procol.procolombia.postulacion.mappers.RelUsuarioPalabraclaveMapper;
import com.procol.procolombia.postulacion.repositories.RelUsuarioPalabraclaveRepository;
import com.procol.procolombia.auth.entities.Usuario;
import com.procol.procolombia.auth.repositories.UsuarioRepository;
import com.procol.procolombia.vacante.entities.PalabrasClave;
import com.procol.procolombia.vacante.repositories.PalabrasClaveRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class RelUsuarioPalabraclaveServiceImpl extends AbstractService<RelUsuarioPalabraclave,RelUsuarioPalabraclaveDto,RelUsuarioPalabraclaveId> implements RelUsuarioPalabraclaveService {
    private final RelUsuarioPalabraclaveRepository relUsuarioPalabraclaveRepository;
    private final UsuarioRepository usuarioRepository;
    private final PalabrasClaveRepository palabrasClaveRepository;

    public RelUsuarioPalabraclaveServiceImpl(RelUsuarioPalabraclaveRepository relUsuarioPalabraclaveRepository,
                                             UsuarioRepository usuarioRepository,
                                             PalabrasClaveRepository palabrasClaveRepository) {
        this.relUsuarioPalabraclaveRepository = relUsuarioPalabraclaveRepository;
        this.usuarioRepository = usuarioRepository;
        this.palabrasClaveRepository = palabrasClaveRepository;
    }

    @Override
    protected JpaRepository<RelUsuarioPalabraclave, RelUsuarioPalabraclaveId> getEntityRepository() {
        return relUsuarioPalabraclaveRepository;
    }

    @Override
    protected RelUsuarioPalabraclaveDto mapToDto(RelUsuarioPalabraclave entity) {
        return RelUsuarioPalabraclaveMapper.toDto(entity);
    }

    @Override
    protected RelUsuarioPalabraclave mapToEntity(RelUsuarioPalabraclaveDto dto) {
        RelUsuarioPalabraclave relacion = RelUsuarioPalabraclaveMapper.toEntity(dto);

        RelUsuarioPalabraclaveId id = new RelUsuarioPalabraclaveId();
        id.setIdUsuario(dto.getIdUsuario());
        id.setIdPalabraClave(dto.getIdPalabraClave());
        relacion.setId(id);

        if (dto.getIdUsuario() != null) {
            Usuario usuario = usuarioRepository.findById(dto.getIdUsuario())
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + dto.getIdUsuario()));
            relacion.setIdUsuario(usuario);
        }

        if (dto.getIdPalabraClave() != null) {
            PalabrasClave palabraClave = palabrasClaveRepository.findById(dto.getIdPalabraClave())
                    .orElseThrow(() -> new RuntimeException("Palabra clave no encontrada con id: " + dto.getIdPalabraClave()));
            relacion.setIdPalabraClave(palabraClave);
        }

        return relacion;
    }

    @Override
    protected List<RelUsuarioPalabraclaveDto> mapToDtoList(List<RelUsuarioPalabraclave> entities) {
        return RelUsuarioPalabraclaveMapper.toDtoList(entities);
    }

    @Override
    protected void updateEntityFromDto(RelUsuarioPalabraclave entity, RelUsuarioPalabraclaveDto dto) {

        if (dto.getIdUsuario() != null) {
            Usuario usuario = usuarioRepository.findById(dto.getIdUsuario())
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + dto.getIdUsuario()));
            entity.setIdUsuario(usuario);
        }

        if (dto.getIdPalabraClave() != null) {
            PalabrasClave palabraClave = palabrasClaveRepository.findById(dto.getIdPalabraClave())
                    .orElseThrow(() -> new RuntimeException("Palabra clave no encontrada con id: " + dto.getIdPalabraClave()));
            entity.setIdPalabraClave(palabraClave);
        }
    }

    @Override
    public void deleteByUsuarioAndPalabraClave(Integer idUsuario, Integer idPalabraClave) {
        relUsuarioPalabraclaveRepository.deleteByIdUsuario_IdAndIdPalabraClave_Id(idUsuario, idPalabraClave);
    }

    @Override
    public List<RelUsuarioPalabraclaveDto> findByUsuario(Integer idUsuario) {
        List<RelUsuarioPalabraclave> relaciones = relUsuarioPalabraclaveRepository.findByIdUsuario_Id(idUsuario);
        return mapToDtoList(relaciones);
    }

    @Override
    public List<RelUsuarioPalabraclaveDto> findByPalabraClave(Integer idPalabraClave) {
        List<RelUsuarioPalabraclave> relaciones = relUsuarioPalabraclaveRepository.findByIdPalabraClave_Id(idPalabraClave);
        return mapToDtoList(relaciones);
    }

    @Override
    public boolean existsByUsuarioAndPalabraClave(Integer idUsuario, Integer idPalabraClave) {
        return relUsuarioPalabraclaveRepository.existsByIdUsuario_IdAndIdPalabraClave_Id(idUsuario, idPalabraClave);
    }

    @Override
    public long countByUsuario(Integer usuarioId) {
        return relUsuarioPalabraclaveRepository.countByUsuario(usuarioId);
    }

    @Override
    public long countByPalabra(Integer palabraId) {
        return relUsuarioPalabraclaveRepository.countByPalabra(palabraId);
    }

    @Override
    public RelUsuarioPalabraclaveDto asociarUsuarioConPalabraClave(Integer usuarioId, Integer palabraClaveId) {
        if (existsByUsuarioAndPalabraClave(usuarioId, palabraClaveId)) {
            throw new RuntimeException("Ya existe la asociación entre el usuario y la palabra clave");
        }

        RelUsuarioPalabraclaveDto relacionDto = new RelUsuarioPalabraclaveDto();
        relacionDto.setIdUsuario(usuarioId);
        relacionDto.setIdPalabraClave(palabraClaveId);

        return save(relacionDto);
    }

    @Override
    public void desasociarUsuarioDePalabraClave(Integer usuarioId, Integer palabraClaveId) {
        if (!existsByUsuarioAndPalabraClave(usuarioId, palabraClaveId)) {
            throw new RuntimeException("No existe la asociación entre el usuario y la palabra clave");
        }
        deleteByUsuarioAndPalabraClave(usuarioId, palabraClaveId);
    }

    @Override
    public void asociarMultiplesPalabrasClaveAUsuario(Integer usuarioId, List<Integer> palabrasClaveIds) {
        for (Integer palabraClaveId : palabrasClaveIds) {
            if (!existsByUsuarioAndPalabraClave(usuarioId, palabraClaveId)) {
                asociarUsuarioConPalabraClave(usuarioId, palabraClaveId);
            }
        }
    }

    @Override
    public void desasociarTodasLasPalabrasDeUsuario(Integer usuarioId) {
        List<RelUsuarioPalabraclaveDto> relaciones = findByUsuario(usuarioId);
        for (RelUsuarioPalabraclaveDto relacion : relaciones) {
            deleteByUsuarioAndPalabraClave(relacion.getIdUsuario(), relacion.getIdPalabraClave());
        }
    }
}
