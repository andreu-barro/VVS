package es.udc.fic.vvs.spoticopy.contenido;

import java.util.ArrayList;
import java.util.List;

/** Contenido con titulo y duracion.
 */
public class Cancion implements Contenido {

    /** Titulo de la cancion.
     */
    private final String titulo;
    /** Duracion de la cancion.
     */
    private final int duracion;

    /**
     * Crea como contenido una cancion.
     * @param titulo El titulo de la cancion.
     * @param duracion La duracion de la cancion.
     */
    public Cancion(final String titulo, final int duracion) {
        this.titulo = titulo;
        this.duracion = duracion;
    }

    /** Devuelve el titulo de la cancion.
     *  @return Titulo de la cancion.
     */
    public String obtenerTitulo() {
        return this.titulo;
    }

    /** Devuelve la duracion de la cancion.
     * @return Duracion de la cancion.
     */
    public int obtenerDuracion() {
        return this.duracion;
    }

    /**
     * Una cancion se devuelve a si mismo dentro de una lista.
     * @return Una lista de contenidos con el anuncio dentro
     */
    public List<Contenido> obtenerListaReproduccion() {

        List<Contenido> contenido = new ArrayList<Contenido>();
        contenido.add(this);
        return contenido;
    }

    /**
     * Si la subcadena que le pasamos está contenida en el titulo de
     * la cancion, lo devolvemos. Sino devolvemos una lista vacía.
     *
     * @param subcadena la subcadena a buscar
     * @return La cancion, si contiene la subcadena en su titulo.
     *         Lista vacia en caso contrario.
     */
    public List<Contenido> buscar(final String subcadena) {
        List<Contenido> contenido = new ArrayList<Contenido>();
        if (this.titulo.contains(subcadena)) {
            contenido.add(this);
        }
        return contenido;
    }

    /**
     * Cancion no hace uso de esta funcion. De llamarse no hara
     * absolutamente nada.
     * @param contenido El "contenido" a agregar
     * @param predecesor El "predecesor" al contenido.
     */
    public void agregar(final Contenido contenido, final Contenido predecesor) {
    }

    /**
     * Cancion no hace uso de esta funcion. De llamarse no hara absolutamente
     * nada.
     * @param contenido El "contenido" a eliminar
     */
    public void eliminar(final Contenido contenido) {
    }

}
