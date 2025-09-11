package com.procol.procolombia.auth.service.impl;

import com.procol.procolombia.auth.dto.Request.UsuarioRequestDTO;
import com.procol.procolombia.auth.dto.Response.AccesoResponseDTO;
import com.procol.procolombia.auth.dto.Response.ApiResponseDTO;
import com.procol.procolombia.auth.exception.notfound.AccesoNotFoundException;
import com.procol.procolombia.auth.mappers.AccesoMapper;
import com.procol.procolombia.auth.repositories.AccesoRepository;
import com.procol.procolombia.auth.service.AccesoService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AccesoServiceImpl implements AccesoService {

    private final AccesoRepository accesoRepository;
    private final AccesoMapper accesoMapper;

    public AccesoServiceImpl(AccesoRepository accesoRepository, AccesoMapper accesoMapper) {
        this.accesoRepository = accesoRepository;
        this.accesoMapper = accesoMapper;
    }


    @Override
    public ApiResponseDTO<String> eliminarAcceso(Integer idAcceso) {
        if(!accesoRepository.existsById(idAcceso)){
            throw new AccesoNotFoundException("Acceso no encontrado con id: "+idAcceso);
        }
        accesoRepository.deleteById(idAcceso);
        return new ApiResponseDTO<>(200, "Acceso eliminado con exito", null, LocalDateTime.now().toString());
    }

    @Override
    public ApiResponseDTO<AccesoResponseDTO> editarAcceso(Integer idUsuario, UsuarioRequestDTO requestDTO) {
        return null;
    }

    @Override
    public ApiResponseDTO<List<AccesoResponseDTO>> ListarAcceso(Integer idUsuario) {
        return null;
    }

    @Override
    public ApiResponseDTO<AccesoResponseDTO> crearAcceso(UsuarioRequestDTO requestDTO) {
        return null;
    }

    @Override
    public ApiResponseDTO<AccesoResponseDTO> obtenerAccesoPorId(Integer idUsuario) {
        return null;
    }
}
