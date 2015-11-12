package es.udc.fic.vvs.spoticopy.servidor;

import java.util.List;

import es.udc.fic.vvs.spoticopy.contenido.Contenido;

public interface Servidor {

	/** Devuelve el nombre con el que identificar al servidor
	 * 
	 *  @return el nombre del servidor
	 */
	public String obtenerNombre();
	
	/** Genera un token para obtener contenidos del servidor libres de
	 *  anuncios. Tiene un número limitado de usos.
	 *  
	 *  @return el token generado
	 */
	public String alta();
	
	/** Elimina un token pregenerado. ESTO LO HARA AUNQUE LE QUEDEN USOS
	 *  RESTANTES, SOLO USAR SI ES SEGURO QUE NO SE QUIERE SEGUIR USANDO
	 *
	 * @param token El token a dar de baja
	 */
	public void baja(String token);
	
	/** Agrega el contenido a la lista del servidor. Esta operacion solo
	 *  es posible mediante el uso de un token especial de administracion.
	 * 
	 * @param contenido el contenido a agregar
	 * @param token el token de administrador
	 */
	public void agregar(Contenido contenido, String token);
	
	/** Elimina el contenido de la lista del servidor. Esta operacion solo
	 *  es posible mediante el uso de un token especial de administracion.
	 * 
	 * @param contenido el contenido a eliminar
	 * @param token el token de administrador
	 */
	public void eliminar(Contenido contenido, String token);
	
	/** Devuelve los contenidos que tengan la subcadena en su titulo.
	 * 
	 * @param subcadena la cadena a buscar en el titulo
	 * @param token el token del usuario. Un token incorrecto o nulo anhadira
	 * anuncions por el medio de la lista resultado de la busqueda
	 * @return la lista de contenidos que contienen la subcadena en el titulo
	 */
	public List<Contenido> buscar(String subcadena, String token);
}
