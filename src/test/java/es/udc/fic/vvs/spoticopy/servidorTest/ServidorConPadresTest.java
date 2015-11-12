package es.udc.fic.vvs.spoticopy.servidorTest;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import es.udc.fic.vvs.spoticopy.contenido.Cancion;
import es.udc.fic.vvs.spoticopy.contenido.Contenido;
import es.udc.fic.vvs.spoticopy.servidor.ServidorConPadres;
import es.udc.fic.vvs.spoticopy.servidor.ServidorNormal;

public class ServidorConPadresTest {
	
	@Test
	public void buscarTest() {
		List<Contenido> listaContenidos = new ArrayList<Contenido>();
		Cancion cancion = new Cancion("cancion1", 5);
		listaContenidos.add(cancion);
		
		// Servidor Padre
		ServidorNormal servidorPadre = new ServidorNormal("SP", listaContenidos);
		
		// Servidor con Padre
		ServidorConPadres servidorConPadres = new ServidorConPadres("SCP", servidorPadre, null);
		
		assertEquals(servidorConPadres.buscar("cancion", "ADMINISTRADOR"),listaContenidos);
	}
	
}
