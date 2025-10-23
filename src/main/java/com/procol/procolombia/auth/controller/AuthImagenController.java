package com.procol.procolombia.auth.controller;

import com.procol.procolombia.auth.dto.Response.ApiResponseDTO;
import com.procol.procolombia.auth.dto.Response.ImagenResponseDTO;
import com.procol.procolombia.auth.service.ImagenService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/imagen")
public class AuthImagenController {

    private final ImagenService imagenService;

    public AuthImagenController(ImagenService imagenService) {
        this.imagenService = imagenService;
    }

    @PostMapping("/subir")
    @PreAuthorize("hasAnyRole('ADMINISTRADOR', 'Aspirante')")
    public ResponseEntity<ApiResponseDTO<ImagenResponseDTO>> addImagen(
            @RequestParam Integer idUsuario,
            @RequestParam Short favoritaImagen,
            @RequestParam("archivo") MultipartFile archivo
    ) {
        ApiResponseDTO<ImagenResponseDTO> responseDTO = imagenService.agregarImagen(idUsuario, archivo, favoritaImagen);
        return ResponseEntity.status(responseDTO.codigoEstado()).body(responseDTO);
    }

    @GetMapping("/usuario/{idUsuario}")
    @PreAuthorize("hasAnyRole('ADMINISTRADOR', 'ASPIRANTE', 'EMPRESA')")
    public ResponseEntity<ApiResponseDTO<ImagenResponseDTO>> getUsuario(@PathVariable Integer idUsuario) {
        ApiResponseDTO<ImagenResponseDTO> responseDTO = imagenService.imagenPerfil(idUsuario);
        return ResponseEntity.status(responseDTO.codigoEstado()).body(responseDTO);
    }

    @DeleteMapping("/{IdImagen}")
    @PreAuthorize("hasAnyRole('ADMINISTRADOR', ' ASPIRANTE')")
    public ResponseEntity<ApiResponseDTO<String>> eliminarImagen(@PathVariable Integer IdImagen) {
        ApiResponseDTO<String> responseDTO = imagenService.eliminarImagen(IdImagen);
        return ResponseEntity.status(responseDTO.codigoEstado()).body(responseDTO);
    }

    @PutMapping("{idImagen}/favorita")
    @PreAuthorize("hasAnyRole('Administrador', 'ASPIRANTE')")
    public ResponseEntity<ApiResponseDTO<ImagenResponseDTO>> marcarFavorita(
            @PathVariable Integer idImagen,
            @RequestParam Integer idUsuario
    ) {
        ApiResponseDTO<ImagenResponseDTO> responseDTO = imagenService.marcarFavorita(idImagen, idUsuario);
        return ResponseEntity.status(responseDTO.codigoEstado()).body(responseDTO);
    }
}
