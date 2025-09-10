package com.procol.procolombia.postulacion.services.relUsuarioPalabraClave;

import com.procol.procolombia.postulacion.dto.RelUsuarioPalabraclaveDto;
import com.procol.procolombia.postulacion.entities.RelUsuarioPalabraclave;
import com.procol.procolombia.postulacion.entities.RelUsuarioPalabraclaveId;
import com.procol.procolombia.postulacion.mappers.RelUsuarioPalabraclaveMapper;
import com.procol.procolombia.postulacion.repositories.RelUsuarioPalabraclaveRepository;
import com.procol.procolombia.auth.entities.Usuario;
import com.procol.procolombia.auth.repositories.UsuarioRepository;
import com.procol.procolombia.vacante.entities.PalabrasClave;
import com.procol.procolombia.vacante.repositories.PalabrasClaveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RelUsuarioPalabraclaveServiceImpl {

    private final RelUsuarioPalabraclaveRepository relUsuarioPalabraclaveRepository;

    private final UsuarioRepository usuarioRepository;

    private final PalabrasClaveRepository palabrasClaveRepository;

    public RelUsuarioPalabraclaveServiceImpl(RelUsuarioPalabraclaveRepository relUsuarioPalabraclaveRepository, UsuarioRepository usuarioRepository, PalabrasClaveRepository palabrasClaveRepository) {
        this.relUsuarioPalabraclaveRepository = relUsuarioPalabraclaveRepository;
        this.usuarioRepository = usuarioRepository;
        this.palabrasClaveRepository = palabrasClaveRepository;
    }

    public List<RelUsuarioPalabraclaveDto> findAll() {
        List<RelUsuarioPalabraclave> relaciones = relUsuarioPalabraclaveRepository.findAll();
        return RelUsuarioPalabraclaveMapper.toDtoList(relaciones);
    }

    public Optional<RelUsuarioPalabraclaveDto> findById(RelUsuarioPalabraclaveId id) {
        Optional<RelUsuarioPalabraclave> relacion = relUsuarioPalabraclaveRepository.findById(id);
        return relacion.map(RelUsuarioPalabraclaveMapper::toDto);
    }

    public RelUsuarioPalabraclaveDto save(RelUsuarioPalabraclaveDto relUsuarioPalabraclaveDto) {
        RelUsuarioPalabraclave relacion = RelUsuarioPalabraclaveMapper.toEntity(relUsuarioPalabraclaveDto);

        RelUsuarioPalabraclaveId id = new RelUsuarioPalabraclaveId();
        id.setIdUsuario(relUsuarioPalabraclaveDto.getIdUsuario());
        id.setIdPalabraClave(relUsuarioPalabraclaveDto.getIdPalabraClave());
        relacion.setId(id);

        if (relUsuarioPalabraclaveDto.getIdUsuario() != null) {
            Usuario usuario = usuarioRepository.findById(relUsuarioPalabraclaveDto.getIdUsuario())
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + relUsuarioPalabraclaveDto.getIdUsuario()));
            relacion.setIdUsuario(usuario);
        }

        if (relUsuarioPalabraclaveDto.getIdPalabraClave() != null) {
            PalabrasClave palabraClave = palabrasClaveRepository.findById(relUsuarioPalabraclaveDto.getIdPalabraClave())
                    .orElseThrow(() -> new RuntimeException("Palabra clave no encontrada con id: " + relUsuarioPalabraclaveDto.getIdPalabraClave()));
            relacion.setIdPalabraClave(palabraClave);
        }

        RelUsuarioPalabraclave savedRelacion = relUsuarioPalabraclaveRepository.save(relacion);
        return RelUsuarioPalabraclaveMapper.toDto(savedRelacion);
    }

    public void deleteById(RelUsuarioPalabraclaveId id) {
        relUsuarioPalabraclaveRepository.deleteById(id);
    }

    public void deleteByUsuarioAndPalabraClave(Integer idUsuario, Integer idPalabraClave) {
        relUsuarioPalabraclaveRepository.deleteByIdUsuario_IdAndIdPalabraClave_Id(idUsuario, idPalabraClave);
    }

    public List<RelUsuarioPalabraclaveDto> findByUsuario(Integer idUsuario) {
        List<RelUsuarioPalabraclave> relaciones = relUsuarioPalabraclaveRepository.findByIdUsuario_Id(idUsuario);
        return RelUsuarioPalabraclaveMapper.toDtoList(relaciones);
    }

    public List<RelUsuarioPalabraclaveDto> findByPalabraClave(Integer idPalabraClave) {
        List<RelUsuarioPalabraclave> relaciones = relUsuarioPalabraclaveRepository.findByIdPalabraClave_Id(idPalabraClave);
        return RelUsuarioPalabraclaveMapper.toDtoList(relaciones);
    }

    public boolean existsByUsuarioAndPalabraClave(Integer idUsuario, Integer idPalabraClave) {
        return relUsuarioPalabraclaveRepository.existsByIdUsuario_IdAndIdPalabraClave_Id(idUsuario, idPalabraClave);
    }

    public long countByUsuario(Integer usuarioId) {
        return relUsuarioPalabraclaveRepository.countByUsuario(usuarioId);
    }

    public long countByPalabra(Integer palabraId) {
        return relUsuarioPalabraclaveRepository.countByPalabra(palabraId);
    }

    public RelUsuarioPalabraclaveDto asociarUsuarioConPalabraClave(Integer usuarioId, Integer palabraClaveId) {
        if (existsByUsuarioAndPalabraClave(usuarioId, palabraClaveId)) {
            throw new RuntimeException("Ya existe la asociación entre el usuario y la palabra clave");
        }

        RelUsuarioPalabraclaveDto relacionDto = new RelUsuarioPalabraclaveDto();
        relacionDto.setIdUsuario(usuarioId);
        relacionDto.setIdPalabraClave(palabraClaveId);

        return save(relacionDto);
    }

    public void desasociarUsuarioDePalabraClave(Integer usuarioId, Integer palabraClaveId) {
        if (!existsByUsuarioAndPalabraClave(usuarioId, palabraClaveId)) {
            throw new RuntimeException("No existe la asociación entre el usuario y la palabra clave");
        }
        deleteByUsuarioAndPalabraClave(usuarioId, palabraClaveId);
    }

    public void asociarMultiplesPalabrasClaveAUsuario(Integer usuarioId, List<Integer> palabrasClaveIds) {
        for (Integer palabraClaveId : palabrasClaveIds) {
            if (!existsByUsuarioAndPalabraClave(usuarioId, palabraClaveId)) {
                asociarUsuarioConPalabraClave(usuarioId, palabraClaveId);
            }
        }
    }

    public void desasociarTodasLasPalabrasDeUsuario(Integer usuarioId) {
        List<RelUsuarioPalabraclaveDto> relaciones = findByUsuario(usuarioId);
        for (RelUsuarioPalabraclaveDto relacion : relaciones) {
            deleteByUsuarioAndPalabraClave(relacion.getIdUsuario(), relacion.getIdPalabraClave());
        }
    }
}
