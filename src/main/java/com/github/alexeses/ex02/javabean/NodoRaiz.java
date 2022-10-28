package com.github.alexeses.ex02.javabean;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

public class NodoRaiz implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private final ArrayList<Jugador> listaJugadores;

    public NodoRaiz() {
        listaJugadores = new ArrayList<Jugador>();
    }

    public void addJugador(Jugador j) {
        listaJugadores.add(j);
    }

    public ArrayList<Jugador> getListaJugadores() {
        return listaJugadores;
    }
}
