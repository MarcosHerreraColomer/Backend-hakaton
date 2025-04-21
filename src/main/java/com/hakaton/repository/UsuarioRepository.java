package com.hakaton.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hakaton.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // Método para buscar usuario por su DNI
    Usuario findByDni(String dni);
}
