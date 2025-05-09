package com.hakaton.controller;

import com.hakaton.model.Eleccion;
import com.hakaton.model.Partido;
import com.hakaton.repository.EleccionRepository;
import com.hakaton.repository.PartidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "http://localhost:4200")
public class AdminController {

    @Autowired
    private EleccionRepository eleccionRepository;

    @Autowired
    private PartidoRepository partidoRepository;

    @PostMapping("/eleccion")
    public ResponseEntity<Eleccion> crearEleccion(@RequestBody Eleccion eleccion) {
        eleccion.setEstado("abierta");
        return ResponseEntity.ok(eleccionRepository.save(eleccion));
    }

    @PostMapping("/partido")
    public ResponseEntity<Partido> crearPartido(@RequestBody Partido partido) {
        return ResponseEntity.ok(partidoRepository.save(partido));
    }
}
