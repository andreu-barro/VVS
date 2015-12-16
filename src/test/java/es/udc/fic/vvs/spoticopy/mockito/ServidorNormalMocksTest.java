package es.udc.fic.vvs.spoticopy.mockito;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;

import es.udc.fic.vvs.spoticopy.contenido.Contenido;
import es.udc.fic.vvs.spoticopy.mocks.AnuncioMock;
import es.udc.fic.vvs.spoticopy.mocks.CancionMock;
import es.udc.fic.vvs.spoticopy.mocks.EmisoraMock;
import es.udc.fic.vvs.spoticopy.servidor.ServidorGenerico;
import es.udc.fic.vvs.spoticopy.servidor.ServidorNormal;
import es.udc.fic.vvs.spoticopy.token.Token;

public class ServidorNormalMocksTest {

    AnuncioMock anuncioMock = Mockito.mock(AnuncioMock.class);
    EmisoraMock emisoraMock = Mockito.mock(EmisoraMock.class);
    CancionMock cancionMock = Mockito.mock(CancionMock.class);

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

        List<Contenido> resultadoCanciones = new ArrayList<Contenido>();
        List<Contenido> resultadoAnuncios = new ArrayList<Contenido>();
        List<Contenido> resultadoEmisoras = new ArrayList<Contenido>();
        resultadoCanciones = servidor.buscar(subCadenaCancion, strToken);
        assertEquals(resultadoCanciones.size(), 5);
        resultadoAnuncios = servidor.buscar(subCadenaAnuncio, strToken);
        assertEquals(resultadoAnuncios.size(), 5);
        resultadoEmisoras = servidor.buscar(subCadenaEmisora, strToken);
        assertEquals(resultadoEmisoras.size(), 5);

    }
}
