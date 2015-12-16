package es.udc.fic.vvs.spoticopy.servidor;

import java.util.ArrayList;
import java.util.List;

import es.udc.fic.vvs.spoticopy.contenido.Anuncio;
import es.udc.fic.vvs.spoticopy.contenido.Contenido;
import es.udc.fic.vvs.spoticopy.token.Token;

/**
 * La peculiaridad de un ServidorConPadres es que, de no poder devolver
 * contenidos que cumplan el criterio de busqueda, solicita los contenidos de
 * otro servidor, llamado padre, y devuelve lo que el le proporcione. Cualquier
 * servidor puede ser el padre, no necesariamente otro Servidor ConPadres
 * (aunque seria posible montarse arboles, listas o mallas de Servidores). OJO,
 * la implementacion no contempla el caso de un anillo de servidores, intentarlo
 * creara un bucle infinito.
 */
public class ServidorConPadres extends ServidorGenerico {

    /**
     * El servidor padre al que solicitar contenidos.
     */
    private final Servidor padre;

    /** Crea un servidor que pide los contenidos a un segundo servidor (padre)
     *  en caso de no disponer de ninguno que cumpla con la peticion de busqueda.
     * 
     * @param nombre El nombre del servidor
     * @param padre El servidor padre
     * @param contenidos Contenidos iniciales
     * @param token Tokens que usara el servidor
     */
    public ServidorConPadres(final String nombre, final Servidor padre,
            final List<Contenido> contenidos, final Token token) {
        super(nombre, contenidos, token);
        this.padre = padre;
    }

    /**
     * Busca contenidos que contengan la subcadena indicada.
     * 
     * @param subcadena La subcadena a comprobar.
     * @param tok Un token para no incluir anuncios.
     * @return Contenidos resultado de la busqueda.
     */
    public List<Contenido> buscar(final String subcadena, final String tok) {
        List<Contenido> resultado = new ArrayList<Contenido>();
        final int espacio = 3;
        Token token = getToken();
        List<Contenido> contenidos = getContenidos();
        
        boolean hayToken = (token.contains(tok) || token.isAdminToken(tok));

        // Lista de contenidos que contiene la subcadena
        List<Contenido> busqueda = new ArrayList<Contenido>();
        for (Contenido i : contenidos) {
            if (i.obtenerTitulo().contains(subcadena)) {
                busqueda.add(i);
            }
        }

        // Envío final
        int interv = 0;
        if (!hayToken) {
            resultado.add(new Anuncio());
        }
        for (Contenido i : busqueda) {
            resultado.add(i);
            if (!hayToken) {
                interv++;
                if (interv >= espacio) {
                    resultado.add(new Anuncio());
                    interv = 0;
                }
            }
        }

        // Usamos al padre si el contenido está vacío
        if (resultado.isEmpty()) {
            resultado = padre.buscar(subcadena, tok);
        } else {
            // Reducimos un uso al token
            token.usarToken(tok);
        }

        return resultado;
    }

}
