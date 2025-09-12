package com.procol.procolombia.vacante.controllers;

import com.procol.procolombia.vacante.services.AnuncioService;
import com.procol.procolombia.vacante.dto.AnuncioDto;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/anuncios")
public class AnuncioController {
    private final AnuncioService anuncioService;

    public AnuncioController(AnuncioService anuncioService) {
        this.anuncioService = anuncioService;
    }

    @PostMapping
    public ResponseEntity<AnuncioDto> create(@RequestBody AnuncioDto dto) {
        return ResponseEntity.ok(anuncioService.createAnuncio(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AnuncioDto> update(@PathVariable Integer id, @RequestBody AnuncioDto dto) {
        return ResponseEntity.ok(anuncioService.updateAnuncio(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        anuncioService.deleteAnuncio(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnuncioDto> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(anuncioService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<AnuncioDto>> findAll() {
        return ResponseEntity.ok(anuncioService.findAll());
    }

    @GetMapping("/vacante/{vacanteId}")
    public ResponseEntity<AnuncioDto> findByVacanteId(@PathVariable Integer vacanteId) {
        return ResponseEntity.ok(anuncioService.findByVacanteId(vacanteId));
    }
}
