package com.github.alexeses.ex02.javabean;

import java.io.Serial;
import java.io.Serializable;

public class Jugador implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    // Declare variables
    private String nombre;
    private String naciondalidad;
    private int edad;
    private int posicion;
    private int puntos;
    private Entrenador entrenador;

    // Constructor
    public Jugador(String nombre, String naciondalidad, int edad, int posicion, int puntos, Entrenador entrenador) {
        this.nombre = nombre;
        this.naciondalidad = naciondalidad;
        this.edad = edad;
        this.posicion = posicion;
        this.puntos = puntos;
        this.entrenador = entrenador;
    }

    // Getters
    public String getNombre() {
        return nombre;
    }
    public String getNaciondalidad() {
        return naciondalidad;
    }
    public int getEdad() {
        return edad;
    }
    public int getPosicion() {
        return posicion;
    }
    public int getPuntos() {
        return puntos;
    }
    public Entrenador getEntrenador() {
        return entrenador;
    }
}
