package com.procol.procolombia.postulacion.controller;

import com.procol.procolombia.postulacion.dto.HistorialEstadosPostulacioneDto;
import com.procol.procolombia.postulacion.services.historialEstadoPostulaciones.HistorialEstadosPostulacioneServiceImpl;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/historial-estados")
@CrossOrigin(origins = "*")
public class HistorialEstadosPostulacioneController {

    private final HistorialEstadosPostulacioneServiceImpl historialService;

    public HistorialEstadosPostulacioneController(HistorialEstadosPostulacioneServiceImpl historialService) {
        this.historialService = historialService;
    }

    @GetMapping
    public ResponseEntity<List<HistorialEstadosPostulacioneDto>> getAllHistoriales() {
        try {
            List<HistorialEstadosPostulacioneDto> historiales = historialService.findAll();
            return ResponseEntity.ok(historiales);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<HistorialEstadosPostulacioneDto> getHistorialById(@PathVariable Integer id) {
        try {
            Optional<HistorialEstadosPostulacioneDto> historial = historialService.findById(id);
            return historial.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<HistorialEstadosPostulacioneDto> createHistorial(@Valid @RequestBody HistorialEstadosPostulacioneDto historialDto) {
        try {
            HistorialEstadosPostulacioneDto savedHistorial = historialService.save(historialDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedHistorial);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<HistorialEstadosPostulacioneDto> updateHistorial(@PathVariable Integer id, @Valid @RequestBody HistorialEstadosPostulacioneDto historialDto) {
        try {
            HistorialEstadosPostulacioneDto updatedHistorial = historialService.update(id, historialDto);
            return ResponseEntity.ok(updatedHistorial);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHistorial(@PathVariable Integer id) {
        try {
            historialService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/postulacion/{postulacionId}")
    public ResponseEntity<List<HistorialEstadosPostulacioneDto>> getHistorialByPostulacion(@PathVariable Integer postulacionId) {
        try {
            List<HistorialEstadosPostulacioneDto> historiales = historialService.findByPostulacion(postulacionId);
            return ResponseEntity.ok(historiales);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/estado/{estadoId}")
    public ResponseEntity<List<HistorialEstadosPostulacioneDto>> getHistorialByEstado(@PathVariable Integer estadoId) {
        try {
            List<HistorialEstadosPostulacioneDto> historiales = historialService.findByEstado(estadoId);
            return ResponseEntity.ok(historiales);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/postulacion/{postulacionId}/ordenado")
    public ResponseEntity<List<HistorialEstadosPostulacioneDto>> getHistorialByPostulacionOrdenado(@PathVariable Integer postulacionId) {
        try {
            List<HistorialEstadosPostulacioneDto> historiales = historialService.findByPostulacionOrdenado(postulacionId);
            return ResponseEntity.ok(historiales);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/fecha-range")
    public ResponseEntity<Page<HistorialEstadosPostulacioneDto>> getHistorialByFechaRange(
    @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Instant fechaInicio,
    @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Instant fechaFin,
    Pageable pageable) {
        try {
            Page<HistorialEstadosPostulacioneDto> historiales = historialService.findByFechaRange(fechaInicio, fechaFin, pageable);
            return ResponseEntity.ok(historiales);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/count/postulacion/{postulacionId}")
    public ResponseEntity<Long> countByPostulacion(@PathVariable Integer postulacionId) {
        try {
            long count = historialService.countByPostulacion(postulacionId);
            return ResponseEntity.ok(count);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<HistorialEstadosPostulacioneDto>> searchByDetalle(@RequestParam String detalle) {
        try {
            List<HistorialEstadosPostulacioneDto> historiales = historialService.searchByDetalle(detalle);
            return ResponseEntity.ok(historiales);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}