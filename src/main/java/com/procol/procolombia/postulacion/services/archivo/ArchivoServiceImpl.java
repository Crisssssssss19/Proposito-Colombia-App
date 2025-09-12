package com.procol.procolombia.postulacion.services.archivo;

import com.procol.procolombia.auth.entities.Usuario;
import com.procol.procolombia.auth.repositories.UsuarioRepository;
import com.procol.procolombia.postulacion.dto.ArchivoDto;
import com.procol.procolombia.postulacion.entities.Archivo;
import com.procol.procolombia.postulacion.mappers.ArchivoMapper;
import com.procol.procolombia.postulacion.repositories.ArchivoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ArchivoServiceImpl implements ArchivoService{

    private final ArchivoRepository archivoRepository;
    private final UsuarioRepository usuarioRepository;

    public ArchivoServiceImpl(ArchivoRepository archivoRepository, UsuarioRepository usuarioRepository) {
        this.archivoRepository = archivoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public List<ArchivoDto> findAll() {
        List<Archivo> archivos = archivoRepository.findAll();
        return ArchivoMapper.toDtoList(archivos);
    }

    @Override
    public Optional<ArchivoDto> findById(Integer id) {
        Optional<Archivo> archivo = archivoRepository.findById(id);
        return archivo.map(ArchivoMapper::toDto);
    }

    @Override
    public ArchivoDto save(ArchivoDto archivoDto) {
        Archivo archivo = ArchivoMapper.toEntity(archivoDto);

        if (archivoDto.getIdUsuario() != null) {
            Usuario usuario = usuarioRepository.findById(archivoDto.getIdUsuario())
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + archivoDto.getIdUsuario()));
            archivo.setIdUsuario(usuario);
        }

        Archivo savedArchivo = archivoRepository.save(archivo);
        return ArchivoMapper.toDto(savedArchivo);
    }

    @Override
    public ArchivoDto update(Integer id, ArchivoDto archivoDto) {
        return archivoRepository.findById(id)
                .map(existingArchivo -> {
                    existingArchivo.setNombrePublicoArchivo(archivoDto.getNombrePublicoArchivo());
                    existingArchivo.setNombreArchivoArchivo(archivoDto.getNombreArchivoArchivo());
                    existingArchivo.setTipoArchivo(archivoDto.getTipoArchivo());
                    existingArchivo.setTamanioArchivo(archivoDto.getTamanioArchivo());
                    existingArchivo.setGrupoArchivo(archivoDto.getGrupoArchivo());

                    if (archivoDto.getIdUsuario() != null) {
                        Usuario usuario = usuarioRepository.findById(archivoDto.getIdUsuario())
                                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + archivoDto.getIdUsuario()));
                        existingArchivo.setIdUsuario(usuario);
                    }

                    return ArchivoMapper.toDto(archivoRepository.save(existingArchivo));
                })
                .orElseThrow(() -> new RuntimeException("Archivo no encontrado con id: " + id));
    }

    @Override
    public void deleteById(Integer id) {
        archivoRepository.deleteById(id);
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
