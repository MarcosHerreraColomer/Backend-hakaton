package com.hakaton.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "votos")
public class Voto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long partido_id;
    private Long eleccion_id;

    // Solo usamos este campo para almacenar fecha y hora exacta del voto
    private LocalDateTime fecha_voto;

    private String tipo;

    // Getters y Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPartido_id() {
        return partido_id;
    }

    public void setPartido_id(Long partido_id) {
        this.partido_id = partido_id;
    }

    public Long getEleccion_id() {
        return eleccion_id;
    }

    public void setEleccion_id(Long eleccion_id) {
        this.eleccion_id = eleccion_id;
    }

    public LocalDateTime getFecha_voto() {
        return fecha_voto;
    }

    public void setFecha_voto(LocalDateTime fecha_voto) {
        this.fecha_voto = fecha_voto;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
