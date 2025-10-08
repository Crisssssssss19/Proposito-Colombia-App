package com.procol.procolombia.perfil.controllers;

import com.procol.procolombia.perfil.dtos.request.SaveTalento;
import com.procol.procolombia.perfil.dtos.response.ApiResponse;
import com.procol.procolombia.perfil.dtos.response.GetTalento;
import com.procol.procolombia.perfil.services.TalentoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/talentos")
public class TalentoController {

    private final TalentoService talentoService;

    public TalentoController(TalentoService talentoService) {
        this.talentoService = talentoService;
    }

    @PostMapping("/crear")
    public ResponseEntity<ApiResponse<GetTalento>> crearTalento(@Valid @RequestBody SaveTalento saveTalento) {
        GetTalento talentoCreado = talentoService.crearTalento(saveTalento);
        return ResponseEntity.ok(ApiResponse.success("Talento creado correctamente", talentoCreado, HttpStatus.CREATED));
    }

    @GetMapping("/listar")
    public ResponseEntity<ApiResponse<List<GetTalento>>> getTalentos() {
        return ResponseEntity.ok(ApiResponse.success("Lista de talentos obtenida correctamente", talentoService.ListarTalentos(), HttpStatus.OK));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<GetTalento>> getTalentoPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(ApiResponse.success("Talento obtenido correctamente", talentoService.obtenerTalentoPorId(id), HttpStatus.OK));
    }

    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<ApiResponse<List<GetTalento>>> getTalentosPorTipo(@PathVariable Short tipo) {
        return ResponseEntity.ok(ApiResponse.success("Lista de talentos por tipo obtenida correctamente", talentoService.ListarTalentosPorTipo(tipo), HttpStatus.OK));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<GetTalento>> actualizarTalento(@PathVariable Integer id, @Valid @RequestBody SaveTalento saveTalento) {
        return ResponseEntity.ok(ApiResponse.success("Talento actualizado correctamente", talentoService.actualizarTalento(id, saveTalento), HttpStatus.OK));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminarTalento(@PathVariable Integer id) {
        talentoService.eliminarTalento(id);
        return ResponseEntity.ok(ApiResponse.success("Talento eliminado correctamente", null, HttpStatus.OK));
    }
}
