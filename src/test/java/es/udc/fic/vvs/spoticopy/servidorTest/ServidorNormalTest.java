package es.udc.fic.vvs.spoticopy.servidorTest;

import es.udc.fic.vvs.spoticopy.contenido.Anuncio;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import es.udc.fic.vvs.spoticopy.contenido.Cancion;
import es.udc.fic.vvs.spoticopy.contenido.Contenido;
import es.udc.fic.vvs.spoticopy.servidor.Servidor;
import es.udc.fic.vvs.spoticopy.servidor.ServidorNormal;
import es.udc.fic.vvs.spoticopy.token.Token;

public class ServidorNormalTest {

    @Test
    public void obtenerNombreTest() {
        Token token = new Token("ADMIN");
        Servidor serv = new ServidorNormal("NOMBRE", null, token);

        assertTrue(serv.obtenerNombre().contentEquals("NOMBRE"));
    }

    @Test
    public void altaTokenTest() {
        Token token = new Token("ADMIN");
        Servidor serv = new ServidorNormal("NOMBRE", null, token);

        String res = serv.alta();
        assertTrue(res != null);
        assertNotEquals(res, "ADMIN");
        assertEquals(token.obtenerUsos(res), 10);

        // Generamos un segundo token
        String res2 = serv.alta();
        assertTrue(res2 != null);
        assertNotEquals(res, res2);
        assertNotEquals(res, "ADMIN");
        assertEquals(token.obtenerUsos(res2), 10);
    }

    @Test
    public void bajaTokenTest() {
        Token token = new Token("ADMIN");
        Servidor serv = new ServidorNormal("NOMBRE", null, token);

        // Recien creado
        String res = serv.alta();
        assertTrue(res != null);
        assertEquals(token.obtenerUsos(res), 10);
        // Damos de baja
        serv.baja(res);
        assertEquals(token.obtenerUsos(res), 0);
        // Damos de baja un nulo
        serv.baja(null);
        // Damos de baja un token que no existe
        serv.baja("ADSF");
        // Damos de baja el token de admin
        serv.baja("ADMIN");
    }

    @Test
    public void agregarTest() {
        // Creamos un servidor con algunos contenidos
        List<Contenido> lista = new ArrayList<Contenido>();
        List<Contenido> lista2 = new ArrayList<Contenido>();
        Cancion cancion1 = new Cancion("A", 5);
        lista.add(cancion1);
        lista2.add(cancion1);

        Token token = new Token("ADMIN");
        Servidor serv = new ServidorNormal("NOMBRE", lista, token);

        // Comprobacion inicial
        assertEquals(serv.buscar("", "ADMIN"), lista);

        // Insertamos uno al final
        Cancion cancion2 = new Cancion("B", 3);
        serv.agregar(cancion2, "ADMIN");
        lista2.add(cancion2);
        assertEquals(serv.buscar("", "ADMIN"), lista2);

        // Insertamos un nulo
        serv.agregar(null, "ADMIN");
        assertEquals(serv.buscar("", "ADMIN"), lista2);

        // Insertamos sin token de admin
        Cancion cancion3 = new Cancion("B", 8);
        serv.agregar(cancion3, "ASDF");
        assertEquals(serv.buscar("", "ADMIN"), lista2);
    }

    @Test
    public void eliminarTest() {
        // Creamos un servidor con varios contenidos
        List<Contenido> lista = new ArrayList<Contenido>();
        List<Contenido> lista2 = new ArrayList<Contenido>();
        Cancion cancion1 = new Cancion("A", 5);
        Cancion cancion2 = new Cancion("B", 3);
        Cancion cancion3 = new Cancion("C", 7);
        lista.add(cancion1);
        lista.add(cancion2);
        lista.add(cancion3);
        lista2.add(cancion1);
        lista2.add(cancion2);
        lista2.add(cancion3);
        
        Token token = new Token("ADMIN");
        Servidor serv = new ServidorNormal("NOMBRE", lista, token);

        // Comprobacion inicial
        assertEquals(serv.buscar("", "ADMIN"), lista);

        // Eliminamos uno
        serv.eliminar(cancion2, "ADMIN");
        lista2.remove(cancion2);
        assertEquals(serv.buscar("", "ADMIN"), lista2);

        // Eliminamos uno que NO existe
        Cancion cancion4 = new Cancion("D", 15);
        serv.eliminar(cancion4, "ADMIN");
        assertEquals(serv.buscar("", "ADMIN"), lista2);

        // Eliminamos un null
        serv.eliminar(null, "ADMIN");
        assertEquals(serv.buscar("", "ADMIN"), lista2);

        // Eliminamos uno que existe pero sin permisos
        serv.eliminar(cancion1, "ASDF");
        assertEquals(serv.buscar("", "ADMIN"), lista2);
    }

    @Test
    public void buscarTest() {
        List<Contenido> listaContenidos = new ArrayList<Contenido>();
        Cancion cancion1 = new Cancion("cancion1", 5);
        Cancion cancion2 = new Cancion("cancion2", 9);
        Cancion cancion3 = new Cancion("cacion3", 3);
        Cancion cancion4 = new Cancion("cacion4", 31);
        Cancion cancion5 = new Cancion("cancion5", 12);
        listaContenidos.add(cancion1);
        listaContenidos.add(cancion2);
        listaContenidos.add(cancion3);
        listaContenidos.add(cancion4);
        listaContenidos.add(cancion5);

        Token token = new Token("ADMIN");
        Servidor serv = new ServidorNormal("NOMBRE", listaContenidos, token);

        // Busquedas con token admin
        assertEquals(serv.buscar("cion", "ADMIN"), listaContenidos);
        // Busquedas con token normal
        String tok = serv.alta();
        long usos = token.obtenerUsos(tok);
        assertEquals(serv.buscar("cion", tok), listaContenidos);
        assertEquals(token.obtenerUsos(tok), usos-1);
        // Busquedas parciales
        List<Contenido> busqueda = serv.buscar("cancion", tok);
        assertEquals(busqueda.size(), 3);
        assertEquals(busqueda.get(0), cancion1);
        assertEquals(busqueda.get(1), cancion2);
        assertEquals(busqueda.get(2), cancion5);
        assertEquals(token.obtenerUsos(tok), usos-2);
        // Busquedas sin resultado
        assertEquals(new ArrayList<Contenido>(), serv.buscar("ASDF", tok));
        assertEquals(token.obtenerUsos(tok), usos-3);
        
        // Busquedas sin token y con token falso incluyen anuncios
        List<Contenido> busqueda2 = serv.buscar("ca", null);
        List<Contenido> busqueda3 = serv.buscar("ca", "ASDF");
        assertEquals(busqueda2, busqueda3);
        // Contenidos correctos
        assertEquals(busqueda2.get(1), cancion1);
        assertEquals(busqueda2.get(2), cancion2);
        assertEquals(busqueda2.get(3), cancion3);
        assertEquals(busqueda2.get(5), cancion4);
        assertEquals(busqueda2.get(6), cancion5);
        // Anuncios incluidos
        for(int i = 0; i < busqueda2.size(); i+=4) {
            assertEquals(busqueda2.get(i), new Anuncio());
        }
        // Una busqueda sin resultados sigue dando un anuncio
        List<Contenido> anuncio = new ArrayList<Contenido>();
        anuncio.add(new Anuncio());
        assertEquals(serv.buscar("ASDF", null), anuncio);
    }

}
