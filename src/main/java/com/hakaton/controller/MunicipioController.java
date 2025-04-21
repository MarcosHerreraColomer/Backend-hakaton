package com.hakaton.controller;

import com.hakaton.model.Municipio;
import com.hakaton.repository.MunicipioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/municipios")
public class MunicipioController {

    @Autowired
    private MunicipioRepository municipioRepository;

    @GetMapping
    public List<Municipio> obtenerMunicipios() {
        return municipioRepository.findAll();
    }
}
