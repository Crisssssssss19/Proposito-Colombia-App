package com.procol.procolombia.auth.service.impl;

import com.procol.procolombia.auth.dto.Response.ApiResponseDTO;
import com.procol.procolombia.auth.dto.Response.UbicacionResponseDTO;
import com.procol.procolombia.auth.mappers.UbicacionMapper;
import com.procol.procolombia.auth.repositories.UbicacioneRepository;
import com.procol.procolombia.auth.service.UbicacionService;

import java.time.LocalDateTime;
import java.util.List;

public class UbicacionServiceImpl implements UbicacionService {

    private final UbicacioneRepository ubicacioneRepository;
    private final UbicacionMapper ubicacionMapper;
    public UbicacionServiceImpl(UbicacioneRepository ubicacioneRepository, UbicacionMapper ubicacionMapper) {
        this.ubicacioneRepository = ubicacioneRepository;
        this.ubicacionMapper = ubicacionMapper;
    }

    @Override
    public ApiResponseDTO<List<UbicacionResponseDTO>> listarUbicaciones() {
        List<UbicacionResponseDTO> lista = ubicacioneRepository.findAll()
                .stream()
                .map(ubicacionMapper::toDto)
                .toList();

        return new ApiResponseDTO<>(200, "Lista de ubicaciones: "+lista.size(), lista, LocalDateTime.now().toString());
    }

    @Override
    public ApiResponseDTO<List<UbicacionResponseDTO>> autocompletarUbicaciones(String texto) {
        List<UbicacionResponseDTO> lista = ubicacioneRepository
                .findByNombreUbicacionContainingIgnoreCase(texto)
                .stream()
                .map(ubicacionMapper::toDto)
                .toList();

        return new ApiResponseDTO<>(200, "Resultados de autocompletar: "+lista.size(), lista, LocalDateTime.now().toString());
    }
}
