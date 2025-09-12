package com.procol.procolombia.auth.controller;

import com.procol.procolombia.auth.dto.Response.ApiResponseDTO;
import com.procol.procolombia.auth.dto.Response.ImagenResponseDTO;
import com.procol.procolombia.auth.service.ImagenService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/usuario/imagen")
public class ImagenController {

    private final ImagenService imagenService;

    private ImagenController(ImagenService imagenService) {
        this.imagenService = imagenService;
    }

    @PostMapping("/agregar")
    public ResponseEntity<ApiResponseDTO<ImagenResponseDTO>> addImagen(@RequestParam("idUsuario") Integer idUsuario, @RequestParam("imagen") MultipartFile imagen) {
        ApiResponseDTO<ImagenResponseDTO> response = imagenService.agregarImagen(idUsuario, imagen, (short) 1);
        return ResponseEntity.status(response.codigoEstado()).body(response);
    }
}
