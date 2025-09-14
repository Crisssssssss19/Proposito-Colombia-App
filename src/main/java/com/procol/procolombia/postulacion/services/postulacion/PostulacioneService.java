package com.procol.procolombia.postulacion.services.postulacion;

import com.procol.procolombia.postulacion.dto.PostulacioneDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface PostulacioneService {
    List<PostulacioneDto> findAll();
    Optional<PostulacioneDto> findById(Integer id);
    PostulacioneDto save(PostulacioneDto postulacioneDto);
    PostulacioneDto update(Integer id, PostulacioneDto postulacioneDto);
    void deleteById(Integer id);
    List<PostulacioneDto> findByUsuario(Integer idUsuario);
    List<PostulacioneDto> findByVacante(Integer idVacante);
    List<PostulacioneDto> findByEstado(Short estadoPostulacion);
    Page<PostulacioneDto> findByUsuarioPaginated(Integer idUsuario, Pageable pageable);
    Page<PostulacioneDto> findByVacantePaginated(Integer idVacante, Pageable pageable);
    Optional<PostulacioneDto> findByUsuarioAndVacante(Integer idUsuario, Integer idVacante);
    boolean existsByUsuarioAndVacante(Integer idUsuario, Integer idVacante);
    List<PostulacioneDto> findByCorrespondencia(Short correspondencia);
    void updateCorrespondencia(Integer id, Short correspondencia);
    void updateEstado(Integer id, Short estado);
    long countByVacante(Integer vacanteId);
    long countByUsuario(Integer usuarioId);
    Page<PostulacioneDto> findByEmpresa(Integer empresaId, Pageable pageable);
    PostulacioneDto crearPostulacion(Integer usuarioId, Integer vacanteId);
}
