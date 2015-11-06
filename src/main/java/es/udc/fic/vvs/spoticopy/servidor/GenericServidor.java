package es.udc.fic.vvs.spoticopy.servidor;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import es.udc.fic.vvs.spoticopy.contenido.Contenido;

// Esta clase contiene el comportamiento genérico de un servidor
// Las particularidades se implementan en distintas clases que
// heredarán de GenericServidor
public abstract class GenericServidor implements Servidor {

	protected String nombre;
	protected List<String> tokens;
	protected String admin_token;
	protected Random r;
	protected List<Contenido> contenidos;

	public GenericServidor(String nombre, String admin_token, List<Contenido> contenidos) {
		this.nombre = nombre;
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

}