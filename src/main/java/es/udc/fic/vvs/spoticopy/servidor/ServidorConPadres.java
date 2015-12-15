package es.udc.fic.vvs.spoticopy.servidor;

import java.util.ArrayList;
import java.util.List;

import es.udc.fic.vvs.spoticopy.contenido.Anuncio;
import es.udc.fic.vvs.spoticopy.contenido.Contenido;
import es.udc.fic.vvs.spoticopy.token.Token;

/** La peculiaridad de un ServidorConPadres es que, de no poder devolver
 *  contenidos que cumplan el criterio de busqueda, solicita los contenidos
 *  de otro servidor, llamado padre, y devuelve lo que el le proporcione.
 *  Cualquier servidor puede ser el padre, no necesariamente otro Servidor
 *  ConPadres (aunque seria posible montarse arboles, listas o mallas de
 *  Servidores). OJO, la implementacion no contempla el caso de un anillo
 *  de servidores, intentarlo creara un bucle infinito.
 */
public class ServidorConPadres extends ServidorGenerico {

	private Servidor padre;
	
	public ServidorConPadres(String nombre, Servidor padre, List<Contenido> contenidos, Token token) {
		super(nombre, contenidos, token);
		this.padre = padre;
	}

	public List<Contenido> buscar(String subcadena, String tok) {
		List<Contenido> resultado = new ArrayList<Contenido>();
		boolean hay_token = false;
		
		if(tok != null) {
			if(token.contains(tok) || token.isAdminToken(tok)) {
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
		
		// Reducimos un uso al token
		if(hay_token && !token.isAdminToken(tok)) {
			token.usarToken(tok);
		}
		
		// Usamos al padre si el contenido está vacío
		if(resultado.isEmpty()) {
			resultado = padre.buscar(subcadena, tok);
		}
		
		return resultado;
	}

}
