/**
 * @author Sr. Javi
 *
 */
package es.udc.fic.vvs.spoticopy.contenido;

import java.util.List;

public class Anuncio implements Contenido{

	private static String titulo = "PUBLICIDAD";
	private static int duracion = 5;
	
	public String obtenerTitulo() {
		return titulo;
	}
	public int obtenerDuracion() {
		return duracion;
	}
	public List<Contenido> obtenerListaReproduccion() {
		return null;
	}
	public List<Contenido> buscar(String subcadena) {
		return null;
	}
	public void agregar(Contenido contenido, Contenido predecesor) {
		return;
	}
	public void eliminar(Contenido contenido) {
		return;
	}

}