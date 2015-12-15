package es.udc.fic.vvs.spoticopy.generadorTest;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import net.java.quickcheck.Generator;
import net.java.quickcheck.generator.PrimitiveGenerators;
import net.java.quickcheck.generator.iterable.Iterables;
import static net.java.quickcheck.generator.CombinedGenerators.lists;
import static net.java.quickcheck.generator.PrimitiveGenerators.integers;
import static net.java.quickcheck.generator.PrimitiveGenerators.strings;

import org.junit.Test;

import es.udc.fic.vvs.spoticopy.contenido.Cancion;
import es.udc.fic.vvs.spoticopy.contenido.Contenido;
import es.udc.fic.vvs.spoticopy.contenido.Emisora;
import es.udc.fic.vvs.spoticopy.servidor.Servidor;
import es.udc.fic.vvs.spoticopy.servidor.ServidorNormal;
import es.udc.fic.vvs.spoticopy.token.Token;

public class ServidorTest {
	@Test
	public void nombreServidorTest(){
		 Generator<String> first = PrimitiveGenerators.strings();
		 List<String> lista = new ArrayList<String>();
		 List<Servidor> servidores = new ArrayList<Servidor>();
		 List<Contenido> contenido = new ArrayList<Contenido>();
		 Token token = new Token("admin");
		 Servidor servidor;
		 for(int i=0;i<100;i++){
			 lista.add(first.next());
		 }
		 for(int i=0;i<100;i++){
			 servidor = new ServidorNormal(lista.get(i),contenido,token);
			 servidores.add(servidor);
		 }
		 for(int i=0;i<100;i++){
			 assertEquals(servidores.get(i).obtenerNombre(),lista.get(i));
		 }
	}
	
	@Test
	public void eliminarServidorTest(){
		boolean eliminar = false;
		String tokenAdmin = "4691819800";
		List<Contenido> eliminable = new ArrayList<Contenido>();
		for (Servidor servidor : Iterables.toIterable(new GeneradorServidor())){
			Integer numero = integers(1,100).next();
			for(int i=0;i<numero;i++){
				GeneradorContenido c = new GeneradorContenido();
				Contenido contenido = c.next();
				eliminable.add(contenido);
				try {
					servidor.agregar(contenido, tokenAdmin);
					try {
						servidor.eliminar(contenido, tokenAdmin);
						eliminar=true;
					} catch (Exception e) {
						eliminar=false;
					}
					
				} catch (Exception e2) {
					eliminar=false;
				}
				assertTrue(eliminar);
				eliminar=false;
			}
			
		}
	}
	
	@Test
	public void altaServidorTest(){
		String token=null;
		boolean alta=false;
		for (Servidor servidor : Iterables.toIterable(new GeneradorServidor())){
			Integer numero = integers(1,100).next();
			for(int i=0;i<numero;i++){
				token= servidor.alta();
				if(token != null){
					alta=true;
				}
				assertTrue(alta);
				alta=false;
			}
		}
	    		
	}
	
	@Test
	public void bajaServidorTest(){
		for (Servidor servidor : Iterables.toIterable(new GeneradorServidor())){
			Integer numero = integers(1,100).next();
			List<String> tokens = new ArrayList<String>();
			boolean baja=false;
			for(int i=0;i<numero;i++){
				tokens.add(servidor.alta());
			}
			for(int i=0;i<tokens.size();i++){
				try {
					servidor.baja(tokens.get(i));
					baja=true;
				} catch (Exception e) {
					baja=false;
					e.printStackTrace();
				}
				assertTrue(baja);
				baja=false;
			}
		}
	}

	@Test
	public void agregarServidorTest(){
		boolean agregar = false;
		String tokenAdmin = "4691819800";
		for (Servidor servidor : Iterables.toIterable(new GeneradorServidor())){
			Integer numero = integers(1,100).next();
			for(int i=0;i<numero;i++){
				GeneradorContenido c = new GeneradorContenido();
				Contenido contenido = c.next();
				try {
					servidor.agregar(contenido, tokenAdmin);
					agregar = true;
				} catch (Exception e) {
					agregar=false;
					e.printStackTrace();
				}
				assertTrue(agregar);
			}
		}
	}
	

	
	class GeneradorCancion implements Generator<Contenido>{
		Generator<Integer> entero = integers(1,100);
		Generator<String> string = strings();
		public Contenido next(){
			Contenido cancion = null;
			try {
				cancion = new Cancion(string.next(),entero.next());
			} catch (Exception e) {
				e.printStackTrace();
			}
			return cancion;
		}
	}
	class GeneradorServidorVacio implements Generator<Servidor>{
		Token token = new Token("admin");
		List<Contenido> contenido = new ArrayList<Contenido>();
		public Servidor next(){
			Servidor servidor = new ServidorNormal(strings().next(), contenido, token);
			return servidor;
		}
	}

	//Genera servidores
	class GeneradorServidor implements Generator<Servidor>{
		Token tokenAdmin = new Token("admin");
		List<Contenido> contenidoVacio = new ArrayList<Contenido>();
		GeneradorContenido c = new GeneradorContenido();
		Contenido contenido = c.next();
		public Servidor next(){
			Servidor servidor = new ServidorNormal(strings().next(),contenidoVacio,tokenAdmin);

				servidor.agregar(contenido, "admin");
			return servidor;
		}
	}
	
	//Genera Emisoras
	class GeneradorContenido implements Generator<Contenido> {
		Generator<List<String>> lGen = lists(strings());

		public Contenido next() {
		    List<String> lista = lGen.next();
	    	Generator<Integer> iGen = integers(1,100);
	    	List<Contenido> contenido = new ArrayList<Contenido>();
			Emisora emisora=new Emisora(strings().next(), contenido);
			for (String any : lista) { 
				Cancion cancion = new Cancion(any, iGen.next());
				emisora.agregar(cancion, null);			
			} 	
			return emisora;		
		}	
	}
}
