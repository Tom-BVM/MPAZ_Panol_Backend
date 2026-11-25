package com.mpaz.panol.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "reservas")
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String rutSolicitante;
    private String nombreSolicitante;
    private Long itemId;
    private String itemNombre;
    private int cantidad;
    private String salaUso;
    private LocalDateTime fechaHoraInicio;
    private LocalDateTime fechaHoraFin;
    private String estado;
    private String comentarioGestion;
    private LocalDateTime fechaCreacion = LocalDateTime.now();

    public Reserva() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getRutSolicitante() { return rutSolicitante; }
    public void setRutSolicitante(String rutSolicitante) { this.rutSolicitante = rutSolicitante; }
    public String getNombreSolicitante() { return nombreSolicitante; }
    public void setNombreSolicitante(String nombreSolicitante) { this.nombreSolicitante = nombreSolicitante; }
    public Long getItemId() { return itemId; }
    public void setItemId(Long itemId) { this.itemId = itemId; }
    public String getItemNombre() { return itemNombre; }
    public void setItemNombre(String itemNombre) { this.itemNombre = itemNombre; }
    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }
    public String getSalaUso() { return salaUso; }
    public void setSalaUso(String salaUso) { this.salaUso = salaUso; }
    public LocalDateTime getFechaHoraInicio() { return fechaHoraInicio; }
    public void setFechaHoraInicio(LocalDateTime fechaHoraInicio) { this.fechaHoraInicio = fechaHoraInicio; }
    public LocalDateTime getFechaHoraFin() { return fechaHoraFin; }
    public void setFechaHoraFin(LocalDateTime fechaHoraFin) { this.fechaHoraFin = fechaHoraFin; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public String getComentarioGestion() { return comentarioGestion; }
    public void setComentarioGestion(String comentarioGestion) { this.comentarioGestion = comentarioGestion; }
    public LocalDateTime getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(LocalDateTime fechaCreacion) { this.fechaCreacion = fechaCreacion; }
}