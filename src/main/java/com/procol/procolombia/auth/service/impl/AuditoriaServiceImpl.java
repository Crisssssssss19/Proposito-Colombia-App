package com.procol.procolombia.auth.service.impl;

import com.procol.procolombia.auth.dto.Response.ApiResponseDTO;
import com.procol.procolombia.auth.dto.Response.AuditoriaResponseDTO;
import com.procol.procolombia.auth.entities.Auditoria;
import com.procol.procolombia.auth.exception.notfound.AuditoriaNotFoundException;
import com.procol.procolombia.auth.mappers.AuditoriaMapper;
import com.procol.procolombia.auth.repositories.AuditoriaRepository;
import com.procol.procolombia.auth.service.AuditoriaService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AuditoriaServiceImpl implements AuditoriaService {
    private final AuditoriaRepository auditoriaRepository;
    private final AuditoriaMapper auditoriaMapper;
    public AuditoriaServiceImpl(AuditoriaRepository auditoriaRepository, AuditoriaMapper auditoriaMapper) {
        this.auditoriaRepository = auditoriaRepository;
        this.auditoriaMapper = auditoriaMapper;
    }


    @Override
    public ApiResponseDTO<List<AuditoriaResponseDTO>> listarAuditorias() {
        List<AuditoriaResponseDTO> auditorias = auditoriaRepository.findAll()
                .stream()
                .map(auditoriaMapper::toDto)
                .toList();
        return new ApiResponseDTO<>(200, "Lista de auditorias: "+auditorias.size(), auditorias, LocalDateTime.now().toString());
    }

    @Override
    public ApiResponseDTO<AuditoriaResponseDTO> obtenerAuditoriaPorId(Integer idAuditoria) {
        Auditoria auditoria = auditoriaRepository.findById(idAuditoria)
                .orElseThrow(() -> new AuditoriaNotFoundException("Auditoria no encontrada por id: "+idAuditoria));
        return new ApiResponseDTO<>(200, "Auditoria encontrada", auditoriaMapper.toDto(auditoria), LocalDateTime.now().toString());
    }
}
