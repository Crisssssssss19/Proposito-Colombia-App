package com.procol.procolombia.auth.controller;

import com.procol.procolombia.auth.dto.Response.ApiResponseDTO;
import com.procol.procolombia.auth.repositories.PreRegistroRepository;
import com.procol.procolombia.auth.service.PreRegistroService;
import jakarta.annotation.security.PermitAll;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/preregistro")
public class PreRegistroController {
    private final PreRegistroService preRegistroService;
    public PreRegistroController(PreRegistroService preRegistroService, PreRegistroRepository preRegistroRepository) {
        this.preRegistroService = preRegistroService;
    }

    @PostMapping("/enviar")
    @PermitAll
    public ResponseEntity<ApiResponseDTO<String>> enviarCodigo(@RequestParam String telefono){
        ApiResponseDTO<String> apiResponseDTO = preRegistroService.enviarCodigo(telefono);
        return ResponseEntity.status(apiResponseDTO.codigoEstado()).body(apiResponseDTO);
    }

    @PostMapping("/validar")
    @PermitAll
    public ResponseEntity<ApiResponseDTO<String>> validarCodigo(@RequestParam String telefono, @RequestParam String codigo){
        ApiResponseDTO<String> apiResponseDTO = preRegistroService.validarCodigo(telefono, codigo);
        return ResponseEntity.status(apiResponseDTO.codigoEstado()).body(apiResponseDTO);
    }
}
