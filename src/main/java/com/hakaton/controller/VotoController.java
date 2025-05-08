package com.hakaton.controller;

import com.hakaton.model.Voto;
import com.hakaton.repository.VotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/votos")
@CrossOrigin(origins = "http://localhost:4200")
public class VotoController {

    @Autowired
    private VotoRepository votoRepository;

    @PostMapping
    public ResponseEntity<?> registrarVoto(@RequestBody Voto voto) {
        try {
            System.out.println("📥 Datos recibidos: " + voto);

            if (voto.getPartido_id() == null || voto.getEleccion_id() == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Error: partido_id y eleccion_id son obligatorios");
            }

            voto.setFecha_voto(LocalDateTime.now());
            Voto saved = votoRepository.save(voto);

            return ResponseEntity.ok(saved);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("❌ Error al registrar el voto: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> listarVotos() {
        return ResponseEntity.ok(votoRepository.findAll());
    }
}
