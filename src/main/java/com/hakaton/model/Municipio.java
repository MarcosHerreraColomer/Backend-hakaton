package com.hakaton.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "municipios")
public class Municipio {

    @Id
    private Integer id; // ID del municipio (int)

    private String nombre;
    private String provincia;
    private String comunidad_autonoma;
    private Integer comunidad_id;

    // Getters y setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getComunidad_autonoma() {
        return comunidad_autonoma;
    }

    public void setComunidad_autonoma(String comunidad_autonoma) {
        this.comunidad_autonoma = comunidad_autonoma;
    }

    public Integer getComunidad_id() {
        return comunidad_id;
    }

    public void setComunidad_id(Integer comunidad_id) {
        this.comunidad_id = comunidad_id;
    }
}
