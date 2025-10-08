package com.procol.procolombia.postulacion.services.archivo;

import com.procol.procolombia.abtract.AbstractService;
import com.procol.procolombia.auth.entities.Usuario;
import com.procol.procolombia.auth.repositories.UsuarioRepository;
import com.procol.procolombia.postulacion.dto.ArchivoDto;
import com.procol.procolombia.postulacion.entities.Archivo;
import com.procol.procolombia.postulacion.mappers.ArchivoMapper;
import com.procol.procolombia.postulacion.repositories.ArchivoRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ArchivoServiceImpl extends AbstractService<Archivo, ArchivoDto, Integer> implements ArchivoService {

    private final ArchivoRepository archivoRepository;
    private final UsuarioRepository usuarioRepository;

    public ArchivoServiceImpl(ArchivoRepository archivoRepository, UsuarioRepository usuarioRepository) {
        this.archivoRepository = archivoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    protected JpaRepository<Archivo, Integer> getEntityRepository() {
        return archivoRepository;
    }

    @Override
    protected ArchivoDto mapToDto(Archivo entity) {
        return ArchivoMapper.toDto(entity);
    }

    @Override
    protected Archivo mapToEntity(ArchivoDto dto) {
        Archivo archivo = ArchivoMapper.toEntity(dto);
        
        if (dto.getIdUsuario() != null) {
            Usuario usuario = usuarioRepository.findById(dto.getIdUsuario())
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + dto.getIdUsuario()));
            archivo.setIdUsuario(usuario);
        }
        
        return archivo;
    }

    @Override
    protected List<ArchivoDto> mapToDtoList(List<Archivo> entities) {
        return ArchivoMapper.toDtoList(entities);
    }

    @Override
    protected void updateEntityFromDto(Archivo entity, ArchivoDto dto) {
        entity.setNombrePublicoArchivo(dto.getNombrePublicoArchivo());
        entity.setNombreArchivoArchivo(dto.getNombreArchivoArchivo());
        entity.setTipoArchivo(dto.getTipoArchivo());
        entity.setTamanioArchivo(dto.getTamanioArchivo());
        entity.setGrupoArchivo(dto.getGrupoArchivo());

        if (dto.getIdUsuario() != null) {
            Usuario usuario = usuarioRepository.findById(dto.getIdUsuario())
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + dto.getIdUsuario()));
            entity.setIdUsuario(usuario);
        }
    }

    @Override
    public List<ArchivoDto> findByUsuario(Integer idUsuario) {
        List<Archivo> archivos = archivoRepository.findByIdUsuario_Id(idUsuario);
        return ArchivoMapper.toDtoList(archivos);
    }

    @Override
    public List<ArchivoDto> findByGrupoArchivo(Short grupoArchivo) {
        List<Archivo> archivos = archivoRepository.findByGrupoArchivo(grupoArchivo);
        return ArchivoMapper.toDtoList(archivos);
    }

    @Override
    public List<ArchivoDto> findByTipoArchivo(String tipoArchivo) {
        List<Archivo> archivos = archivoRepository.findByTipoArchivo(tipoArchivo);
        return ArchivoMapper.toDtoList(archivos);
    }

    @Override
    public List<ArchivoDto> findByUsuarioAndGrupo(Integer usuarioId, Short grupo) {
        List<Archivo> archivos = archivoRepository.findByUsuarioAndGrupo(usuarioId, grupo);
        return ArchivoMapper.toDtoList(archivos);
    }

    @Override
    public long countByUsuario(Integer usuarioId) {
        return archivoRepository.countByUsuario(usuarioId);
    }

    @Override
    public List<ArchivoDto> searchByNombre(String nombre) {
        List<Archivo> archivos = archivoRepository.findByNombrePublicoArchivoContainingIgnoreCase(nombre);
        return ArchivoMapper.toDtoList(archivos);
    }
}