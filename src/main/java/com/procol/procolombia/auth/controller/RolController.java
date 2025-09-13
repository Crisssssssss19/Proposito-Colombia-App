package com.procol.procolombia.auth.controller;

import com.procol.procolombia.auth.dto.Response.ApiResponseDTO;
import com.procol.procolombia.auth.dto.Response.RolResponseDTO;
import com.procol.procolombia.auth.service.RolService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rol")
public class RolController {

    private final RolService rolService;

    public RolController(RolService rolService) {
        this.rolService = rolService;
    }

    @GetMapping
    @PreAuthorize("hasRole('Administrador')")
    public ResponseEntity<ApiResponseDTO<List<RolResponseDTO>>> listarRols() {
        ApiResponseDTO<List<RolResponseDTO>> roles = rolService.listarRols();
        return ResponseEntity.status(listarRols().getStatusCode()).body(roles);
    }
}
