package com.procol.procolombia.vacante.Controllers;

import com.procol.procolombia.vacante.Services.EstadoVacanteService;
import com.procol.procolombia.vacante.dto.EstadosVacanteDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/EstadosVacantes")
public class EstadoVacanteController {
    private final EstadoVacanteService estadoVacanteService;

    public EstadoVacanteController(EstadoVacanteService estadoVacanteService) {
        this.estadoVacanteService = estadoVacanteService;
    }
    @GetMapping
    public ResponseEntity<List<EstadosVacanteDto>> getAllEstadosVacante() {
        List<EstadosVacanteDto> estados = estadoVacanteService.getAllEstadosVacante();
        return ResponseEntity.ok(estados);
    }
    @GetMapping("/{idVacante}")
    public ResponseEntity<List<EstadosVacanteDto>> getEstadosByVacanteId(@PathVariable Integer idVacante) {
        List<EstadosVacanteDto> estados = estadoVacanteService.getAllEstadosVacanteById(idVacante);
        return ResponseEntity.ok(estados);
    }
    @PutMapping
    public ResponseEntity<EstadosVacanteDto> updateEstadoVacante(@RequestBody EstadosVacanteDto dto) {
        EstadosVacanteDto updated = estadoVacanteService.updateEstadoVacante(dto);
        return ResponseEntity.ok(updated);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEstadoVacante(@PathVariable Integer id) {
        estadoVacanteService.deleteByEstadoVacanteId(id);
        return ResponseEntity.noContent().build();
    }
}
