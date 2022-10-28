package com.github.alexeses.ex02.javabean;

import java.io.Serial;
import java.io.Serializable;

public class Entrenador implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private String nomEntrenador;
    private String nacEntrenador;
    private int edad;

    public Entrenador(String nomEntrenador, String nacEntrenador, int edad) {
        this.nomEntrenador = nomEntrenador;
        this.nacEntrenador = nacEntrenador;
        this.edad = edad;
    }

    public String getNomEntrenador() {
        return nomEntrenador;
    }

    public String getNacEntrenador() {
        return nacEntrenador;
    }

    public int getEdad() {
        return edad;
    }
}
