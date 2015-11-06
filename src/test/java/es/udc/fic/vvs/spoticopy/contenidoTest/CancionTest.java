package es.udc.fic.vvs.spoticopy.contenidoTest;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import es.udc.fic.vvs.spoticopy.contenido.Cancion;
import es.udc.fic.vvs.spoticopy.contenido.Contenido;

public class CancionTest {

	Cancion cancion = new Cancion("Andreu", 5);

	@Test
	public void obtenerTituloTest() {
		assertTrue(cancion.obtenerTitulo().equals("Andreu"));
	}
	
	@Test
	public void obtenerDuracionTest() {
		assertTrue(Integer.compare(cancion.obtenerDuracion(),5)==0);
	}

	@Test
	public void buscarTest() 
	{
		List<Contenido> listaContenidos = new ArrayList<Contenido>();
		listaContenidos.add(cancion);
		assertEquals(cancion.buscar("dreu"),listaContenidos);
	}
	
	
}
