package es.udc.fic.vvs.spoticopy.contenidoTest;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import es.udc.fic.vvs.spoticopy.contenido.Anuncio;
import es.udc.fic.vvs.spoticopy.contenido.Contenido;

public class AnuncioTest {

    Anuncio anuncio = new Anuncio();

    @Test
    public void obtenerTituloTest() {
        assertTrue(anuncio.obtenerTitulo().contentEquals("PUBLICIDAD"));
    }

    @Test
    public void obtenerDuracionTest() {
        assertTrue(anuncio.obtenerDuracion() == 5);
    }

    @Test
    public void obtenerListaReproduccionTest() {
        List<Contenido> anuncios = new ArrayList<Contenido>();
        anuncios.add(anuncio);
        assertEquals(anuncios, anuncio.obtenerListaReproduccion());
    }
    
    @Test
    public void buscarTest() {
        // Valido
        List<Contenido> listaContenidos = new ArrayList<Contenido>();
        listaContenidos.add(anuncio);
        assertEquals(anuncio.buscar("PUBLI"), listaContenidos);
        
        // No valido
        assertEquals(anuncio.buscar("ASFD"), new ArrayList<Contenido>());
    }

    @Test
    public void agregarTest() {
        Contenido cont = new Anuncio();
        List<Contenido> listaContenidos = new ArrayList<Contenido>();
        listaContenidos.add(anuncio);
        assertEquals(anuncio.buscar("PUBLI"), listaContenidos);
        anuncio.agregar(cont, anuncio);
        assertEquals(anuncio.buscar("PUBLI"), listaContenidos);
    }

    @Test
    public void eliminarTest() {
        List<Contenido> listaContenidos = anuncio.obtenerListaReproduccion();
        anuncio.eliminar(anuncio);
        assertEquals(anuncio.buscar("PUBLI"), listaContenidos);
    }
    
    @Test
    public void equalsTest() {
        assertTrue(anuncio.equals(new Anuncio()));
    }
    
    @Test
    public void hashCodeTest() {
        assertEquals(anuncio.hashCode(), (new Anuncio()).hashCode());
    }
}
