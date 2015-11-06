package es.udc.fic.vvs.spoticopy.servidor;

import java.util.ArrayList;
import java.util.List;

import es.udc.fic.vvs.spoticopy.contenido.Contenido;
import es.udc.fic.vvs.spoticopy.token.Token;

// Esta clase contiene el comportamiento genérico de un servidor
// Las particularidades se implementan en distintas clases que
// heredarán de GenericServidor
public abstract class GenericServidor implements Servidor {

	protected String nombre;
	protected List<Contenido> contenidos;
	protected Token token;
	
	public GenericServidor(String nombre, List<Contenido> contenidos) {
		this.nombre = nombre;
		this.contenidos = contenidos;
		if(contenidos == null) {
			this.contenidos = new ArrayList<Contenido>();
		}
		this.token = Token.getInstance();
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
		// Sólo el administrador puede usar este método
		if(token.isAdminToken(tok)) { 
			contenidos.add(contenido);
		}
		return;
	}

	public void eliminar(Contenido contenido, String tok) {
		// Sólo el administrador puede usar este método
		if(token.isAdminToken(tok)) {
			contenidos.remove(contenido);
		}
		return;
	}

}