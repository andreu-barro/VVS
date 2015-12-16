package es.udc.fic.vvs.spoticopy.servidor;

import java.util.ArrayList;
import java.util.List;

import es.udc.fic.vvs.spoticopy.contenido.Anuncio;
import es.udc.fic.vvs.spoticopy.contenido.Contenido;
import es.udc.fic.vvs.spoticopy.token.Token;

/**
 * El servidor normal simplemente implementa la busqueda ademas de heredar de
 * GenericServidor.
 */
public class ServidorNormal extends ServidorGenerico {

    /**
     * El servidor normal no implementa nada complicado en el metodo de busqueda.
     * @param nombre El nombre del servidor.
     * @param contenidos Su lista de contenidos.
     * @param token Su sistema de tokens.
     */
    public ServidorNormal(final String nombre, 
            final List<Contenido> contenidos, final Token token) {
        super(nombre, contenidos, token);
    }

    /**
     * Busca contenidos que contengan la subcadena indicada en su nombre.
     * @param subcadena La subcadena de la busqueda.
     * @param tok Un token para no incluir anuncios.
     * @return El resultado de la busqueda.
     */
    public List<Contenido> buscar(final String subcadena, final String tok) {
        List<Contenido> resultado = new ArrayList<Contenido>();
        boolean hayToken = false;
        final int espacio = 3;
        Token token = getToken();
        List<Contenido> contenidos = getContenidos();

        if (tok != null) {
            if (token.contains(tok) || token.isAdminToken(tok)) {
                hayToken = true;
            }
        }

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

	// Reducimos un uso al token
        if (hayToken && !token.isAdminToken(tok)) {
            token.usarToken(tok);
        }

        return resultado;
    }

}
