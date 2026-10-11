/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectogestortareas;
import java.time.LocalDate;

/**
 *
 * @author david
 */
public class Tareas {

    private String nombre;
    private String descripcion;
    private LocalDate fechaLimite;
    private int prioridad;
    private boolean completada;
    private Categoria categoriaAsignada;

    public Tareas(String nombre, String descripcion, LocalDate fechaLimite, int prioridad)
            throws FechaInvalidaException, PrioridadFueraDeRangoException {
        this.nombre = nombre;
        this.descripcion = descripcion;
        setFechaLimite(fechaLimite);
        setPrioridad(prioridad);
        this.completada = false;
    }

    // Constructor sin descripción
    public Tareas(String nombre, LocalDate fechaLimite, int prioridad)
            throws FechaInvalidaException, PrioridadFueraDeRangoException {
        this(nombre, "", fechaLimite, prioridad);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDate getFechaLimite() {
        return fechaLimite;
    }

    // La fecha no puede ser nula ni anterior a hoy
    public void setFechaLimite(LocalDate fechaLimite) throws FechaInvalidaException {
        if (fechaLimite == null || fechaLimite.isBefore(LocalDate.now())) {
            throw new FechaInvalidaException("La fecha límite no es válida");
        }
        this.fechaLimite = fechaLimite;
    }

    public int getPrioridad() {
        return prioridad;
    }

    // Solo se permite 1, 2 o 3
    public void setPrioridad(int p) throws PrioridadFueraDeRangoException {
        if (p != 1 && p != 2 && p != 3) {
            throw new PrioridadFueraDeRangoException("La prioridad debe ser 1, 2 o 3");
        }
        this.prioridad = p;
    }

    public boolean isCompletada() {
        return completada;
    }

    public void setCompletada(boolean completada) {
        this.completada = completada;
    }

    public Categoria getCategoriaAsignada() {
        return categoriaAsignada;
    }

    public void setCategoriaAsignada(Categoria categoriaAsignada) {
        this.categoriaAsignada = categoriaAsignada;
    }

    @Override
    public String toString() {
        return nombre + " (Prioridad: " + prioridad + ", Fecha: " + fechaLimite + ")";
    }
}

// Excepciones propias
class PrioridadFueraDeRangoException extends Exception {

    public PrioridadFueraDeRangoException(String mensaje) {
        super(mensaje);
    }
}

class FechaInvalidaException extends Exception {

    public FechaInvalidaException(String mensaje) {
        super(mensaje);
    }
}