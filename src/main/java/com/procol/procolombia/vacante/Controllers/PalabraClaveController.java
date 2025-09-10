package com.procol.procolombia.vacante.Controllers;

import com.procol.procolombia.vacante.Services.PalabraClaveService;
import com.procol.procolombia.vacante.dto.PalabrasClaveDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/palabrasClave")
public class PalabraClaveController {
    private final PalabraClaveService palabraClaveService;

    public PalabraClaveController(PalabraClaveService palabraClaveService) {
        this.palabraClaveService = palabraClaveService;
    }

    @GetMapping
    public ResponseEntity<List<PalabrasClaveDto>> getAllPalabrasClaves() {
        return ResponseEntity.ok(palabraClaveService.findAllPalabrasClaves());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PalabrasClaveDto> getPalabraClaveById(@PathVariable Integer id) {
        PalabrasClaveDto dto = palabraClaveService.findPalabraClaveById(id);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePalabraClave(@PathVariable Integer id) {
        palabraClaveService.deletePalabraClave(id);
        return ResponseEntity.noContent().build();
    }
}
