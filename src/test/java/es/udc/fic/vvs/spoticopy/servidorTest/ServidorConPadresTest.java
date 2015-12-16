package es.udc.fic.vvs.spoticopy.servidorTest;

import es.udc.fic.vvs.spoticopy.contenido.Anuncio;
import static org.junit.Assert.*;


import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import es.udc.fic.vvs.spoticopy.contenido.Cancion;
import es.udc.fic.vvs.spoticopy.contenido.Contenido;
import es.udc.fic.vvs.spoticopy.servidor.Servidor;
import es.udc.fic.vvs.spoticopy.servidor.ServidorConPadres;
import es.udc.fic.vvs.spoticopy.servidor.ServidorNormal;
import es.udc.fic.vvs.spoticopy.token.Token;

public class ServidorConPadresTest {

    // El resto de metodos ya se testean junto al servidor Normal.
    
    @Test
    public void buscarTest() {
        List<Contenido> listaPadre = new ArrayList<Contenido>();
        List<Contenido> listaHijo = new ArrayList<Contenido>();
        Cancion cancion1 = new Cancion("cancion1", 5);
        Cancion cancion2 = new Cancion("cancion2", 9);
        Cancion cancion3 = new Cancion("cacion3", 3);
        Cancion cancion4 = new Cancion("cacion4", 31);
        Cancion cancion5 = new Cancion("cancion5", 12);
        listaPadre.add(cancion1);
        listaPadre.add(cancion2);
        listaPadre.add(cancion5);
        listaHijo.add(cancion3);
        listaHijo.add(cancion4);
        listaHijo.add(cancion5);
        listaHijo.add(cancion2);
        
        // Comparten tokens, por simplicidad
        Token token = new Token("ADMIN");
        // El padre tiene las "canciones"
        Servidor padre = new ServidorNormal("PADRE", listaPadre, token);
        Servidor serv 
                = new ServidorConPadres("NOMBRE", padre, listaHijo, token);
        
        // Busqueda con token administrador
        assertEquals(serv.buscar("ca", "ADMIN"), listaHijo);
        String tok = token.alta();
        long usos = token.obtenerUsos(tok);
        // Busqueda con subcadena comun a ambos servidores
        List<Contenido> busqueda = serv.buscar("ca", tok);
        // Solo se recuperan los del hijo
        assertEquals(token.obtenerUsos(tok), usos-1);
        assertEquals(busqueda, listaHijo);
        // Busqueda con subcadena exclusiva al padre
        busqueda = serv.buscar("on1", tok);
        // El token solo se uso una vez
        assertEquals(busqueda, cancion1.obtenerListaReproduccion());
        assertEquals(token.obtenerUsos(tok), usos-2);
        // Busqueda con subcadena ni del hijo ni del padre
        busqueda = serv.buscar("ASDF", tok);
        assertEquals(busqueda, new ArrayList<Contenido>());
        assertEquals(token.obtenerUsos(tok), usos-3);
        
        // Busquedas sin token y con token falso incluyen anuncios
        List<Contenido> busqueda2 = serv.buscar("ca", null);
        List<Contenido> busqueda3 = serv.buscar("ca", "ASDF");
        assertEquals(busqueda2, busqueda3);
        // Contenidos correctos
        assertEquals(busqueda2.get(1), cancion3);
        assertEquals(busqueda2.get(2), cancion4);
        assertEquals(busqueda2.get(3), cancion5);
        assertEquals(busqueda2.get(5), cancion2);
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
