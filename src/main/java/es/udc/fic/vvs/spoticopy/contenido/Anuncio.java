package es.udc.fic.vvs.spoticopy.contenido;

import java.util.ArrayList;
import java.util.List;

/**
 * Un anuncio es un contenido que siempre tiene por titulo "PUBLICIDAD" y de
 * duracion 5. Redefine el equals, de tal modo que todo anuncio se considera
 * igual a otro anuncio.
 */
public class Anuncio implements Contenido {

    /** Anuncio tiene un título fijo: "PUBLICIDAD".
     */
    private static final String TITULO = "PUBLICIDAD";
    /** Anuncio tiene una duración fija de 5 segundos.
     */
    private static final int DURACION = 5;

    /**
     * Obtiene el titulo del anuncio (un anuncio siempre tiene "PUBLICIDAD" por
     * nombre.
     *
     * @return el titulo del anuncio, "PUBLICIDAD"
     */
    public String obtenerTitulo() {
        return TITULO;
    }

    /**
     * Obtiene la duracion del anuncio (un anuncio siempre dura 5 segundos).
     *
     * @return la duracion del anuncio, 5
     */
    public int obtenerDuracion() {
        return DURACION;
    }

    /**
     * Un anuncio se devuelve a si mismo dentro de una lista.
     *
     * @return Una lista de contenidos con el anuncio dentro
     */
    public List<Contenido> obtenerListaReproduccion() {
        List<Contenido> lista = new ArrayList<Contenido>();
        lista.add(this);
        return lista;
    }

    /**
     * Si la subcadena que le pasamos está contenida en el titulo del anuncio,
     * lo devolvemos. Sino devolvemos una lista vacía.
     *
     * @param subcadena la subcadena a buscar
     * @return la cancion, si contiene la subcadena en su titulo. Lista vacia en
     * caso contrario.
     */
    public List<Contenido> buscar(final String subcadena) {
        List<Contenido> lista = new ArrayList<Contenido>();
        if (TITULO.contains(subcadena)) {
            lista.add(this);
        }
        return lista;
    }

    /**
     * Anuncio no hace uso de esta funcion. De llamarse no hara absolutamente
     * nada.
     * @param contenido El contenido a "agregar".
     * @param predecesor El contenido predecesor.
     */
    public void agregar(final Contenido contenido, final Contenido predecesor) {
    }

    /**
     * Anuncio no hace uso de esta funcion. De llamarse no hara absolutamente
     * nada.
     * @param contenido El contenido a "eliminar".
     */
    public void eliminar(final Contenido contenido) {
    }

    /**
     * Comprueba si dos objetos son iguales.
     * @param obj El objeto con el que compararlo
     * @return Si los objetos son iguales
     */
    @Override
    public boolean equals(final Object obj) {
        
        // Si son de la misma clase (Anuncio), son iguales.
        return (obj != null) && (this.getClass() == obj.getClass());
    }

    /**
     * Genera un codigo hash del objeto.
     * @return Hash del objeto.
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.TITULO.hashCode();
        hash = 53 * hash + this.DURACION;
        return hash;
    }
    
}
