package es.udc.fic.vvs.spoticopy.mocks;

import java.util.ArrayList;
import java.util.List;

import es.udc.fic.vvs.spoticopy.contenido.Contenido;

public class CancionMock implements Contenido {

    String titulo;
    int duracion;
    ArrayList<Contenido> listaReproduccion;

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
        return listaReproduccion;
    }

    @Override
    public void agregar(Contenido contenido, Contenido predecesor) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void eliminar(Contenido contenido) {
        throw new UnsupportedOperationException();

    }

}
