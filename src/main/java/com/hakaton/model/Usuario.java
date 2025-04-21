package com.hakaton.model;

import jakarta.persistence.*;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String dni;
    private String nombre;
    private String cp;
    private String contrasena;
    private String rol = "votante"; // Por defecto

    @ManyToOne
    @JoinColumn(name = "municipio_id")
    private Municipio municipio;

    @Transient // Este campo se usa solo para recibir el ID desde Angular
    private Long municipio_id;

    public Usuario() {}

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public Municipio getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }

    public Long getMunicipio_id() {
        return municipio_id;
    }

    public void setMunicipio_id(Long municipio_id) {
        this.municipio_id = municipio_id;
    }
}
