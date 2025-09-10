package com.procol.procolombia.postulacion.services.postulacion;

import com.procol.procolombia.postulacion.dto.PostulacioneDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface PostulacioneService {
    public List<PostulacioneDto> findAll();
    public Optional<PostulacioneDto> findById(Integer id);
    public PostulacioneDto save(PostulacioneDto postulacioneDto);
    public PostulacioneDto update(Integer id, PostulacioneDto postulacioneDto);
    public void deleteById(Integer id);
    public List<PostulacioneDto> findByUsuario(Integer idUsuario);
    public List<PostulacioneDto> findByVacante(Integer idVacante);
    public List<PostulacioneDto> findByEstado(Short estadoPostulacion);
    public Page<PostulacioneDto> findByUsuarioPaginated(Integer idUsuario, Pageable pageable);
    public Page<PostulacioneDto> findByVacantePaginated(Integer idVacante, Pageable pageable);
    public Optional<PostulacioneDto> findByUsuarioAndVacante(Integer idUsuario, Integer idVacante);
    public boolean existsByUsuarioAndVacante(Integer idUsuario, Integer idVacante);
    public List<PostulacioneDto> findByCorrespondencia(Short correspondencia);
    public void updateCorrespondencia(Integer id, Short correspondencia);
    public void updateEstado(Integer id, Short estado);
    public long countByVacante(Integer vacanteId);
    public long countByUsuario(Integer usuarioId);
    public Page<PostulacioneDto> findByEmpresa(Integer empresaId, Pageable pageable);
    public PostulacioneDto crearPostulacion(Integer usuarioId, Integer vacanteId);
}
