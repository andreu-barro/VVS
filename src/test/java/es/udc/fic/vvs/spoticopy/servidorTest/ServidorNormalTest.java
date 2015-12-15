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
	
        /*@Test
	public void buscarTest2() {
		List<Contenido> listaContenidos = new ArrayList<Contenido>();
		Cancion cancion = new Cancion("cancion1", 5);
                Cancion cancion2 = new Cancion("cancion2", 5);
                Cancion cancion3 = new Cancion("cancion3", 5);
		listaContenidos.add(new Anuncio());
		listaContenidos.add(cancion);
                listaContenidos.add(cancion2);
                listaContenidos.add(cancion3);
		Token token = new Token("ADMINISTRADOR");
		
		// Servidor Padre
		ServidorNormal servidor = new ServidorNormal("SP", listaContenidos, token);
		
		assertEquals(servidor.buscar("cancion", null),listaContenidos);
                
                // Obtenemos el nombre del servidor
                assertEquals(servidor.obtenerNombre(), "SP");
                
	}*/
        
	@Test
	public void eliminarTest()
	{
		List<Contenido> listaContenidos = new ArrayList<Contenido>();
		Cancion cancion = new Cancion("cancion1", 5);
		//listaContenidos.add(new Anuncio());
		listaContenidos.add(cancion);
		Token token = new Token("ADMINISTRADOR");
		
		ServidorNormal servidor = new ServidorNormal("SP", listaContenidos, token);
		servidor.eliminar(cancion, "ADMINISTRADOR");
		
		List<Contenido> listaVacia = new ArrayList<Contenido>();
		assertEquals(servidor.buscar("cancion", token.alta()),listaVacia);

                // Damos token de alta
                String strToken = token.alta();
                
                // Usamos token
                token.usarToken(strToken);
                
                // Damos token de baja
                token.baja(strToken);
	}
	
	@Test
	public void agregarNoInsertadoTest()
	{
		List<Contenido> listaContenidos = new ArrayList<Contenido>();
		Cancion cancion = new Cancion("cancion1", 5);
		//listaContenidos.add(new Anuncio());
		listaContenidos.add(cancion);
		Token token = new Token("ADMINISTRADOR");
		
		ServidorNormal servidor = new ServidorNormal("SP", listaContenidos, token);
		String tokenGenerado = servidor.alta();
		Cancion cancion2 = new Cancion("coco", 5);
		servidor.agregar(cancion2, tokenGenerado);
		List<Contenido> listaNueva = new ArrayList<Contenido>();
		//listaNueva.add(cancion2);
		assertEquals(servidor.buscar("coc", token.alta()),listaNueva);
	}
	
	@Test
	public void agregarTest()
	{
		List<Contenido> listaContenidos = new ArrayList<Contenido>();
		Cancion cancion = new Cancion("cancion1", 5);
		//listaContenidos.add(new Anuncio());
		listaContenidos.add(cancion);
		Token token = new Token("ADMINISTRADOR");
		
		ServidorNormal servidor = new ServidorNormal("SP", listaContenidos, token);

		Cancion cancion2 = new Cancion("coco", 5);
		servidor.agregar(cancion2, "ADMINISTRADOR");
		List<Contenido> listaNueva = new ArrayList<Contenido>();
		listaNueva.add(cancion2);
		assertEquals(servidor.buscar("coc", token.alta()),listaNueva);
	}

}
