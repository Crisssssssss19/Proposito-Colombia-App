package com.procol.procolombia.auth.service.impl;

import com.procol.procolombia.auth.dto.Response.ApiResponseDTO;
import com.procol.procolombia.auth.dto.Response.RolResponseDTO;
import com.procol.procolombia.auth.mappers.RolMapper;
import com.procol.procolombia.auth.repositories.RoleRepository;
import com.procol.procolombia.auth.service.RolService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class RolServiceImpl implements RolService {

    private final RoleRepository roleRepository;
    private final RolMapper rolMapper;

    public RolServiceImpl(RoleRepository roleRepository, RolMapper rolMapper) {
        this.roleRepository = roleRepository;
        this.rolMapper = rolMapper;
    }

    @Override
    public ApiResponseDTO<List<RolResponseDTO>> listarRols() {
        List<RolResponseDTO> listaRols = roleRepository.findAll()
                .stream()
                .map(rolMapper::toDto)
                .toList();

        return new ApiResponseDTO<>(200, "Lista de roles: "+listaRols.size(), listaRols, LocalDateTime.now().toString());
    }
}
