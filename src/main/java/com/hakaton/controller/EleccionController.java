package com.hakaton.controller;

import com.hakaton.model.Eleccion;
import com.hakaton.repository.EleccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/elecciones")
@CrossOrigin(origins = "http://localhost:4200")
public class EleccionController {

    @Autowired
    private EleccionRepository eleccionRepository;

    @GetMapping("/{tipo}")
    public ResponseEntity<List<Eleccion>> getEleccionesPorTipo(@PathVariable String tipo) {
        List<Eleccion> elecciones = eleccionRepository.findByTipoAndEstado(tipo, "abierta");
        if (elecciones.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(elecciones);
    }
}
