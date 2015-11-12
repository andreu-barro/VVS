package es.udc.fic.vvs.spoticopy.contenidoTest;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import es.udc.fic.vvs.spoticopy.contenido.Emisora;
import es.udc.fic.vvs.spoticopy.contenido.Contenido;

public class EmisoraTest {

	
	Emisora emisora = new Emisora("Andreu", null);

	@Test
	public void obtenerTituloTest() {
		assertTrue(emisora.obtenerTitulo().equals("Andreu"));
	}
	
	@Test
	public void obtenerDuracionTest() {
		assertTrue(emisora.obtenerDuracion() == 0);
	}

	@Test
	public void buscarTest() 
	{
		List<Contenido> listaContenidos = new ArrayList<Contenido>();
		listaContenidos.add(emisora);
		assertEquals(emisora.buscar("dreu"),listaContenidos);
	}
	
	
}
