package com.mpaz.panol.model;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String rut;

    private String nombre;
    private String email;
    private String password;
    private String cargo;
    private String rol;

    public User() {}

    public User(String rut, String nombre, String email, String password, String cargo, String rol) {
        this.rut = rut;
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.cargo = cargo;
        this.rol = rol;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getRut() { return rut; }
    public void setRut(String rut) { this.rut = rut; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getCargo() { return cargo; }
    public void setCargo(String cargo) { this.cargo = cargo; }
    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }
}