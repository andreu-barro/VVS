package es.udc.fic.vvs.spoticopy.servidor;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import es.udc.fic.vvs.spoticopy.contenido.Anuncio;
import es.udc.fic.vvs.spoticopy.contenido.Contenido;

public class ServidorConPadres implements Servidor {

	private Servidor padre;
	private String nombre;
	private List<String> tokens;
	private String admin_token;
	private Random r;
	private List<Contenido> contenidos;
	
	public ServidorConPadres(String nombre, Servidor padre,
			String admin_token, List<Contenido> contenidos) {
		this.nombre = nombre;
		this.padre = padre;
		this.tokens = new ArrayList<String>();
		this.admin_token = admin_token;
		this.contenidos = contenidos;
		// Introducir una semilla concreta si se quiere una generación de
		// tokens predecible entre ejecuciones de la aplicación
		this.r = new Random(Calendar.getInstance().getTimeInMillis());
	}
	
	public String obtenerNombre() {
		return nombre;
	}

	public String alta() {
		String token = null;
		
		while(token == null) {
			try {
				byte[] bytes = new byte[16];
				this.r.nextBytes(bytes);
				token = new String(bytes, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				return null;
			}
			// Ha generado un token que ya existe
			// o el token de administración
			if(tokens.contains(token)
			|| token.contentEquals(this.admin_token)) {
				token = null;
			} else {
				tokens.add(token);
			}
		}
		return token;
	}

	public void baja(String token) {
		tokens.remove(token);
	}

	public void agregar(Contenido contenido, String token) {
		// Sólo el administrador puede usar este método
		if(token.contentEquals(admin_token)) {
			contenidos.add(contenido);
		}
		return;
	}

	public void eliminar(Contenido contenido, String token) {
		// Sólo el administrador puede usar este método
		if(token.contentEquals(admin_token)) {
			contenidos.remove(contenido);
		}
		return;
	}

	public List<Contenido> buscar(String subcadena, String token) {
		List<Contenido> resultado = new ArrayList<Contenido>();
		boolean hay_token = false;
		
		if(token != null) {
			if(tokens.contains(token)) {
				hay_token = true;
			}
		}
		
		// Lista de contenidos que contiene la subcadena
		List<Contenido> busqueda = new ArrayList<Contenido>();
		for(Contenido i : contenidos) {
			if(i.obtenerTitulo().contains(subcadena)) {
				busqueda.add(i);
			}
		}
		
		// Envío final
		int intervalo = 0;
		for(Contenido i : busqueda) {
			if(!hay_token && intervalo == 0) {
				resultado.add(new Anuncio());
			}
			if(i.obtenerTitulo().contains(subcadena)) {
				resultado.add(i);
			}	
			intervalo++;
			if(intervalo >= 3) {
				intervalo = 0;
			}
		}
		
		// Usamos al padre si el contenido está vacío
		if(resultado.isEmpty()) {
			resultado = padre.buscar(subcadena, token);
		}
		
		return resultado;
	}

}
