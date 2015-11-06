package es.udc.fic.vvs.spoticopy.contenido;

import java.util.ArrayList;
import java.util.List;

//Esta clase implementa las funciones de la interfaz contenido 
public class Cancion implements Contenido {



	private String titulo;
	private int duracion;



	//Contructor vacio
	public Cancion() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	//Constructor del contenido Cancion
	public Cancion(String titulo, int duracion)
	{
		this.titulo = titulo;
		this.duracion = duracion;
	}
	
	//Permite modificar anadir titulo
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	//Permite anadir duracion
	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}
	//Obtenemos el titulo de la cancion
	public String obtenerTitulo() {
		// TODO Auto-generated method stub
		return this.titulo;
	}

	//Obtenemos la duracion de la cacion
	public int obtenerDuracion() {
		// TODO Auto-generated method stub
		return this.duracion;
	}

	//Obtenemos la lista de reproduccion de canciones
	public List<Contenido> obtenerListaReproduccion() {
		
		List<Contenido> contenido = new ArrayList<Contenido>();
		contenido.add(this);
		return contenido;
	}

	//Buscamos si la subcadena la contiene el titutlo, si es asi devolvemos la cancion
	//Sino, devolvemos vacio
	public List<Contenido> buscar(String subcadena) {
		List<Contenido> contenido = new ArrayList<Contenido>();
		if(this.titulo.contains(subcadena))
		{
			contenido.add(this);
		}
		return contenido;
		}

	//El contenido cancion no implementa esta funcion
	public void agregar(Contenido contenido, Contenido predecesor) {
		return;
		
	}

	//El contenido cancion no implementa esta funcion
	public void eliminar(Contenido contenido) {
		return;
	}

}
