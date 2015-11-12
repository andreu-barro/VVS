package es.udc.fic.vvs.spoticopy.servidorTest;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import es.udc.fic.vvs.spoticopy.contenido.Anuncio;
import es.udc.fic.vvs.spoticopy.contenido.Cancion;
import es.udc.fic.vvs.spoticopy.contenido.Contenido;
import es.udc.fic.vvs.spoticopy.servidor.ServidorNormal;
import es.udc.fic.vvs.spoticopy.token.Token;

public class ServidorNormalTest {

	@Test
	public void buscarTest() {
		List<Contenido> listaContenidos = new ArrayList<Contenido>();
		Cancion cancion = new Cancion("cancion1", 5);
		listaContenidos.add(new Anuncio());
		listaContenidos.add(cancion);

		Token token = new Token("ADMINISTRADOR");
		
		// Servidor Padre
		ServidorNormal servidor = new ServidorNormal("SP", listaContenidos, token);
		
		assertEquals(servidor.buscar("cancion", null),listaContenidos);
	}

}
