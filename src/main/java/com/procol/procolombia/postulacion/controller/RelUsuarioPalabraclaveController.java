package com.procol.procolombia.postulacion.controller;

import com.procol.procolombia.postulacion.dto.RelUsuarioPalabraclaveDto;
import com.procol.procolombia.postulacion.entities.RelUsuarioPalabraclaveId;
import com.procol.procolombia.postulacion.services.relUsuarioPalabraClave.RelUsuarioPalabraclaveServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/relaciones-usuario-palabra")
@CrossOrigin(origins = "*")
public class RelUsuarioPalabraclaveController {

    private final RelUsuarioPalabraclaveServiceImpl relUsuarioPalabraclaveService;

    public RelUsuarioPalabraclaveController(RelUsuarioPalabraclaveServiceImpl relUsuarioPalabraclaveService) {
        this.relUsuarioPalabraclaveService = relUsuarioPalabraclaveService;
    }

    @GetMapping
    public ResponseEntity<List<RelUsuarioPalabraclaveDto>> getAll() {
        return ResponseEntity.ok(relUsuarioPalabraclaveService.findAll());
    }

    @GetMapping("/{idUsuario}/{idPalabraClave}")
    public ResponseEntity<RelUsuarioPalabraclaveDto> getById(
            @PathVariable Integer idUsuario,
            @PathVariable Integer idPalabraClave) {

        RelUsuarioPalabraclaveId id = new RelUsuarioPalabraclaveId();
        id.setIdUsuario(idUsuario);
        id.setIdPalabraClave(idPalabraClave);

        Optional<RelUsuarioPalabraclaveDto> relacion = relUsuarioPalabraclaveService.findById(id);
        return relacion.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<RelUsuarioPalabraclaveDto> create(@Valid @RequestBody RelUsuarioPalabraclaveDto dto) {
        return ResponseEntity.ok(relUsuarioPalabraclaveService.save(dto));
    }

    @DeleteMapping("/{idUsuario}/{idPalabraClave}")
    public ResponseEntity<Void> delete(
            @PathVariable Integer idUsuario,
            @PathVariable Integer idPalabraClave) {

        relUsuarioPalabraclaveService.deleteByUsuarioAndPalabraClave(idUsuario, idPalabraClave);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<RelUsuarioPalabraclaveDto>> getByUsuario(@PathVariable Integer idUsuario) {
        return ResponseEntity.ok(relUsuarioPalabraclaveService.findByUsuario(idUsuario));
    }

    @GetMapping("/palabra/{idPalabraClave}")
    public ResponseEntity<List<RelUsuarioPalabraclaveDto>> getByPalabraClave(@PathVariable Integer idPalabraClave) {
        return ResponseEntity.ok(relUsuarioPalabraclaveService.findByPalabraClave(idPalabraClave));
    }

    @PostMapping("/asociar/{idUsuario}/{idPalabraClave}")
    public ResponseEntity<RelUsuarioPalabraclaveDto> asociar(
            @PathVariable Integer idUsuario,
            @PathVariable Integer idPalabraClave) {
        return ResponseEntity.ok(relUsuarioPalabraclaveService.asociarUsuarioConPalabraClave(idUsuario, idPalabraClave));
    }

    @DeleteMapping("/desasociar/{idUsuario}/{idPalabraClave}")
    public ResponseEntity<Void> desasociar(
            @PathVariable Integer idUsuario,
            @PathVariable Integer idPalabraClave) {
        relUsuarioPalabraclaveService.desasociarUsuarioDePalabraClave(idUsuario, idPalabraClave);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/asociar-multiples/{idUsuario}")
    public ResponseEntity<Void> asociarMultiples(
            @PathVariable Integer idUsuario,
            @Valid @RequestBody List<Integer> palabrasClaveIds) {
        relUsuarioPalabraclaveService.asociarMultiplesPalabrasClaveAUsuario(idUsuario, palabrasClaveIds);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/desasociar-todas/{idUsuario}")
    public ResponseEntity<Void> desasociarTodas(@PathVariable Integer idUsuario) {
        relUsuarioPalabraclaveService.desasociarTodasLasPalabrasDeUsuario(idUsuario);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/existe/{idUsuario}/{idPalabraClave}")
    public ResponseEntity<Boolean> existsByUsuarioAndPalabraClave(
            @PathVariable Integer idUsuario,
            @PathVariable Integer idPalabraClave) {
        boolean exists = relUsuarioPalabraclaveService.existsByUsuarioAndPalabraClave(idUsuario, idPalabraClave);
        return ResponseEntity.ok(exists);
    }

    @GetMapping("/count/usuario/{idUsuario}")
    public ResponseEntity<Long> countByUsuario(@PathVariable Integer idUsuario) {
        long count = relUsuarioPalabraclaveService.countByUsuario(idUsuario);
        return ResponseEntity.ok(count);
    }

    @GetMapping("/count/palabra/{idPalabraClave}")
    public ResponseEntity<Long> countByPalabra(@PathVariable Integer idPalabraClave) {
        long count = relUsuarioPalabraclaveService.countByPalabra(idPalabraClave);
        return ResponseEntity.ok(count);
    }
}