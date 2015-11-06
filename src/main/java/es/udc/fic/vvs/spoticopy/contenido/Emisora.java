/**
 * @author Sr. Javi
 *
 */
package es.udc.fic.vvs.spoticopy.contenido;

import java.util.ArrayList;
import java.util.List;

public class Emisora implements Contenido{

	// Anuncio tendra una duracion y un titulo fijos
	private String titulo;
	private int duracion;
	private List<Contenido> listaContenidos;
	
	// Constructor de Emisora
	public Emisora(String titulo, int duracion, List<Contenido> listaContenidos) {
		super();
		this.titulo = titulo;
		this.duracion = duracion;
		this.setListaContenidos(listaContenidos);
	}

	// Obtiene el titulo de la emisora
	public String obtenerTitulo() {
		return titulo;
	}
	
	// Obtiene la duración de la emisora
	public int obtenerDuracion() {
		return duracion;
	}
	
	// Obtenemos la lista de reproduccion de la emisora
	public List<Contenido> obtenerListaReproduccion() {
		return this.listaContenidos;
	}
	
	// Si la cadena que le pasamos está contenida en el titulo de la emisora, la devolvemos.
	// Sino devolvemos una lista vacía
	public List<Contenido> buscar(String subcadena) {
		if (this.titulo.contains(subcadena)) {
			List<Contenido> lista = new ArrayList<Contenido>();
			lista.add(this);
			return lista;
		}
		return null;
	}
	
	// Agregamos un contenido a la emisora
	public void agregar(Contenido contenido, Contenido predecesor) {
		
		// Si tenemos el predecesor, insertamos el contenido en la posicion siguiente.
		// Suponemos que la siguiente posicion está vacía
		if (this.listaContenidos.contains(predecesor)) {
			int pos = listaContenidos.indexOf(predecesor);
			this.listaContenidos.add(pos+1, contenido);
		}

		// Si no hay predecesor o si la lista está vacía
		if (predecesor==null || this.listaContenidos.isEmpty()) {
			this.listaContenidos.add(contenido);
		}

	}
	
	// Elimina el contenido de la emisora
	public void eliminar(Contenido contenido) {
		
		// Si existe el contenido a eliminar
		if (this.listaContenidos.contains(contenido)) {
			this.listaContenidos.remove(contenido);
		}
	}

	// Obtenemos la lista de contenidos de la emisora
	public List<Contenido> getListaContenidos() {
		return listaContenidos;
	}

	// Seleccionamos la lista de contenidos de la emisora
	public void setListaContenidos(List<Contenido> listaContenidos) {
		this.listaContenidos = listaContenidos;
	}

}