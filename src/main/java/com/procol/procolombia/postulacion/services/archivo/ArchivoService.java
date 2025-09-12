package com.procol.procolombia.postulacion.services.archivo;

import com.procol.procolombia.postulacion.dto.ArchivoDto;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ArchivoService {
    public List<ArchivoDto> findAll();
    public Optional<ArchivoDto> findById(Integer id);
    public ArchivoDto save(ArchivoDto archivoDto);
    public ArchivoDto update(Integer id, ArchivoDto archivoDto);
    public void deleteById(Integer id);
    public List<ArchivoDto> findByUsuario(Integer idUsuario);
    public List<ArchivoDto> findByGrupoArchivo(Short grupoArchivo);
    public List<ArchivoDto> findByTipoArchivo(String tipoArchivo);
    public List<ArchivoDto> findByUsuarioAndGrupo(Integer usuarioId, Short grupo);
    public long countByUsuario(Integer usuarioId);
    public List<ArchivoDto> searchByNombre(String nombre);
}
