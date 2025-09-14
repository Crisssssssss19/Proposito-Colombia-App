package com.procol.procolombia.vacante.controllers;


import com.procol.procolombia.vacante.services.AnuncioService;
import com.procol.procolombia.vacante.dto.AnuncioDto;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import com.procol.procolombia.vacante.response.ApiResponse;

import java.util.List;

@RestController
@RequestMapping("/anuncios")
public class AnuncioController {
    private final AnuncioService anuncioService;

    public AnuncioController(AnuncioService anuncioService) {
        this.anuncioService = anuncioService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<AnuncioDto>> create(@RequestBody AnuncioDto dto) {
        AnuncioDto created = anuncioService.createAnuncio(dto);
        ApiResponse<AnuncioDto> response = new ApiResponse<>(
                200,
                "Anuncio creado exitosamente",
                created
        );
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<AnuncioDto>> update(@PathVariable Integer id, @RequestBody AnuncioDto dto) {
        AnuncioDto updated = anuncioService.updateAnuncio(id, dto);
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
}
