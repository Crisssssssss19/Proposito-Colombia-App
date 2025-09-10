package com.procol.procolombia.postulacion.controller;


import com.procol.procolombia.postulacion.dto.EstadosPostulacioneDto;
import com.procol.procolombia.postulacion.services.EstadosPostulaciones.EstadosPostulacioneServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/estados-postulacion")
@CrossOrigin(origins = "*")
public class EstadosPostulacioneController {

    private final EstadosPostulacioneServiceImpl estadosPostulacioneService;

    public EstadosPostulacioneController(EstadosPostulacioneServiceImpl estadosPostulacioneService) {
        this.estadosPostulacioneService = estadosPostulacioneService;
    }

    @GetMapping
    public ResponseEntity<List<EstadosPostulacioneDto>> getAllEstados() {
        try {
            List<EstadosPostulacioneDto> estados = estadosPostulacioneService.findAll();
            return ResponseEntity.ok(estados);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstadosPostulacioneDto> getEstadoById(@PathVariable Integer id) {
        try {
            Optional<EstadosPostulacioneDto> estado = estadosPostulacioneService.findById(id);
            return estado.map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<EstadosPostulacioneDto> createEstado(@Valid @RequestBody EstadosPostulacioneDto estadosPostulacioneDto) {
        try {
            EstadosPostulacioneDto savedEstado = estadosPostulacioneService.save(estadosPostulacioneDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedEstado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<EstadosPostulacioneDto> updateEstado(@PathVariable Integer id, @Valid @RequestBody EstadosPostulacioneDto estadosPostulacioneDto) {
        try {
            EstadosPostulacioneDto updatedEstado = estadosPostulacioneService.update(id, estadosPostulacioneDto);
            return ResponseEntity.ok(updatedEstado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEstado(@PathVariable Integer id) {
        try {
            estadosPostulacioneService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Endpoints específicos del negocio
    @GetMapping("/nombre/{nombreEstado}")
    public ResponseEntity<EstadosPostulacioneDto> getEstadoByNombre(@PathVariable String nombreEstado) {
        try {
            Optional<EstadosPostulacioneDto> estado = estadosPostulacioneService.findByNombre(nombreEstado);
            return estado.map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/ordenados")
    public ResponseEntity<List<EstadosPostulacioneDto>> getEstadosOrdenados() {
        try {
            List<EstadosPostulacioneDto> estados = estadosPostulacioneService.findAllOrderByOrden();
            return ResponseEntity.ok(estados);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/orden/{orden}")
    public ResponseEntity<EstadosPostulacioneDto> getEstadoByOrden(@PathVariable Short orden) {
        try {
            Optional<EstadosPostulacioneDto> estado = estadosPostulacioneService.findByOrden(orden);
            return estado.map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/count-historial/{estadoId}")
    public ResponseEntity<Long> countHistorialByEstado(@PathVariable Integer estadoId) {
        try {
            long count = estadosPostulacioneService.countHistorialByEstado(estadoId);
            return ResponseEntity.ok(count);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<EstadosPostulacioneDto>> searchEstadosByNombre(@RequestParam String nombre) {
        try {
            List<EstadosPostulacioneDto> estados = estadosPostulacioneService.searchByNombre(nombre);
            return ResponseEntity.ok(estados);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/exists/{nombreEstado}")
    public ResponseEntity<Boolean> existsByNombre(@PathVariable String nombreEstado) {
        try {
            boolean exists = estadosPostulacioneService.existsByNombre(nombreEstado);
            return ResponseEntity.ok(exists);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
