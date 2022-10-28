package com.github.alexeses.ex01.javabean;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

public class RaizViajes implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    private final ArrayList<Viaje> listaRaiz;

    public RaizViajes() {
        listaRaiz = new ArrayList<>();
    }

    public void addViaje(Viaje viaje) {
        listaRaiz.add(viaje);
    }

    public ArrayList<Viaje> getListaRaiz() {
        return listaRaiz;
    }

    @Override
    public String toString() {
        return "RaizViajes{" +
                "listaRaiz=" + listaRaiz +
                '}';
    }
}
