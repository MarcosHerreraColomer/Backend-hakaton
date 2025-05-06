package com.hakaton.controller;

import com.hakaton.model.Usuario;
import com.hakaton.repository.UsuarioRepository;
import com.hakaton.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private JwtUtil jwtUtil;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody Map<String, String> loginRequest) {
        String dni = loginRequest.get("dni");
        String contrasena = loginRequest.get("contrasena");

        Usuario usuario = usuarioRepository.findById(dni).orElse(null); // Ahora el ID es el DNI
        Map<String, Object> response = new HashMap<>();

        if (usuario != null && passwordEncoder.matches(contrasena, usuario.getPassword())) {
            String token = jwtUtil.generateToken(dni);
            response.put("token", token);
            response.put("nombre", usuario.getNombre());
            response.put("municipio_id", usuario.getMunicipio() != null ? usuario.getMunicipio().getId() : null);
        } else {
            response.put("error", "DNI o contraseña incorrectos");
        }

        return response;
    }
}
