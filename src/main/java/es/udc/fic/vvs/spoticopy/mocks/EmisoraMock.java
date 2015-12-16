package es.udc.fic.vvs.spoticopy.mocks;

import java.util.ArrayList;
import java.util.List;

import es.udc.fic.vvs.spoticopy.contenido.Contenido;

public class EmisoraMock implements Contenido{

	private String titulo;
	private int duracion;
	private ArrayList<Contenido> listaReproduccion;

	@Override
	public String obtenerTitulo() {
		return titulo;
	}

	@Override
	public int obtenerDuracion() {
		return duracion;
	}

	@Override
	public List<Contenido> obtenerListaReproduccion() {
		return listaReproduccion;
	}

	@Override
	public List<Contenido> buscar(String subcadena) {
		List<Contenido> lista = new ArrayList<Contenido>();
		return lista;
	}

	@Override
	public void agregar(Contenido contenido, Contenido predecesor) {
	}

	@Override
	public void eliminar(Contenido contenido) {
	}
}
