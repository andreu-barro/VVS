package es.udc.fic.vvs.spoticopy.contenidoTest;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import es.udc.fic.vvs.spoticopy.contenido.Cancion;
import es.udc.fic.vvs.spoticopy.contenido.Contenido;

public class CancionTest {

    Cancion cancion = new Cancion("Cancion", 15);

    @Test
    public void obtenerTituloTest() {
        assertTrue(cancion.obtenerTitulo().contentEquals("Cancion"));
    }

    @Test
    public void obtenerDuracionTest() {
        assertTrue(cancion.obtenerDuracion() == 15);
    }

    @Test
    public void obtenerListaReproduccionTest() {
        List<Contenido> canciones = new ArrayList<Contenido>();
        canciones.add(cancion);
        assertEquals(canciones, cancion.obtenerListaReproduccion());
    }

    @Test
    public void buscarTest() {
        // Valido
        List<Contenido> listaContenidos = new ArrayList<Contenido>();
        listaContenidos.add(cancion);
        assertEquals(cancion.buscar("Canc"), listaContenidos);
        
        // No valido
        assertEquals(cancion.buscar("ASFD"), new ArrayList<Contenido>());
    }
    
    @Test
    public void agregarTest() {
        Contenido cont = new Cancion("AAAA", 21);
        assertEquals(cancion.buscar("AAA"), new ArrayList<Contenido>());
        cancion.agregar(cont, cancion);
        assertEquals(cancion.buscar("AAA"), new ArrayList<Contenido>());
    }

    @Test
    public void eliminarTest() {
        List<Contenido> listaContenidos = cancion.obtenerListaReproduccion();
        cancion.eliminar(cancion);
        assertEquals(cancion.buscar("Cancion"), listaContenidos);
    }

}
