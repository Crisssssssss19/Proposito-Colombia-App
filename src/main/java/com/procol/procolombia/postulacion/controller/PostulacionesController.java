package com.procol.procolombia.postulacion.controller;

import com.procol.procolombia.postulacion.dto.PostulacioneDto;
import com.procol.procolombia.postulacion.services.postulacion.PostulacioneServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/postulaciones")
@CrossOrigin(origins = "*")
public class PostulacionesController {

    private final PostulacioneServiceImpl postulacioneService;

    public PostulacionesController(PostulacioneServiceImpl postulacioneService) {
        this.postulacioneService = postulacioneService;
    }

    @GetMapping
    public ResponseEntity<List<PostulacioneDto>> getAll() {
        return ResponseEntity.ok(postulacioneService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostulacioneDto> getById(@PathVariable Integer id) {
        return postulacioneService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<PostulacioneDto> create(@Valid @RequestBody PostulacioneDto dto) {
        return ResponseEntity.ok(postulacioneService.save(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostulacioneDto> update(@PathVariable Integer id, @Valid @RequestBody PostulacioneDto dto) {
        return ResponseEntity.ok(postulacioneService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        postulacioneService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<PostulacioneDto>> getByUsuario(@PathVariable Integer idUsuario) {
        return ResponseEntity.ok(postulacioneService.findByUsuario(idUsuario));
    }

    @GetMapping("/vacante/{idVacante}")
    public ResponseEntity<List<PostulacioneDto>> getByVacante(@PathVariable Integer idVacante) {
        return ResponseEntity.ok(postulacioneService.findByVacante(idVacante));
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<PostulacioneDto>> getByEstado(@PathVariable Short estado) {
        return ResponseEntity.ok(postulacioneService.findByEstado(estado));
    }

    @GetMapping("/usuario/{idUsuario}/page")
    public ResponseEntity<Page<PostulacioneDto>> getByUsuarioPaginated(@PathVariable Integer idUsuario, Pageable pageable) {
        return ResponseEntity.ok(postulacioneService.findByUsuarioPaginated(idUsuario, pageable));
    }

    @GetMapping("/vacante/{idVacante}/page")
    public ResponseEntity<Page<PostulacioneDto>> getByVacantePaginated(@PathVariable Integer idVacante, Pageable pageable) {
        return ResponseEntity.ok(postulacioneService.findByVacantePaginated(idVacante, pageable));
    }

    @GetMapping("/usuario/{idUsuario}/vacante/{idVacante}")
    public ResponseEntity<PostulacioneDto> getByUsuarioAndVacante(@PathVariable Integer idUsuario, @PathVariable Integer idVacante) {
        return postulacioneService.findByUsuarioAndVacante(idUsuario, idVacante)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/correspondencia/{correspondencia}")
    public ResponseEntity<List<PostulacioneDto>> getByCorrespondencia(@PathVariable Short correspondencia) {
        return ResponseEntity.ok(postulacioneService.findByCorrespondencia(correspondencia));
    }

    @GetMapping("/vacante/{idVacante}/count")
    public ResponseEntity<Long> countByVacante(@PathVariable Integer idVacante) {
        return ResponseEntity.ok(postulacioneService.countByVacante(idVacante));
    }

    @GetMapping("/usuario/{idUsuario}/count")
    public ResponseEntity<Long> countByUsuario(@PathVariable Integer idUsuario) {
        return ResponseEntity.ok(postulacioneService.countByUsuario(idUsuario));
    }

    @GetMapping("/empresa/{idEmpresa}/page")
    public ResponseEntity<Page<PostulacioneDto>> getByEmpresa(@PathVariable Integer idEmpresa, Pageable pageable) {
        return ResponseEntity.ok(postulacioneService.findByEmpresa(idEmpresa, pageable));
    }

    @PostMapping("/crear")
    public ResponseEntity<PostulacioneDto> crearPostulacion(
            @RequestParam Integer usuarioId,
            @RequestParam Integer vacanteId
    ) {
        return ResponseEntity.ok(postulacioneService.crearPostulacion(usuarioId, vacanteId));
    }
}
