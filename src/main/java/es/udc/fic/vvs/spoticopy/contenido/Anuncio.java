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
    private final String titulo = "PUBLICIDAD";
    /** Anuncio tiene una duración fija de 5 segundos.
     */
    private final int duracion = 5;

    /**
     * Obtiene el titulo del anuncio (un anuncio siempre tiene "PUBLICIDAD" por
     * nombre.
     *
     * @return el titulo del anuncio, "PUBLICIDAD"
     */
    public String obtenerTitulo() {
        return titulo;
    }

    /**
     * Obtiene la duracion del anuncio (un anuncio siempre dura 5 segundos).
     *
     * @return la duracion del anuncio, 5
     */
    public int obtenerDuracion() {
        return duracion;
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
        if (titulo.contains(subcadena)) {
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

    @Override
    public boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Anuncio other = (Anuncio) obj;
        if (!this.titulo.equals(other.titulo)) {
            return false;
        }
        return this.duracion == other.duracion;
    }

    /**
     * Sobreescrito el equals de Anuncio, para que iguale segun su contenido.
     *
     * @return Si los dos contenidos son anuncios
     */
    @Override
    public int hashCode() {
        int hash = 5;
        int tit = 0;
        if (this.titulo != null) {
            tit = this.titulo.hashCode();
        }
        hash = 29 * hash + tit;
        hash = 29 * hash + this.duracion;
        return hash;
    }
}
