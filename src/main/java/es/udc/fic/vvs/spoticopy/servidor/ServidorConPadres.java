package es.udc.fic.vvs.spoticopy.servidor;

import java.util.ArrayList;
import java.util.List;

import es.udc.fic.vvs.spoticopy.contenido.Anuncio;
import es.udc.fic.vvs.spoticopy.contenido.Contenido;

public class ServidorConPadres extends GenericServidor implements Servidor {

	private Servidor padre;
	
	public ServidorConPadres(String nombre, Servidor padre,
			String admin_token, List<Contenido> contenidos) {
		super(nombre, admin_token, contenidos);
		this.padre = padre;
	}

	public List<Contenido> buscar(String subcadena, String token) {
		List<Contenido> resultado = new ArrayList<Contenido>();
		boolean hay_token = false;
		
		if(token != null) {
			if(tokens.contains(token)) {
				hay_token = true;
			}
		}
		
		// Lista de contenidos que contiene la subcadena
		List<Contenido> busqueda = new ArrayList<Contenido>();
		for(Contenido i : contenidos) {
			if(i.obtenerTitulo().contains(subcadena)) {
				busqueda.add(i);
			}
		}
		
		// Envío final
		int intervalo = 0;
		for(Contenido i : busqueda) {
			if(!hay_token && intervalo == 0) {
				resultado.add(new Anuncio());
			}
			if(i.obtenerTitulo().contains(subcadena)) {
				resultado.add(i);
			}	
			intervalo++;
			if(intervalo >= 3) {
				intervalo = 0;
			}
		}
		
		// Usamos al padre si el contenido está vacío
		if(resultado.isEmpty()) {
			resultado = padre.buscar(subcadena, token);
		}
		
		return resultado;
	}

}
