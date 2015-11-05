package es.udc.fic.vvs.spoticopy.contenido;

import java.util.List;

public interface Contenido {

	// Devuelve el nombre del contenido
	public String obtenerTitulo();
	
	// Devuelve la duración del contenido
	public int obtenerDuracion();
	
	// Devuelve la lista de contenidos
	public List<Contenido> obtenerListaReproduccion();
	
	// Devuelve los contenidos que tengan la subcadena en su título
	public List<Contenido> buscar(String subcadena);
	
	// Agrega el contenido en la lista DESPUÉS del elemento predecesor.
	// De no encontrarse el predecesor se insertará al inicio.
	public void agregar(Contenido contenido, Contenido predecesor);
	
	// Elimina el contenido indicado de la lista
	public void eliminar(Contenido contenido);
}
