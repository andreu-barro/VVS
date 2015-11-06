package es.udc.fic.vvs.spoticopy.servidor;

import java.util.List;

import es.udc.fic.vvs.spoticopy.contenido.Contenido;

public interface Servidor {

	// Devuelve el nombre con el que identificar al servidor
	public String obtenerNombre();
	
	// Genera un token para obtener contenidos del servidor
	// libres de anuncios. Tiene un número limitado de usos.
	public String alta();
	
	// Caduca directamente un token. Es aconsejable usarlo si se tiene la
	// certeza de que no se necesita usar más.
	public void baja(String token);
	
	// Añade contenidos al servidor (USAR TOKEN ESPECIAL DE ADMINISTRADOR)
	public void agregar(Contenido contenido, String token);
	
	// Elimina contenidos al servidor (USAR TOKEN ESPECIAL DE ADMINISTRADOR)
	public void eliminar(Contenido contenido, String token);
	
	// Obtiene los contenidos del servidor cuyo nombre contiene la subcadena
	// dada. Nótese que un token generado tiene un número de usos limitado tras
	// el cual caducará. Un token caducado no devolverá nada. Si no se pasa
	// token, los resultados devueltos contendrán anuncios.
	public List<Contenido> buscar(String subcadena, String token);
}
