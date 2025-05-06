package com.hakaton.controller;

import com.hakaton.model.Usuario;
import com.hakaton.model.Municipio;
import com.hakaton.repository.UsuarioRepository;
import com.hakaton.repository.MunicipioRepository;
import com.hakaton.Service.EmailService;
import com.hakaton.security.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.SecureRandom;
import java.util.*;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "http://localhost:4200")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private MunicipioRepository municipioRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private JwtUtil jwtUtil;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final Random random = new SecureRandom();

    @PostMapping
    public Usuario registrarUsuario(@RequestBody Usuario usuario) {
        System.out.println("📥 JSON recibido:");
        System.out.println("DNI: " + usuario.getDni());
        System.out.println("Nombre: " + usuario.getNombre());
        System.out.println("Municipio ID: " + usuario.getMunicipio_id());

        String contrasena = generarPasswordAleatoria();
        usuario.setPassword(passwordEncoder.encode(contrasena));

        if (usuario.getMunicipio_id() != null) {
            Municipio municipio = municipioRepository.findById(usuario.getMunicipio_id()).orElse(null);
            usuario.setMunicipio(municipio);
            System.out.println("✅ Municipio asignado: " + (municipio != null ? municipio.getNombre() : "null"));
        }

        usuario.setRol("votante");
        usuario.setHa_votado_municipal(false);
        usuario.setHa_votado_autonomica(false);
        usuario.setHa_votado_nacional(false);

        Usuario guardado = usuarioRepository.save(usuario);

        try {
            emailService.enviarCredenciales(usuario.getEmail(), contrasena);
            System.out.println("📧 Correo enviado a " + usuario.getEmail());
        } catch (Exception e) {
            System.out.println("⚠️ Error al enviar correo: " + e.getMessage());
        }

        guardado.setPassword(contrasena);
        return guardado;
    }

    @GetMapping
    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    @GetMapping("/municipio-id")
    public ResponseEntity<?> obtenerMunicipioDesdeToken(@RequestHeader("Authorization") String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token no proporcionado o inválido");
        }

        String token = authHeader.replace("Bearer ", "");
        String dni = jwtUtil.getDniFromToken(token);

        Usuario usuario = usuarioRepository.findByDni(dni);
        if (usuario == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
        }

        return ResponseEntity.ok(usuario.getMunicipio().getId());
    }

    private String generarPasswordAleatoria() {
        String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            sb.append(caracteres.charAt(random.nextInt(caracteres.length())));
        }
        return sb.toString();
    }
}
