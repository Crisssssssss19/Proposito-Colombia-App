package com.procol.procolombia.postulacion.services.relUsuarioPalabraClave;

import com.procol.procolombia.postulacion.dto.RelUsuarioPalabraclaveDto;
import com.procol.procolombia.postulacion.entities.RelUsuarioPalabraclaveId;

import java.util.List;
import java.util.Optional;

public interface RelUsuarioPalabraclaveService {
    List<RelUsuarioPalabraclaveDto> findAll();
    Optional<RelUsuarioPalabraclaveDto> findById(RelUsuarioPalabraclaveId id);
    RelUsuarioPalabraclaveDto save(RelUsuarioPalabraclaveDto relUsuarioPalabraclaveDto);
    void deleteById(RelUsuarioPalabraclaveId id);
    void deleteByUsuarioAndPalabraClave(Integer idUsuario, Integer idPalabraClave);
    List<RelUsuarioPalabraclaveDto> findByUsuario(Integer idUsuario);
    List<RelUsuarioPalabraclaveDto> findByPalabraClave(Integer idPalabraClave);
    boolean existsByUsuarioAndPalabraClave(Integer idUsuario, Integer idPalabraClave);
    long countByUsuario(Integer usuarioId);
    long countByPalabra(Integer palabraId);
    RelUsuarioPalabraclaveDto asociarUsuarioConPalabraClave(Integer usuarioId, Integer palabraClaveId);
    void desasociarUsuarioDePalabraClave(Integer usuarioId, Integer palabraClaveId);
    void asociarMultiplesPalabrasClaveAUsuario(Integer usuarioId, List<Integer> palabrasClaveIds);
    void desasociarTodasLasPalabrasDeUsuario(Integer usuarioId);
}
