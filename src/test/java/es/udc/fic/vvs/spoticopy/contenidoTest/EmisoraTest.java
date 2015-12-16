package es.udc.fic.vvs.spoticopy.contenidoTest;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import es.udc.fic.vvs.spoticopy.contenido.Cancion;
import es.udc.fic.vvs.spoticopy.contenido.Emisora;
import es.udc.fic.vvs.spoticopy.contenido.Contenido;

public class EmisoraTest {

    @Test
    public void obtenerTituloTest() {
        Emisora emisora = new Emisora("Emisora", null);
        assertTrue(emisora.obtenerTitulo().equals("Emisora"));
    }

    @Test
    public void obtenerDuracionTest() {
        // Empieza vacio
        Emisora emisora = new Emisora("Emisora", null);
        assertTrue(emisora.obtenerDuracion() == 0);
        
        // Con otros contenidos, la duracion es su suma
        List<Contenido> contenidos = new ArrayList<Contenido>();
        contenidos.add(new Cancion("Cancion1", 10));
        Contenido emisoraUnaCancion
                = new Emisora("EmisoraUnaCancion", contenidos);
        assertTrue(emisoraUnaCancion.obtenerDuracion() == 10);
        contenidos.add(new Cancion("Cancion2", 25));
        Contenido emisoraDosCanciones
                = new Emisora("EmisoraDosCanciones", contenidos);
        assertTrue(emisoraDosCanciones.obtenerDuracion() == 35);
        
    }

    @Test
    public void buscarTest() {
        // Valido
        Emisora emisora = new Emisora("Emisora", null);
        List<Contenido> listaContenidos = new ArrayList<Contenido>();
        listaContenidos.add(emisora);
        assertEquals(emisora.buscar("Emis"), listaContenidos);
        // No valido
        assertEquals(emisora.buscar("AAA"), new ArrayList<Contenido>());
    }

    @Test
    public void obtenerListaReproduccionTest() {
        List<Contenido> lista = new ArrayList<Contenido>();
        Cancion cancion = new Cancion("Cancion", 5);
        Cancion cancion2 = new Cancion("Cancion2", 14);
        lista.add(cancion);
        lista.add(cancion2);
        Emisora emisora = new Emisora("Emisora", lista);
        assertEquals(emisora.obtenerListaReproduccion(), lista);
    }

    @Test
    public void eliminarTest() {
        // Creamos una emisora con varios contenidos
        List<Contenido> lista = new ArrayList<Contenido>();
        Cancion cancion1 = new Cancion("A", 5);
        Cancion cancion2 = new Cancion("B", 3);
        Cancion cancion3 = new Cancion("C", 7);
        lista.add(cancion1);
        lista.add(cancion2);
        lista.add(cancion3);
        Emisora emisora = new Emisora("Emisora", lista);
        
        // Comprobacion inicial
        assertEquals(emisora.obtenerListaReproduccion().size(), 3);
        assertTrue(emisora.obtenerDuracion() == 15);
        assertEquals(emisora.obtenerListaReproduccion(), lista);
        
        // Eliminamos uno
        emisora.eliminar(cancion2);
        assertEquals(emisora.obtenerListaReproduccion().size(), 2);
        assertEquals(emisora.obtenerDuracion(), 12);
        assertTrue(emisora.obtenerListaReproduccion().contains(cancion1));
        assertFalse(emisora.obtenerListaReproduccion().contains(cancion2));
        assertTrue(emisora.obtenerListaReproduccion().contains(cancion3));
        
        // Eliminamos uno que NO existe
        Cancion cancion4 = new Cancion("D", 15);
        emisora.eliminar(cancion4);
        assertEquals(emisora.obtenerListaReproduccion().size(), 2);
        assertEquals(emisora.obtenerDuracion(), 12);
        assertFalse(emisora.obtenerListaReproduccion().contains(cancion4));
        
        // Eliminamos un NULL
        emisora.eliminar(null);
        assertEquals(emisora.obtenerListaReproduccion().size(), 2);
        assertEquals(emisora.obtenerDuracion(), 12);
    }

    @Test
    public void agregarTest() {
        // Creamos una emisora con varios contenidos
        List<Contenido> lista = new ArrayList<Contenido>();
        Cancion cancion1 = new Cancion("A", 5);
        lista.add(cancion1);
        Emisora emisora = new Emisora("Emisora", lista);
        
        // Comprobacion inicial
        assertEquals(emisora.obtenerDuracion(), 5);
        assertEquals(emisora.obtenerListaReproduccion(), lista);
        
        // Insertamos uno al final
        Cancion cancion2 = new Cancion("B", 3);
        emisora.agregar(cancion2, cancion1);
        assertEquals(emisora.obtenerDuracion(), 8);
        assertEquals(emisora.obtenerListaReproduccion().size(), 2);
        assertEquals(emisora.obtenerListaReproduccion().get(0), cancion1);
        assertEquals(emisora.obtenerListaReproduccion().get(1), cancion2);
        
        // Insertamos otro en el medio
        Cancion cancion3 = new Cancion("C", 7);
        emisora.agregar(cancion3, cancion1);
        assertEquals(emisora.obtenerDuracion(), 15);
        assertTrue(emisora.obtenerListaReproduccion().contains(cancion3));
        assertEquals(emisora.obtenerListaReproduccion().size(), 3);
        assertEquals(emisora.obtenerListaReproduccion().get(0), cancion1);
        assertEquals(emisora.obtenerListaReproduccion().get(1), cancion3);
        assertEquals(emisora.obtenerListaReproduccion().get(2), cancion2);
        
        // Insertamos otro al inicio
        Cancion cancion4 = new Cancion("D", 1);
        emisora.agregar(cancion4, null);
        assertEquals(emisora.obtenerDuracion(), 16);
        assertTrue(emisora.obtenerListaReproduccion().contains(cancion4));
        assertEquals(emisora.obtenerListaReproduccion().size(), 4);
        assertEquals(emisora.obtenerListaReproduccion().get(0), cancion4);
        assertEquals(emisora.obtenerListaReproduccion().get(1), cancion1);
        assertEquals(emisora.obtenerListaReproduccion().get(2), cancion3);
        assertEquals(emisora.obtenerListaReproduccion().get(3), cancion2);
        
        // Otro más al inicio, indicando de predecesor
        // un contenido que no existe
        Cancion cancion5 = new Cancion("E", 3);
        Cancion cancion6 = new Cancion("F", 5);
        emisora.agregar(cancion5, cancion6);
        assertEquals(emisora.obtenerDuracion(), 19);
        assertTrue(emisora.obtenerListaReproduccion().contains(cancion5));
        assertEquals(emisora.obtenerListaReproduccion().size(), 5);
        assertEquals(emisora.obtenerListaReproduccion().get(0), cancion5);
        assertEquals(emisora.obtenerListaReproduccion().get(1), cancion4);
        assertEquals(emisora.obtenerListaReproduccion().get(2), cancion1);
        assertEquals(emisora.obtenerListaReproduccion().get(3), cancion3);
        assertEquals(emisora.obtenerListaReproduccion().get(4), cancion2);
    }

}
