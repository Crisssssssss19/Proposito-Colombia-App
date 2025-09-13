package com.procol.procolombia.auth.controller;

import com.procol.procolombia.auth.dto.Response.ApiResponseDTO;
import com.procol.procolombia.auth.dto.Response.AuditoriaResponseDTO;
import com.procol.procolombia.auth.service.AuditoriaService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/auditoria")
public class AuditoriaController {
    private final AuditoriaService auditoriaService;
    public AuditoriaController(AuditoriaService auditoriaService) {
        this.auditoriaService = auditoriaService;
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMINISTRADOR', 'Tecnologia')")
    public ResponseEntity<ApiResponseDTO<List<AuditoriaResponseDTO>>> getAllAuditorias() {

        return ResponseEntity.ok(auditoriaService.listarAuditorias());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMINISTRADOR', 'Tecnologia')")
    public ResponseEntity<ApiResponseDTO<AuditoriaResponseDTO>> getAuditoria(@PathVariable Integer idAuditoria) {
        return ResponseEntity.ok(auditoriaService.obtenerAuditoriaPorId(idAuditoria));
    }
}
