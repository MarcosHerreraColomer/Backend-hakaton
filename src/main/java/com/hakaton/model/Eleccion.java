package com.hakaton.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "elecciones")
public class Eleccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String tipo;
    private LocalDateTime fecha_inicio;
    private LocalDateTime fecha_fin;
    private String estado;
    private String codigo_ambito;

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public LocalDateTime getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(LocalDateTime fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public LocalDateTime getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(LocalDateTime fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCodigo_ambito() {
        return codigo_ambito;
    }

    public void setCodigo_ambito(String codigo_ambito) {
        this.codigo_ambito = codigo_ambito;
    }

    @Override
    public String toString() {
        return "Eleccion{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", tipo='" + tipo + '\'' +
                ", fecha_inicio=" + fecha_inicio +
                ", fecha_fin=" + fecha_fin +
                ", estado='" + estado + '\'' +
                ", codigo_ambito='" + codigo_ambito + '\'' +
                '}';
    }
}