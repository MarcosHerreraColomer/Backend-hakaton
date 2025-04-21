package com.hakaton.controller;

import com.hakaton.model.Usuario;
import com.hakaton.model.Municipio;
import com.hakaton.repository.UsuarioRepository;
import com.hakaton.repository.MunicipioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.SecureRandom;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "http://localhost:4200")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private MunicipioRepository municipioRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final Random random = new SecureRandom();

    @PostMapping
    public Usuario registrarUsuario(@RequestBody Usuario usuario) {
        // 📥 Mostrar datos recibidos
        System.out.println("📥 JSON recibido:");
        System.out.println("DNI: " + usuario.getDni());
        System.out.println("Nombre: " + usuario.getNombre());
        System.out.println("CP: " + usuario.getCp());
        System.out.println("Municipio ID: " + usuario.getMunicipio_id());

        // 🔐 Generar y encriptar contraseña
        String contrasena = generarPasswordAleatoria();
        usuario.setContrasena(passwordEncoder.encode(contrasena));

        // 🏙️ Buscar municipio por ID recibido desde Angular
        if (usuario.getMunicipio_id() != null) {
            Municipio municipio = municipioRepository.findById(usuario.getMunicipio_id()).orElse(null);
            usuario.setMunicipio(municipio);
            System.out.println("✅ Municipio asignado: " + (municipio != null ? municipio.getNombre() : "null"));
        } else {
            System.out.println("❌ municipio_id es null, no se puede asignar municipio.");
        }

        // 💾 Guardar en BBDD
        Usuario guardado = usuarioRepository.save(usuario);

        // Enviar contraseña visible para mostrarla en el frontend
        guardado.setContrasena(contrasena);
        return guardado;
    }

    @GetMapping
    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
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
