package com.hakaton.controller;

import com.hakaton.model.Partido;
import com.hakaton.model.Usuario;
import com.hakaton.repository.PartidoRepository;
import com.hakaton.repository.UsuarioRepository;
import com.hakaton.security.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/partidos")
@CrossOrigin(origins = "http://localhost:4200")
public class PartidoController {

    @Autowired
    private PartidoRepository partidoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("/municipales")
    public List<Partido> obtenerMunicipales(@RequestHeader("Authorization") String authHeader) {
        String dni = extraerDniDesdeToken(authHeader);
        Usuario usuario = usuarioRepository.findByDni(dni);
        String codMunicipio = String.valueOf(usuario.getMunicipio().getId());
        return partidoRepository.findByAmbitoAndCodigoAmbito("municipal", codMunicipio);
    }

    @GetMapping("/autonomicos")
    public List<Partido> obtenerAutonomicos(@RequestHeader("Authorization") String authHeader) {
        String dni = extraerDniDesdeToken(authHeader);
        Usuario usuario = usuarioRepository.findByDni(dni);
        String codComunidad = String.valueOf(usuario.getMunicipio().getComunidad_id());
        return partidoRepository.findByAmbitoAndCodigoAmbito("autonomico", codComunidad);
    }

    @GetMapping("/nacionales")
    public List<Partido> obtenerNacionales() {
        return partidoRepository.findByAmbito("nacional");
    }

    private String extraerDniDesdeToken(String authHeader) {
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            return jwtUtil.getDniFromToken(token);
        }
        return null;
    }
}
