package es.udc.fic.vvs.spoticopy.contenido;

import java.util.ArrayList;
import java.util.List;

/** Contenido con titulo y duracion
 */
public class Cancion implements Contenido {

	private String titulo;
	private int duracion;

	public Cancion(String titulo, int duracion)
	{
		this.titulo = titulo;
		this.duracion = duracion;
	}

	//Obtenemos el titulo de la cancion
	public String obtenerTitulo() {
		return this.titulo;
	}

	//Obtenemos la duracion de la cancion
	public int obtenerDuracion() {
		return this.duracion;
	}

	/** Una cancion se devuelve a si mismo dentro de una lista.
	 * 
	 *  @return Una lista de contenidos con el anuncio dentro
	 */
	public List<Contenido> obtenerListaReproduccion() {
		
		List<Contenido> contenido = new ArrayList<Contenido>();
		contenido.add(this);
		return contenido;
	}

	/** Si la subcadena que le pasamos está contenida en el titulo de la cancion,
	 *  lo devolvemos. Sino devolvemos una lista vacía.
	 *  
	 *  @param subcadena la subcadena a buscar
	 *  @return la cancion, si contiene la subcadena en su titulo. Lista vacia
	 *  en caso contrario.
	 */
	public List<Contenido> buscar(String subcadena) {
		List<Contenido> contenido = new ArrayList<Contenido>();
		if(this.titulo.contains(subcadena)) {
			contenido.add(this);
		}
		return contenido;
	}

	/** Cancion no hace uso de esta funcion. De llamarse no hara absolutamente
	 *  nada.
	 * 
	 *  @return la nada hecha codigo
	 */
	public void agregar(Contenido contenido, Contenido predecesor) {
		return;
		
	}

	/** Cancion no hace uso de esta funcion. De llamarse no hara absolutamente
	 *  nada.
	 * 
	 *  @return la nada hecha codigo
	 */
	public void eliminar(Contenido contenido) {
		return;
	}

}
