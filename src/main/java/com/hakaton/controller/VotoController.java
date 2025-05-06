package com.hakaton.controller;

import com.hakaton.model.Voto;
import com.hakaton.repository.VotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/votos")
@CrossOrigin(origins = "http://localhost:4200")
public class VotoController {

    @Autowired
    private VotoRepository votoRepository;

    @PostMapping
    public Voto registrarVoto(@RequestBody Voto voto) {
        voto.setFecha_voto(LocalDateTime.now());
        return votoRepository.save(voto);
    }

    @GetMapping
    public List<Voto> listarVotos() {
        return votoRepository.findAll();
    }
}
