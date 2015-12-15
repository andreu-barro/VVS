package es.udc.fic.vvs.spoticopy.contenido;

import java.util.ArrayList;
import java.util.List;

/**
 * Una emisora es un contenido que tiene otros contenidos dentro.
 */
public class Emisora implements Contenido {

    /**
     * Titulo de la emisora.
     */
    private final String titulo;
    /**
     * Duracion de su contenido.
     */
    private int duracion;
    /**
     * Una emisora contiene una lista de otros contenidos.
     */
    private List<Contenido> listaContenidos;

    /**
     * Una emisora es un contenido que tiene otros contenidos dentro.
     *
     * @param titulo El titulo de la emisora.
     * @param listaContenidos La lista de contenidos.
     */
    public Emisora(final String titulo, final List<Contenido> listaContenidos) {
        this.titulo = titulo;
        this.duracion = 0;
        if (listaContenidos != null) {
            this.listaContenidos = listaContenidos;
            for (Contenido i : listaContenidos) {
                this.duracion += i.obtenerDuracion();
            }
        } else {
            this.listaContenidos = new ArrayList<Contenido>();
        }
    }

    /**
     * Devuelve el nombre de la emisora.
     *
     * @return el titulo de la emisora
     */
    public String obtenerTitulo() {
        return titulo;
    }

    /**
     * Devuelve la duracion del contenido que ofrece la emisora.
     *
     * @return la duracion del contenido de la emisora
     */
    public int obtenerDuracion() {
        return duracion;
    }

    /**
     * Devuelve la lista de contenidos que ofrece la emisora.
     *
     * @return la lista de contenidos de la emisora
     */
    public List<Contenido> obtenerListaReproduccion() {
        return this.listaContenidos;
    }

    /**
     * Devuelve los contenidos de la emisora que tengan la subcadena en su
     * titulo.
     *
     * @param subcadena la cadena a buscar en el titulo
     * @return la lista de contenidos que contienen la subcadena en el titulo
     */
    public List<Contenido> buscar(final String subcadena) {
        List<Contenido> lista = new ArrayList<Contenido>();
        if (this.titulo.contains(subcadena)) {
            lista.add(this);
        }
        return lista;
    }

    /**
     * Agrega el contenido en la lista DESPUES del elemento predecesor. De no
     * encontrarse el predecesor se insertara al inicio. Notese que se puede
     * introducir contenidos repetidos sin problema.
     *
     * @param contenido el contenido a agregar
     * @param predecesor el predecesor que debe tener
     */
    public void agregar(final Contenido contenido, final Contenido predecesor) {

        // No se indica predecesor
        if (predecesor == null) {
            this.listaContenidos.add(0, contenido);
            this.duracion += contenido.obtenerDuracion();
        } else if (this.listaContenidos.contains(predecesor)) {
            // Si tenemos el predecesor, insertamos el contenido
            // en la posicion siguiente. Suponemos que la siguiente
            // posicion esta vacia
            int pos = listaContenidos.indexOf(predecesor);
            this.listaContenidos.add(pos + 1, contenido);
            this.duracion += contenido.obtenerDuracion();
        } else {
            // En cualquier otro caso, entra en primera posicion
            this.listaContenidos.add(0, contenido);
            this.duracion += contenido.obtenerDuracion();
        }

    }

    /**
     * Elimina el contenido indicado de la lista. De estar repetido elimina la
     * primera aparicion en la lista.
     *
     * @param contenido el contenido a eliminar
     */
    public void eliminar(final Contenido contenido) {

        // Si existe el contenido a eliminar
        if (this.listaContenidos.contains(contenido)) {
            this.listaContenidos.remove(contenido);
            this.duracion -= contenido.obtenerDuracion();
        }
    }
}
