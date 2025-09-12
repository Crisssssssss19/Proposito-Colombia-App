package com.procol.procolombia.postulacion.controller;

import com.procol.procolombia.postulacion.dto.MensajeDto;
import com.procol.procolombia.postulacion.services.mensaje.MensajeServiceImpl;
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
@RequestMapping("/api/mensajes")
@CrossOrigin(origins = "*")
public class MensajeController {

    private final MensajeServiceImpl mensajeService;

    public MensajeController(MensajeServiceImpl mensajeService) {
        this.mensajeService = mensajeService;
    }

    @GetMapping
    public ResponseEntity<List<MensajeDto>> getAllMensajes() {
        try {
            List<MensajeDto> mensajes = mensajeService.findAll();
            return ResponseEntity.ok(mensajes);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<MensajeDto> getMensajeById(@PathVariable Integer id) {
        try {
            Optional<MensajeDto> mensaje = mensajeService.findById(id);
            return mensaje.map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<MensajeDto> createMensaje(@Valid @RequestBody MensajeDto mensajeDto) {
        try {
            MensajeDto savedMensaje = mensajeService.save(mensajeDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedMensaje);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<MensajeDto> updateMensaje(@PathVariable Integer id, @Valid @RequestBody MensajeDto mensajeDto) {
        try {
            MensajeDto updatedMensaje = mensajeService.update(id, mensajeDto);
            return ResponseEntity.ok(updatedMensaje);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMensaje(@PathVariable Integer id) {
        try {
            mensajeService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Endpoints específicos del negocio
    @GetMapping("/postulacion/{postulacionId}")
    public ResponseEntity<List<MensajeDto>> getMensajesByPostulacion(@PathVariable Integer postulacionId) {
        try {
            List<MensajeDto> mensajes = mensajeService.findByPostulacion(postulacionId);
            return ResponseEntity.ok(mensajes);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<MensajeDto>> getMensajesByUsuario(@PathVariable Integer usuarioId) {
        try {
            List<MensajeDto> mensajes = mensajeService.findByUsuario(usuarioId);
            return ResponseEntity.ok(mensajes);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/estado/{estadoMensaje}")
    public ResponseEntity<List<MensajeDto>> getMensajesByEstado(@PathVariable Short estadoMensaje) {
        try {
            List<MensajeDto> mensajes = mensajeService.findByEstado(estadoMensaje);
            return ResponseEntity.ok(mensajes);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/postulacion/{postulacionId}/paginado")
    public ResponseEntity<Page<MensajeDto>> getMensajesByPostulacionPaginated(
            @PathVariable Integer postulacionId,
            Pageable pageable) {
        try {
            Page<MensajeDto> mensajes = mensajeService.findByPostulacionPaginated(postulacionId, pageable);
            return ResponseEntity.ok(mensajes);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/postulacion/{postulacionId}/visibles")
    public ResponseEntity<List<MensajeDto>> getMensajesVisiblesByPostulacion(@PathVariable Integer postulacionId) {
        try {
            List<MensajeDto> mensajes = mensajeService.findMensajesVisiblesByPostulacion(postulacionId);
            return ResponseEntity.ok(mensajes);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/count/postulacion/{postulacionId}")
    public ResponseEntity<Long> countByPostulacion(@PathVariable Integer postulacionId) {
        try {
            long count = mensajeService.countByPostulacion(postulacionId);
            return ResponseEntity.ok(count);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/fecha-range")
    public ResponseEntity<List<MensajeDto>> getMensajesByFechaRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Instant fechaInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Instant fechaFin) {
        try {
            List<MensajeDto> mensajes = mensajeService.findByFechaRange(fechaInicio, fechaFin);
            return ResponseEntity.ok(mensajes);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/crear")
    public ResponseEntity<MensajeDto> crearMensaje(
            @RequestParam Integer postulacionId,
            @RequestParam Integer usuarioId,
            @RequestParam String texto) {
        try {
            MensajeDto mensaje = mensajeService.crearMensaje(postulacionId, usuarioId, texto);
            return ResponseEntity.status(HttpStatus.CREATED).body(mensaje);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
