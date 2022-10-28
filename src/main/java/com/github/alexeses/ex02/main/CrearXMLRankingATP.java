package com.github.alexeses.ex02.main;

import com.github.alexeses.ex02.javabean.Entrenador;
import com.github.alexeses.ex02.javabean.Jugador;
import com.github.alexeses.ex02.javabean.NodoRaiz;
import com.thoughtworks.xstream.XStream;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;

public class CrearXMLRankingATP {

    static final String SEPARADOR_JUG = "/";
    static final String SEPARADOR_ENTRENA = "-";
    static final int POS_ENTRENA = 5;
    static NodoRaiz nr;

    public static void main(String[] args) {
       leerFicheroTxt(); // Step 01

        generarFicheroXMLXStream(); // Step 02

        leerFicheroXMLDom(); // Setp 03
    }

    private static void leerFicheroXMLDom() {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();

            Document document = db.parse(new File("src/main/resources/rankingATP.xml"));

            Node raiz = document.getFirstChild();

            gestionarNodosHijos(raiz, 0);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static String generarTabulacion(int nivel) {
        String tab = "";
        for (int i = 0; i < nivel; i++) {
            tab += "\t";
        }
        return tab;
    }

    private static void gestionarNodosHijos(Node nodo, int nivel) {
        Node nodoH;

        System.out.println(generarTabulacion(nivel) + "<" + nodo.getNodeName() + ">");
        NodeList elementosNodo = nodo.getChildNodes();

        for (int i = 0; i < elementosNodo.getLength(); i++) {
            nodoH = elementosNodo.item(i);

            if (nodoH.getNodeType() == Node.TEXT_NODE) {
                if (nodoH.getNodeValue().trim().length() > 0)
                    System.out.println(generarTabulacion(nivel + 1) + nodoH.getNodeValue());

            } else if (nodoH.getNodeType() == Node.ELEMENT_NODE) {
                gestionarNodosHijos(nodoH, nivel + 1);
            }
        }

        System.out.println(generarTabulacion(nivel) + "</" + nodo.getNodeName() + ">");
    }

    private static void generarFicheroXMLXStream() {
        try {
            XStream xstream = new XStream();

            xstream.alias("rankingATP", NodoRaiz.class);
            xstream.alias("jugador", Jugador.class);

            xstream.addImplicitCollection(NodoRaiz.class, "listaJugadores"); // Ignoramos

            xstream.toXML(nr, new FileOutputStream("src/main/resources/rankingATP.xml"));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void leerFicheroTxt() {
        nr = new NodoRaiz();

        try (BufferedReader bfr = new BufferedReader(new FileReader("src/main/resources/rankingATP.txt"))) {
            //leemos línea a línea
            String linea;
            String[] datosJug;
            String[] datosEntrena;

            while ((linea = bfr.readLine()) != null) {
                datosJug = linea.split(SEPARADOR_JUG); // el método genera un array de strings separados en /
                datosEntrena = datosJug[POS_ENTRENA].split(SEPARADOR_ENTRENA);

                nr.addJugador(
                        new Jugador(datosJug[0],
                                    datosJug[1],
                                    Integer.parseInt(datosJug[2]),
                                    Integer.parseInt(datosJug[3]),
                                    Integer.parseInt(datosJug[4]),
                        new Entrenador(datosEntrena[0],
                                datosEntrena[1],
                                Integer.parseInt(datosEntrena[2])
                        )));
            }

        } catch(IOException fnfe){
            System.out.println("Error E/S: "+fnfe);
        }


    }
}
