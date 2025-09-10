package com.procol.procolombia.vacante.Controllers;

import com.procol.procolombia.vacante.Services.HistorialEstadoVacanteService;
import com.procol.procolombia.vacante.entities.HistorialEstadoVacante;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/historialEstadosVacantes")
public class HistorialEstadoVacanteController {
    private final HistorialEstadoVacanteService historialEstadoVacanteService;

    public HistorialEstadoVacanteController(HistorialEstadoVacanteService historialEstadoVacanteService) {
        this.historialEstadoVacanteService = historialEstadoVacanteService;
    }

    @GetMapping("/vacante/{vacanteId}/ultimo")
    public ResponseEntity<HistorialEstadoVacante> getUltimoEstadoByVacanteId(@PathVariable Integer vacanteId) {
        HistorialEstadoVacante historial = historialEstadoVacanteService.findEstadoByVacanteId(vacanteId);
        return ResponseEntity.ok(historial);
    }

    @GetMapping("/vacante/{vacanteId}")
    public ResponseEntity<List<HistorialEstadoVacante>> getEstadosByVacanteId(@PathVariable Integer vacanteId) {
        List<HistorialEstadoVacante> historial = historialEstadoVacanteService.findAllEstadosByVacanteId(vacanteId);
        return ResponseEntity.ok(historial);
    }

    @GetMapping("/estado/{estadoId}")
    public ResponseEntity<List<HistorialEstadoVacante>> getVacantesByEstadoId(@PathVariable Integer estadoId) {
        List<HistorialEstadoVacante> vacantes = historialEstadoVacanteService.findAllVacantesByEstadoId(estadoId);
        return ResponseEntity.ok(vacantes);
    }
}
