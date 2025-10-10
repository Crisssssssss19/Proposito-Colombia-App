package com.procol.procolombia.vacante.controllers;

import com.procol.procolombia.vacante.repositories.AnuncioRepository;
import com.procol.procolombia.vacante.services.AnuncioService;
import com.procol.procolombia.vacante.dto.AnuncioDto;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import com.procol.procolombia.vacante.response.ApiResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.core.io.Resource;


@RestController
@RequestMapping("/anuncios")
public class AnuncioController {
    private final AnuncioService anuncioService;

    public AnuncioController(AnuncioService anuncioService) {
        this.anuncioService = anuncioService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<AnuncioDto>> create(
            @RequestParam("idVacante") Integer idVacante,
            @RequestParam("file") MultipartFile file) {

        AnuncioDto created = anuncioService.createAnuncio(idVacante, file);

        ApiResponse<AnuncioDto> response = new ApiResponse<>(
                200,
                "Anuncio creado exitosamente",
                created
        );
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}/imagen")
    public ResponseEntity<ApiResponse<AnuncioDto>> update(@PathVariable Integer id,
                                                          @RequestParam("file") MultipartFile file) {
        AnuncioDto updated = anuncioService.updateAnuncio(id, file);
        ApiResponse<AnuncioDto> response = new ApiResponse<>(
                201,
                "Anuncio actualizado exitosamente",
                updated
        );
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Integer>> delete(@PathVariable Integer id) {
        anuncioService.deleteAnuncio(id);
        ApiResponse<Integer> response = new ApiResponse<>(
                200,
                "Anuncio eliminado exitosamente",
                id
        );
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<AnuncioDto>> findById(@PathVariable Integer id) {
        AnuncioDto anuncio = anuncioService.findById(id);
        ApiResponse<AnuncioDto> response = new ApiResponse<>(
                200,
                "Consulta existosa",
                anuncio
        );
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<AnuncioDto>>> findAll() {
        List<AnuncioDto> anuncios = anuncioService.findAll();
        ApiResponse<List<AnuncioDto>> response = new ApiResponse<>(
          200,
          "Consulta exitosa",
          anuncios
        );
        return ResponseEntity.ok(response);
    }

    @GetMapping("/vacante/{vacanteId}")
    public ResponseEntity<ApiResponse<AnuncioDto>> findByVacanteId(@PathVariable Integer vacanteId) {
        AnuncioDto anuncio = anuncioService.findByVacanteId(vacanteId);
        ApiResponse<AnuncioDto> response = new ApiResponse<>(
                200,
                "Consulta exitosa",
                anuncio
        );
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}/imagen")
    public ResponseEntity<Resource> verImagen(@PathVariable Integer id) {
        AnuncioDto anuncio = anuncioService.findByVacanteId(id);
        Resource recurso = anuncioService.verImagen(id);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(anuncio.tipoAnuncio()))
                .body(recurso);
    }
}
