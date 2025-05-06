package com.hakaton.model;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import jakarta.persistence.Table;




@Entity
@Table(name = "usuarios")

public class Usuario {

    @Id
    private String dni;

    private String nombre;
    private String email;
    private String password;

    private String cp;

    private String rol = "votante"; // Valor por defecto

    @ManyToOne
    @JoinColumn(name = "municipio_id")
    private Municipio municipio;

    @Transient
    private Integer municipio_id;

    private Boolean ha_votado_municipal = false;
    private Boolean ha_votado_autonomica = false;
    private Boolean ha_votado_nacional = false;

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

public String getEmail() {
    return email;
}

public void setEmail(String email) {
    this.email = email;
}

public String getPassword() {
    return password;
}

public void setPassword(String password) {
    this.password = password;
}

public String getCp() {
    return cp;
}

public void setCp(String cp) {
    this.cp = cp;
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

public Integer getMunicipio_id() {
    return municipio_id;
}

public void setMunicipio_id(Integer municipio_id) {
    this.municipio_id = municipio_id;
}

public Boolean getHa_votado_municipal() {
    return ha_votado_municipal;
}

public void setHa_votado_municipal(Boolean ha_votado_municipal) {
    this.ha_votado_municipal = ha_votado_municipal;
}

public Boolean getHa_votado_autonomica() {
    return ha_votado_autonomica;
}

public void setHa_votado_autonomica(Boolean ha_votado_autonomica) {
    this.ha_votado_autonomica = ha_votado_autonomica;
}

public Boolean getHa_votado_nacional() {
    return ha_votado_nacional;
}

public void setHa_votado_nacional(Boolean ha_votado_nacional) {
    this.ha_votado_nacional = ha_votado_nacional;
}

}
