package es.udc.fic.vvs.spoticopy.servidorTest;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import es.udc.fic.vvs.spoticopy.contenido.Cancion;
import es.udc.fic.vvs.spoticopy.contenido.Contenido;
import es.udc.fic.vvs.spoticopy.servidor.ServidorConPadres;
import es.udc.fic.vvs.spoticopy.servidor.ServidorNormal;
import es.udc.fic.vvs.spoticopy.token.Token;

public class ServidorConPadresTest {

    /*@Test
    public void buscarTest() {
        List<Contenido> listaContenidos = new ArrayList<Contenido>();
        List<Contenido> listaContenidosPadre = new ArrayList<Contenido>();
        Cancion cancion = new Cancion("cancion1", 5);
        Cancion cancion2 = new Cancion("cancion2", 14);
        listaContenidos.add(cancion);
        Token token = new Token("ADMIN");

        // Servidor Padre
        ServidorNormal servidorPadre
                = new ServidorNormal("SP", listaContenidos, token);
        // Servidor con Padre
        ServidorConPadres servidorConPadres
                = new ServidorConPadres("SCP", servidorPadre, null, token);

        // Buscar en el hijo da resultados del padre
        assertEquals(servidorConPadres.buscar("cancion", "ADMINISTRADOR"),
                listaContenidos);
    }

    @Test
    public void buscarTest2() {
        List<Contenido> listaContenidos = new ArrayList<Contenido>();
        Cancion cancion = new Cancion("cancion1", 5);
        listaContenidos.add(cancion);
        Token token = new Token("ADMINISTRADOR");

        List<Contenido> listaContenidos2 = new ArrayList<Contenido>();
        Cancion c = new Cancion("coco", 2);
        listaContenidos2.add(c);
        // Servidor Padre
        ServidorNormal servidorPadre = new ServidorNormal("SP", listaContenidos, token);

        // Servidor con Padre
        ServidorConPadres servidorConPadres = new ServidorConPadres("SCP", servidorPadre, listaContenidos2, token);

        assertEquals(servidorConPadres.buscar("cancion", "ADMINISTRADOR"), listaContenidos);

    }

    @Test
     public void buscarTest3() {
     List<Contenido> listaContenidos = new ArrayList<Contenido>();
     Cancion cancion = new Cancion("cancion1", 5);
     listaContenidos.add(cancion);
     Token token = new Token("ADMINISTRADOR");
		
     List<Contenido> listaContenidos2 = new ArrayList<Contenido>();
     Cancion c = new Cancion ("cancion2",2);
     Cancion c2 = new Cancion ("cancion3",2);
     Cancion c3 = new Cancion ("cancion4",2);
     listaContenidos2.add(c);
     listaContenidos2.add(c2);
     listaContenidos2.add(c3);
     // Servidor Padre
     ServidorNormal servidorPadre = new ServidorNormal("SP", listaContenidos, token);
		
     // Servidor con Padre
     ServidorConPadres servidorConPadres = new ServidorConPadres("SCP", servidorPadre, listaContenidos2, token);
		
     assertEquals(servidorConPadres.buscar("cancion", "ADMINISTRADOR"),listaContenidos2);
                
     }*/
}
