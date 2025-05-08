package com.hakaton.controller;

import com.hakaton.model.Municipio;
import com.hakaton.model.Partido;
import com.hakaton.model.Usuario;
import com.hakaton.repository.PartidoRepository;
import com.hakaton.repository.UsuarioRepository;
import com.hakaton.security.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    // Partidos municipales
    @GetMapping("/municipales")
    public ResponseEntity<List<Partido>> obtenerPartidosMunicipalesPorToken(HttpServletRequest request) {
        try {
            String token = request.getHeader("Authorization").replace("Bearer ", "");
            String dni = jwtUtil.getDniFromToken(token);
            Usuario usuario = usuarioRepository.findByDni(dni);
            String codigoAmbito = String.valueOf(usuario.getMunicipio().getId());

            List<Partido> partidos = partidoRepository.findByAmbitoAndCodigoAmbito("municipal", codigoAmbito);
            return ResponseEntity.ok(partidos);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    @GetMapping("/autonomicos")
public ResponseEntity<List<Partido>> obtenerPartidosAutonomicos(HttpServletRequest request) {
    try {
        // 1. Obtener el DNI del token
        String token = jwtUtil.getTokenFromRequest(request);
        String dni = jwtUtil.extraerDniDesdeToken(token);

        // 2. Buscar al usuario por su DNI
        Usuario usuario = usuarioRepository.findByDni(dni);
        if (usuario == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(null);
        }

        // 3. Obtener el municipio del usuario (relación @ManyToOne)
        Municipio municipio = usuario.getMunicipio();
        if (municipio == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(null);
        }

        // 4. Extraer el comunidad_id y convertirlo a String
        String codigoAmbito = String.valueOf(municipio.getComunidad_id());
        System.out.println("📦 Código ámbito autonómico cargado: " + codigoAmbito);

        // 5. Buscar partidos por ámbito "autonomica" y códigoAmbito = comunidad_id
        List<Partido> partidos = partidoRepository.findByAmbitoAndCodigoAmbitoOrderByNombreAsc("autonomica", codigoAmbito);
        return ResponseEntity.ok(partidos);

    } catch (Exception e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(null);
    }
}



    // Partidos nacionales (todos usan codigo_ambito = '0')
    @GetMapping("/nacionales")
    public ResponseEntity<List<Partido>> obtenerPartidosNacionales() {
        try {
            List<Partido> partidos = partidoRepository.findByAmbitoAndCodigoAmbito("nacional", "0");
            return ResponseEntity.ok(partidos);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }
    
}
