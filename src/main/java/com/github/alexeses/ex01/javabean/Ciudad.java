package com.github.alexeses.ex01.javabean;

import java.io.Serializable;

public class Ciudad implements Serializable {

    private final long serialVersionUID = 1L;

    private String codigo;
    private String nombreCiudad;
    private String pais;

    public Ciudad(String codigo, String nombreCiudad, String pais) {
        this.codigo = codigo;
        this.nombreCiudad = nombreCiudad;
        this.pais = pais;
    }

    public long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombreCiudad() {
        return nombreCiudad;
    }

    public String getPais() {
        return pais;
    }

    @Override
    public String toString() {
        return "Ciudad{" +
                "serialVersionUID=" + serialVersionUID +
                ", codigo='" + codigo + '\'' +
                ", nombreCiudad='" + nombreCiudad + '\'' +
                ", pais='" + pais + '\'' +
                '}';
    }
}


