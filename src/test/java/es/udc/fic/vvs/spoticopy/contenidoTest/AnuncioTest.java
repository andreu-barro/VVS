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
		assertTrue(anuncio.obtenerTitulo().equals("PUBLICIDAD"));
	}
	
	@Test
	public void obtenerDuracionTest() {
		assertTrue(Integer.compare(anuncio.obtenerDuracion(),5)==0);
	}
	
	@Test
	public void buscarTest() 
	{
		List<Contenido> listaContenidos = new ArrayList<Contenido>();
		listaContenidos.add(anuncio);
		assertEquals(anuncio.buscar("PUBLI"),listaContenidos);
	}

        @Test
	public void obtenerListaReproduccionTest() {
            List<Contenido> anuncios = new ArrayList<Contenido>();
            anuncios.add(anuncio);
            assertEquals(anuncios, anuncio.obtenerListaReproduccion());
	}
        
        @Test
	public void agregarTest() {
            anuncio.agregar(anuncio, anuncio);
	}
        
        @Test
	public void eliminarTest() {
            anuncio.eliminar(anuncio);
	}
}
