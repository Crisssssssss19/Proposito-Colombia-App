package com.procol.procolombia.vacante.services;

import com.procol.procolombia.vacante.dto.InteresDto;

import java.util.List;
import java.util.Optional;

public interface InteresService {
    InteresDto createInteres(InteresDto interesDto);
    InteresDto updateInteres(InteresDto interesDto);
    Optional<InteresDto> getInteresById(Integer idUsuario, Integer idEmpresa);
    void deleteInteresById(Integer idUsuario, Integer idEmpresa);
    Short obtenerTipoInteres(Integer idEmpresa, Integer idUsuario);
    List<Integer> obtenerEmpresasSeguidasporUsuario(Integer idUsuario, Short tipoInteres);
    List<Integer> obtenerUsuariosSeguidasporEmpresa(Integer idEmpresa, Short tipoInteres);
    List<InteresDto> obtenerRelacionesMutuas();
    void usuarioSigueEmpresa(Integer idUsuario, Integer idEmpresa);
    void empresaSigueUsuario(Integer idEmpresa, Integer idUsuario);
    boolean esSeguimientoMutuo(Integer idEmpresa, Integer idUsuario);

}
