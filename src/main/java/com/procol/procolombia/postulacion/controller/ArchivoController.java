package com.procol.procolombia.postulacion.controller;

import com.procol.procolombia.postulacion.dto.ArchivoDto;
import com.procol.procolombia.postulacion.services.archivo.ArchivoServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/archivos")
@CrossOrigin(origins = "*")
public class ArchivoController {

    private final ArchivoServiceImpl archivoService;

    public ArchivoController(ArchivoServiceImpl archivoService) {
        this.archivoService = archivoService;
    }

    @GetMapping
    public ResponseEntity<List<ArchivoDto>> getAllArchivos() {
        try {
            List<ArchivoDto> archivos = archivoService.findAll();
            return ResponseEntity.ok(archivos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArchivoDto> getArchivoById(@PathVariable Integer id) {
        try {
            Optional<ArchivoDto> archivo = archivoService.findById(id);
            return archivo.map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<ArchivoDto> createArchivo(@Valid @RequestBody ArchivoDto archivoDto) {
        try {
            ArchivoDto savedArchivo = archivoService.save(archivoDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedArchivo);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ArchivoDto> updateArchivo(@PathVariable Integer id, @Valid @RequestBody ArchivoDto archivoDto) {
        try {
            ArchivoDto updatedArchivo = archivoService.update(id, archivoDto);
            return ResponseEntity.ok(updatedArchivo);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArchivo(@PathVariable Integer id) {
        try {
            archivoService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<ArchivoDto>> getArchivosByUsuario(@PathVariable Integer usuarioId) {
        try {
            List<ArchivoDto> archivos = archivoService.findByUsuario(usuarioId);
            return ResponseEntity.ok(archivos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/grupo/{grupoArchivo}")
    public ResponseEntity<List<ArchivoDto>> getArchivosByGrupo(@PathVariable Short grupoArchivo) {
        try {
            List<ArchivoDto> archivos = archivoService.findByGrupoArchivo(grupoArchivo);
            return ResponseEntity.ok(archivos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/tipo/{tipoArchivo}")
    public ResponseEntity<List<ArchivoDto>> getArchivosByTipo(@PathVariable String tipoArchivo) {
        try {
            List<ArchivoDto> archivos = archivoService.findByTipoArchivo(tipoArchivo);
            return ResponseEntity.ok(archivos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/usuario/{usuarioId}/grupo/{grupo}")
    public ResponseEntity<List<ArchivoDto>> getArchivosByUsuarioAndGrupo(
            @PathVariable Integer usuarioId,
            @PathVariable Short grupo) {
        try {
            List<ArchivoDto> archivos = archivoService.findByUsuarioAndGrupo(usuarioId, grupo);
            return ResponseEntity.ok(archivos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/count/usuario/{usuarioId}")
    public ResponseEntity<Long> countArchivosByUsuario(@PathVariable Integer usuarioId) {
        try {
            long count = archivoService.countByUsuario(usuarioId);
            return ResponseEntity.ok(count);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<ArchivoDto>> searchArchivosByNombre(@RequestParam String nombre) {
        try {
            List<ArchivoDto> archivos = archivoService.searchByNombre(nombre);
            return ResponseEntity.ok(archivos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
