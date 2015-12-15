package es.udc.fic.vvs.spoticopy.servidor;

import java.util.List;

import es.udc.fic.vvs.spoticopy.contenido.Contenido;

/**
 * Un servidor ofrece una serie de contenidos. Un administrador puede
 * agregar, y eliminar contenidos de su lista. Los usuarios pueden
 * generar tokens, con los cuales no se agregan anuncios cuando buscan
 * los contenidos del servidor.
 */
public interface Servidor {

	/** Devuelve el nombre con el que identificar al servidor.
	 * 
	 *  @return el nombre del servidor
	 */
	String obtenerNombre();
	
	/** Genera un token para obtener contenidos del servidor libres de
	 *  anuncios. Tiene un número limitado de usos.
	 *  
	 *  @return el token generado
	 */
	String alta();
	
	/** Elimina un token pregenerado. ESTO LO HARA AUNQUE LE QUEDEN USOS
	 *  RESTANTES, SOLO USAR SI ES SEGURO QUE NO SE QUIERE SEGUIR USANDO
	 *
	 * @param token El token a dar de baja
	 */
	void baja(String token);
	
	/** Agrega el contenido a la lista del servidor. Esta operacion solo
	 *  es posible mediante el uso de un token especial de administracion.
	 * 
	 * @param contenido el contenido a agregar
	 * @param token el token de administrador
	 */
	void agregar(Contenido contenido, String token);
	
	/** Elimina el contenido de la lista del servidor. Esta operacion solo
	 *  es posible mediante el uso de un token especial de administracion.
	 * 
	 * @param contenido el contenido a eliminar
	 * @param token el token de administrador
	 */
	void eliminar(Contenido contenido, String token);
	
	/** Devuelve los contenidos que tengan la subcadena en su titulo.
	 * 
	 * @param subcadena la cadena a buscar en el titulo
	 * @param token el token del usuario. Un token incorrecto o nulo anhadira
	 * anuncions por el medio de la lista resultado de la busqueda
	 * @return la lista de contenidos que contienen la subcadena en el titulo
	 */
	List<Contenido> buscar(String subcadena, String token);
}
