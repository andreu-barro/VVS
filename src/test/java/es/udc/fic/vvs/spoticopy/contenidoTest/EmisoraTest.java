package es.udc.fic.vvs.spoticopy.contenidoTest;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import es.udc.fic.vvs.spoticopy.contenido.Cancion;
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
	
	@Test
	public void buscarInexistenteTest() 
	{
		List<Contenido> listaContenidos = new ArrayList<Contenido>();
		listaContenidos.add(emisora);
		List<Contenido> listaVacia = new ArrayList<Contenido>();
		assertEquals(emisora.buscar("nada"),listaVacia);
	}
	
	@Test
	public void obtenerListaReproduccionTest()
	{
		List<Contenido> lista = new ArrayList<Contenido>();
		Cancion cancion = new Cancion("Andreu", 5);
		lista.add(cancion);
		Emisora emisora2 = new Emisora("Andreu", lista);
		assertEquals(emisora2.obtenerListaReproduccion(), lista);
	}
	
	@Test
	public void eliminarTest()
	{
		//Creamos una lista de contenidos
		List<Contenido> lista = new ArrayList<Contenido>();
		Cancion cancion1 = new Cancion("A", 5);
		Cancion cancion2 = new Cancion("B", 3);
		lista.add(cancion1);
		lista.add(cancion2);
		//creamos la emisora
		Emisora emisora2 = new Emisora("Andreu", lista);
		emisora2.eliminar(cancion2);
		//Al eliminar 1 dato, la lista de contenidos de la emisora solo tendra 1 dato
		assertEquals(1, emisora2.obtenerListaReproduccion().size());
	}
	
	@Test
	public void eliminarDatoNoExistenteTest()
	{
		//Creamos una lista de contenidos
		List<Contenido> lista = new ArrayList<Contenido>();
		Cancion cancion1 = new Cancion("A", 5);
		Cancion cancion2 = new Cancion("B", 3);
		Cancion cancion3 = new Cancion("C", 3); //Cancion que no esta en la lista de reproduccion
		lista.add(cancion1);
		lista.add(cancion2);
		//creamos la emisora
		Emisora emisora2 = new Emisora("Andreu", lista);
		emisora2.eliminar(cancion3);
		//Al eliminar 1 dato, la lista de contenidos de la emisora solo tendra 1 dato
		assertEquals(2, emisora2.obtenerListaReproduccion().size());
	}
	
	@Test
	public void agregarTest()
	{
		Cancion cancion1 = new Cancion("A", 5);
		emisora.agregar(cancion1, null);
		assertEquals(5, emisora.obtenerDuracion());
	}
	
	@Test
	public void agregar2Test()
	{
		Cancion cancion1 = new Cancion("A", 5);
		Cancion cancion2 = new Cancion("C", 5);
		emisora.agregar(cancion1, null);
		emisora.agregar(cancion2, emisora);
		assertEquals(10, emisora.obtenerDuracion());
	}
	
	@Test
	public void agregar3Test()
	{
		
		emisora.agregar(emisora, null);
		emisora.agregar(emisora, emisora);
		assertEquals(0, emisora.obtenerDuracion());
	}
}
