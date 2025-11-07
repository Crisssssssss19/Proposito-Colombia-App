package com.procol.procolombia.auth.controller;

import com.procol.procolombia.auth.dto.Response.ApiResponseDTO;
import com.procol.procolombia.auth.dto.Response.CorreoVerificacionResponseDTO;
import com.procol.procolombia.auth.service.CorreoVerificacionService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/correo")
public class CorreoVerificacionController {
    private final CorreoVerificacionService correoVerificacionService;
    public CorreoVerificacionController(CorreoVerificacionService correoVerificacionService) {
        this.correoVerificacionService = correoVerificacionService;
    }

    @PostMapping("/enviar")
    @PreAuthorize("hasAnyAuthority('EMPRESA', 'ASPIRANTE')")
    public ResponseEntity<ApiResponseDTO<String>> enviarCodigo(@RequestParam String correoVerificacion){
        ApiResponseDTO<String> apiResponseDTO = correoVerificacionService.enviarVerificarCorreo(correoVerificacion);
        return ResponseEntity.status(apiResponseDTO.codigoEstado()).body(apiResponseDTO);
    }

    @PostMapping("/validar")
    @PreAuthorize("hasAnyAuthority('EMPRESA', 'ASPIRANTE')")
    public ResponseEntity<ApiResponseDTO<String>> validarCodigo(@RequestParam String correoVerificacion, @RequestParam String codigo){
        ApiResponseDTO<String> apiResponseDTO = correoVerificacionService.verificarCorreo(correoVerificacion, codigo);
        return ResponseEntity.status(apiResponseDTO.codigoEstado()).body(apiResponseDTO);
    }

    @GetMapping("/buscar")
    @PreAuthorize("hasAnyAuthority('EMPRESA', 'ASPIRANTE')")
    public ResponseEntity<ApiResponseDTO<CorreoVerificacionResponseDTO>> obtenerPorCorreo(@RequestParam String correoVerificacion) {
        ApiResponseDTO<CorreoVerificacionResponseDTO> apiResponseDTO = correoVerificacionService.obtenerPorCorreo(correoVerificacion);
        return ResponseEntity.status(apiResponseDTO.codigoEstado()).body(apiResponseDTO);
    }
}
