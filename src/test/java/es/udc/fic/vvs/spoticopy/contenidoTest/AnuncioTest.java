package es.udc.fic.vvs.spoticopy.contenidoTest;

import static org.junit.Assert.*;

import org.junit.Test;

import es.udc.fic.vvs.spoticopy.contenido.Anuncio;

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

}
