package com.github.alexeses.ex01.main;

import com.github.alexeses.ex01.javabean.Ciudad;
import com.github.alexeses.ex01.javabean.RaizViajes;
import com.github.alexeses.ex01.javabean.Viaje;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.NoTypePermission;
import com.thoughtworks.xstream.security.NullPermission;
import com.thoughtworks.xstream.security.PrimitiveTypePermission;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class CrearXMLViajes {

    static ArrayList<Viaje> listaViajes;

    public static void main(String[] args) {
        inicializarLista(); // Paso 01

        try {
            genrarFicheroXMLDOM(); // Paso 02
        } catch (ParserConfigurationException | TransformerException e) {
            throw new RuntimeException(e);
        }

        leerFicheroXMLXStream(); // Paso 03
    }

    private static void leerFicheroXMLXStream() {
        // LECTURA XSTREAM
        try (FileInputStream fis = new FileInputStream("src/main/resources/empleados.xml");) {
            XStream xs = new XStream();

            xs.addPermission(NoTypePermission.NONE);
            xs.addPermission(NullPermission.NULL);
            xs.addPermission(PrimitiveTypePermission.PRIMITIVES);
                                // Raiz, Vijae y que contiene Viajes, Ciudad
            Class[] clases = {RaizViajes.class, Viaje.class, Ciudad.class};
            xs.allowTypes(clases);

            xs.allowTypesByWildcard(new String[] {"com.github.alexeses.ex01.javabean.*"});

            xs.alias("agencia", RaizViajes.class); // nombre nodo raiz
            xs.alias("viaje",Viaje.class);

            xs.addImplicitCollection(RaizViajes.class, "listaRaiz");

            RaizViajes nodoRaiz = (RaizViajes) xs.fromXML(fis);

            for (Viaje v : nodoRaiz.getListaRaiz()) {
                System.out.println(v.toString());
                //System.out.println(v.getCiudad().toString());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void genrarFicheroXMLDOM() throws ParserConfigurationException, TransformerException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        DOMImplementation implementation = builder.getDOMImplementation();

        Document document = implementation.createDocument(null, "agencia", null);
        document.setXmlVersion("1.0");

        generarEstructura(document);

        Source source = new DOMSource(document);
        Result result = new StreamResult(new File("src/main/resources/empleados.xml"));

        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.METHOD, "xml");
        transformer.setOutputProperty("{https://xml.apache.org/xslt}indent-amount", "4");

        transformer.transform(source,  result);
    }

    private static void generarEstructura(Document document) {
        Element elemento;
        Element elementoHijo;
        for (Viaje v : listaViajes) {
            elemento = document.createElement("viaje");
            document.getDocumentElement().appendChild(elemento);
            generarElementoFinal(document, elemento, "id", String.valueOf(v.getId()));
            generarElementoFinal(document, elemento, "nombre", v.getNombre());

            elementoHijo = document.createElement("ciudad");
            elemento.appendChild(elementoHijo); // Lo añadimos al elemento principal
            generarElementoFinal(document, elementoHijo, "codigo", v.getCiudad().getCodigo());
            generarElementoFinal(document, elementoHijo, "nombreCiudad", v.getCiudad().getNombreCiudad());
            generarElementoFinal(document, elementoHijo, "pais", v.getCiudad().getPais());

            generarElementoFinal(document, elemento, "dias", String.valueOf(v.getDias()));
            generarElementoFinal(document, elemento, "precio", String.valueOf(v.getPrecio()));
        }
    }

    private static void generarElementoFinal(Document document, Element elemento,
                                             String nomEtiqueta, String contenido) {
        Element elementoF = document.createElement(nomEtiqueta);
        Text texto = document.createTextNode(contenido);
        elementoF.appendChild(texto);
        elemento.appendChild(elementoF);
    }

    private static void inicializarLista() {
        listaViajes = new ArrayList<>();

        listaViajes.add(new Viaje(1, "Semana Santa en Revira Maya",
                new Ciudad("PCN", "Playa del Carmen", "México"), 7, 845));
        listaViajes.add(new Viaje(2, "Semana Santa en Cancún",
                new Ciudad("CUN", "Cancún", "México"), 7, 845));
        listaViajes.add(new Viaje(3, "Semana Santa en Riviera Maya",
                new Ciudad("CUN", "Cancún", "México"), 7, 845));
        listaViajes.add(new Viaje(4, "Semana Santa en Riviera Maya",
                new Ciudad("CUN", "Cancún", "México"), 7, 845));
        listaViajes.add(new Viaje(5, "Semana Santa en Riviera Maya",
                new Ciudad("CUN", "Cancún", "México"), 7, 845));
    }

}
