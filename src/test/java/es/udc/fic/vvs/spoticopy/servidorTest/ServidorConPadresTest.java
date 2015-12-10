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
	
	@Test
	public void buscarTest() {
		List<Contenido> listaContenidos = new ArrayList<Contenido>();
		Cancion cancion = new Cancion("cancion1", 5);
		listaContenidos.add(cancion);
		Token token = new Token("ADMINISTRADOR");
		
		// Servidor Padre
		ServidorNormal servidorPadre = new ServidorNormal("SP", listaContenidos, token);
		
		// Servidor con Padre
		ServidorConPadres servidorConPadres = new ServidorConPadres("SCP", servidorPadre, null, token);
		
		assertEquals(servidorConPadres.buscar("cancion", "ADMINISTRADOR"),listaContenidos);
	}
	
	@Test
	public void buscarTest2() {
		List<Contenido> listaContenidos = new ArrayList<Contenido>();
		Cancion cancion = new Cancion("cancion1", 5);
		listaContenidos.add(cancion);
		Token token = new Token("ADMINISTRADOR");
		
		List<Contenido> listaContenidos2 = new ArrayList<Contenido>();
		Cancion c = new Cancion ("coco",2);
		listaContenidos2.add(c);
		// Servidor Padre
		ServidorNormal servidorPadre = new ServidorNormal("SP", listaContenidos, token);
		
		// Servidor con Padre
		ServidorConPadres servidorConPadres = new ServidorConPadres("SCP", servidorPadre, listaContenidos2, token);
		
		assertEquals(servidorConPadres.buscar("cancion", "ADMINISTRADOR"),listaContenidos);
	}
	
}
