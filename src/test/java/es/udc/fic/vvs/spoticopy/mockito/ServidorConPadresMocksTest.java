package es.udc.fic.vvs.spoticopy.mockito;

import es.udc.fic.vvs.spoticopy.contenido.Anuncio;
import es.udc.fic.vvs.spoticopy.contenido.Cancion;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;

import es.udc.fic.vvs.spoticopy.contenido.Contenido;
import es.udc.fic.vvs.spoticopy.contenido.Emisora;
import es.udc.fic.vvs.spoticopy.servidor.ServidorNormal;
import es.udc.fic.vvs.spoticopy.token.Token;

public class ServidorConPadresMocksTest {

    Anuncio anuncioMock = Mockito.mock(Anuncio.class);
    Emisora emisoraMock = Mockito.mock(Emisora.class);
    Cancion cancionMock = Mockito.mock(Cancion.class);

    @Test
    public void testMockAgregar() {

        String strToken = "ADMIN";
        Token token = new Token("ADMIN");
        ServidorNormal servidor = new ServidorNormal("servidor", null, token);
        boolean pasa = true;

        servidor.agregar(cancionMock, strToken);

        try {
            List<Contenido> contenidos = servidor.buscar("", strToken);
        }
        catch (Exception e) {
            pasa=false;
        }
        assertTrue(!pasa);
    }

    @Test
    public void testMockEliminar() {
        String strToken = "ADMIN";
        Token token = new Token("ADMIN");
        ServidorNormal servidor = new ServidorNormal("servidor", null, token);
        
        servidor.eliminar(cancionMock, strToken);
        List<Contenido> contenidos = servidor.buscar("", strToken);
        assertTrue(contenidos.isEmpty());
    }

    @Test
    public void testMockBuscar() {
        Token token = new Token("ADMIN");
        ServidorNormal servidor = new ServidorNormal("servidor", null, token);
        List<Contenido> listaCanciones = new ArrayList<Contenido>();
        listaCanciones.add(cancionMock);
        List<Contenido> listaPublicidad = new ArrayList<Contenido>();
        List<Contenido> listaEmisoras = new ArrayList<Contenido>();
        listaEmisoras.add(emisoraMock);
        when(anuncioMock.buscar(anyString())).thenReturn(listaPublicidad);
        when(cancionMock.buscar(anyString())).thenReturn(listaCanciones);
        when(emisoraMock.buscar(anyString())).thenReturn(listaEmisoras);
        when(emisoraMock.obtenerTitulo()).thenReturn("Emisora");
        when(anuncioMock.obtenerTitulo()).thenReturn("Anuncio");
        when(cancionMock.obtenerTitulo()).thenReturn("Cancion");

        for (int i = 0; i < 5; i++) {
            servidor.agregar(cancionMock, "ADMIN");
            servidor.agregar(anuncioMock, "ADMIN");
            servidor.agregar(emisoraMock, "ADMIN");
        }
        
        String strToken = servidor.alta();
        String subCadenaCancion = "Cancion";
        String subCadenaAnuncio = "Anuncio";
        String subCadenaEmisora = "Emisora";

        List<Contenido> resultadoCanciones;
        List<Contenido> resultadoAnuncios;
        List<Contenido> resultadoEmisoras;
        resultadoCanciones = servidor.buscar(subCadenaCancion, strToken);
        assertEquals(resultadoCanciones.size(), 5);
        resultadoAnuncios = servidor.buscar(subCadenaAnuncio, strToken);
        assertEquals(resultadoAnuncios.size(), 5);
        resultadoEmisoras = servidor.buscar(subCadenaEmisora, strToken);
        assertEquals(resultadoEmisoras.size(), 5);

    }
}
