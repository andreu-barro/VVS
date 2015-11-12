package es.udc.fic.vvs.spoticopy.servidor;

import java.util.ArrayList;
import java.util.List;

import es.udc.fic.vvs.spoticopy.contenido.Contenido;
import es.udc.fic.vvs.spoticopy.token.Token;

/** Esta clase contiene el comportamiento generico de un servidor.
 * Las particularidades se implementan en distintas clases que
 * heredaran de GenericServidor.
 */
public abstract class ServidorGenerico implements Servidor {

	protected String nombre;
	protected List<Contenido> contenidos;
	/** Lista de tokens compartida por todos los servidores */
	protected Token token = null;
	
	public ServidorGenerico(String nombre, List<Contenido> contenidos, Token token) {
		this.nombre = nombre;
		this.contenidos = contenidos;
		if(contenidos == null) {
			this.contenidos = new ArrayList<Contenido>();
		}
		if(token != null) {
			this.token = token;
		}
		
	}

	public String obtenerNombre() {
		return nombre;
	}

	public String alta() {
		return token.alta();
	}

	public void baja(String tok) {
		token.baja(tok);
	}

	public void agregar(Contenido contenido, String tok) {
		// Solo el administrador puede usar este metodo
		if(token.isAdminToken(tok)) { 
			contenidos.add(contenido);
		}
	}

	public void eliminar(Contenido contenido, String tok) {
		// Solo el administrador puede usar este metodo
		if(token.isAdminToken(tok)) {
			contenidos.remove(contenido);
		}
	}

}