/**
 * @author Sr. Javi
 *
 */
package es.udc.fic.vvs.spoticopy.contenido;

import java.util.ArrayList;
import java.util.List;

public class Anuncio implements Contenido {

	// Anuncio tendra una duracion y un titulo fijos
	private String titulo = "PUBLICIDAD";
	private int duracion = 5;
	
	// Obtiene el titulo del anuncio
	public String obtenerTitulo() {
		return titulo;
	}
	
	// Obtiene la duración del anuncio
	public int obtenerDuracion() {
		return duracion;
	}
	
	// Obtenemos el propio anuncio
	public List<Contenido> obtenerListaReproduccion() {
		List<Contenido> lista = new ArrayList<Contenido>();
		lista.add(this);
		return lista;
	}
	
	// Si la cadena que le pasamos está contenida en el titulo del anuncio, lo devolvemos.
	// Sino devolvemos una lista vacía
	public List<Contenido> buscar(String subcadena) {
		if (titulo.contains(subcadena)) {
			List<Contenido> lista = new ArrayList<Contenido>();
			lista.add(this);
			return lista;
		}
		return null;
	}
	
	// agregar no tiene que devolver nada en Anuncio
	public void agregar(Contenido contenido, Contenido predecesor) {
		return;
	}
	// eliminar no tiene que devolver nada en Anuncio
	public void eliminar(Contenido contenido) {
		return;
	}

	@Override
	public boolean equals(Object obj) {
		Contenido objeto = (Anuncio) obj;
		if(titulo.contentEquals(objeto.obtenerTitulo())
		&& duracion == objeto.obtenerDuracion()) {
			return true;
		}
		
		return false;
	}
}