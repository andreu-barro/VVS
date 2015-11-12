package es.udc.fic.vvs.spoticopy.contenido;

import java.util.List;

/** Un contenido es un tipo de dato que tiene al menos un titulo y
 *  una duracion. Un contenido puede tener otros contenidos.
 */
public interface Contenido {

	/** Devuelve el nombre del contenido.
	 * 
	 *  @return el titulo del contenido
	 */
	public String obtenerTitulo();
	
	/** Devuelve la duracion del contenido.
	 * 
	 *  @return la duracion del contenido
	 */
	public int obtenerDuracion();
	
	/** Devuelve la lista de contenidos (un contenido puede
	 *  ser una lista de otros contenidos).
	 * 
	 * @return la lista de contenidos
	 */
	public List<Contenido> obtenerListaReproduccion();
	
	/** Devuelve los contenidos que tengan la subcadena en su titulo. De
	 *  ser un contenido unico, se devuelve a si mismo solo si contiene
	 *  la subcadena en su propio titulo.
	 * 
	 * @param subcadena la cadena a buscar en el titulo
	 * @return la lista de contenidos que contienen la subcadena en el titulo
	 */
	public List<Contenido> buscar(String subcadena);
	
	/** Agrega el contenido en la lista DESPUES del elemento predecesor.
	 *  De no encontrarse el predecesor se insertara al inicio.
	 *  (Solo relevante si el contenido es una lista de otros contenidos).
	 * 
	 * @param contenido el contenido a agregar
	 * @param predecesor el predecesor que debe tener
	 */
	public void agregar(Contenido contenido, Contenido predecesor);
	
	/** Elimina el contenido indicado de la lista. (Solo relevante si el
	 *  contenido es una lista de otros contenidos).
	 * 
	 * @param contenido el contenido a eliminar
	 */
	public void eliminar(Contenido contenido);
}
