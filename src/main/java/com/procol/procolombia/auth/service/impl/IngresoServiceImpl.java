package com.procol.procolombia.auth.service.impl;

import com.procol.procolombia.auth.dto.Response.ApiResponseDTO;
import com.procol.procolombia.auth.dto.Response.IngresoResponseDTO;
import com.procol.procolombia.auth.entities.Acceso;
import com.procol.procolombia.auth.entities.Ingreso;
import com.procol.procolombia.auth.entities.Usuario;
import com.procol.procolombia.auth.exception.notfound.UsuarioNotFoundException;
import com.procol.procolombia.auth.mappers.IngresoMapper;
import com.procol.procolombia.auth.repositories.AccesoRepository;
import com.procol.procolombia.auth.repositories.IngresoRepository;
import com.procol.procolombia.auth.repositories.UsuarioRepository;
import com.procol.procolombia.auth.service.IngresoService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class IngresoServiceImpl implements IngresoService {

    private final IngresoRepository ingresoRepository;
    private final IngresoMapper ingresoMapper;
    private final AccesoRepository accesoRepository;
    public IngresoServiceImpl(IngresoRepository ingresoRepository, AccesoRepository accesoRepository, IngresoMapper ingresoMapper) {
        this.ingresoRepository = ingresoRepository;
        this.ingresoMapper = ingresoMapper;
        this.accesoRepository = accesoRepository;
    }

    @Override
    public ApiResponseDTO<IngresoResponseDTO> crearIngreso(Integer idAcceso) {
        Acceso acceso = accesoRepository.findById(idAcceso)
                .orElseThrow(() -> new UsuarioNotFoundException("Usuario no encontrado"));

        Ingreso ingreso = new Ingreso();
        ingreso.setId(ingreso.getId());
        ingreso.setIdUsuario(acceso);
        ingreso.setFechaIngreso(LocalDateTime.now());
        ingresoRepository.save(ingreso);

        return new ApiResponseDTO<>(201, "Ingreso creado", ingresoMapper.toDto(ingreso), LocalDateTime.now().toString());
    }

    @Override
    public ApiResponseDTO<List<IngresoResponseDTO>> listarIngresos() {
        List<IngresoResponseDTO> listaIngresos = ingresoRepository.findAll()
                .stream()
                .map(ingresoMapper::toDto)
                .toList();

        return new ApiResponseDTO<>(200, "Lista de ingresos: "+listaIngresos.size(), listaIngresos, LocalDateTime.now().toString());
    }

    @Override
    public ApiResponseDTO<IngresoResponseDTO> buscarIngresoPorId(Integer id) {
        Ingreso ingreso = ingresoRepository.findById(id)
                .orElseThrow(() -> new UsuarioNotFoundException("Usuario no encontrado"));
        return new ApiResponseDTO<>(200, "ingreso encontrado", ingresoMapper.toDto(ingreso), LocalDateTime.now().toString());
    }

    @Override
    public ApiResponseDTO<List<IngresoResponseDTO>> listarIngresosPorUsuario(Integer idUsuario) {
        List<Ingreso> ingresos = ingresoRepository.findByIdUsuario_Id(idUsuario);
        List<IngresoResponseDTO> ingresoResponseDTOs = ingresos.stream().map(ingresoMapper::toDto).toList();

        return new ApiResponseDTO<>(200, "Ingresos encontrados: "+ingresoResponseDTOs.size(), ingresoResponseDTOs, LocalDateTime.now().toString());
    }
}
