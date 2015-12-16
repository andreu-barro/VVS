package es.udc.fic.vvs.spoticopy.servidor;

import java.util.ArrayList;
import java.util.List;

import es.udc.fic.vvs.spoticopy.contenido.Contenido;
import es.udc.fic.vvs.spoticopy.token.Token;

/**
 * Esta clase contiene el comportamiento generico de un servidor. Las
 * particularidades se implementan en distintas clases que heredaran de
 * GenericServidor.
 */
public abstract class ServidorGenerico implements Servidor {

    /**
     * Nombre del servidor.
     */
    private final String nombre;
    /**
     * Contenidos que ofrece el servidor.
     */
    private List<Contenido> contenidos;
    /**
     * Lista de tokens compartida por todos los servidores.
     */
    private Token token = null;

    /**
     * Construye un servidor generico.
     * @param nombre El nombre del servidor.
     * @param contenidos Los contenidos que ofrece.
     * @param token El sistema de tokens que usara.
     */
    protected ServidorGenerico(final String nombre, 
            final List<Contenido> contenidos, final Token token) {
        this.nombre = nombre;
        this.contenidos = contenidos;
        if (contenidos == null) {
            this.contenidos = new ArrayList<Contenido>();
        }
        if (token != null) {
            this.token = token;
        }

    }

    /**
     * Devuelve el nombre del servidor.
     * @return Nombre del servidor.
     */
    public String obtenerNombre() {
        return nombre;
    }

    /**
     * Devuelve todos los contenidos del servidor.
     * @return la lista de contenidos
     */
    protected List<Contenido> getContenidos() {
        return contenidos;
    }
    
    /**
     * Devuelve los tokens del servidor.
     * @return lista de tokens
     */
    protected Token getToken() {
        return token;
    }
    
    /**
     * Da de alta un token en el servidor.
     * @return El token para que lo use el usuario.
     */
    public String alta() {
        return token.alta();
    }

    /**
     * Da de baja un token que ya no quieras usar.
     * @param tok El token a dar de baja.
     */
    public void baja(final String tok) {
        token.baja(tok);
    }

    /**
     * Agregar un contenido a la lista (Sólo administrador).
     * @param contenido El contenido a agregar.
     * @param tok El token de administrador.
     */
    public void agregar(final Contenido contenido, final String tok) {
        // Solo el administrador puede usar este metodo
        if (token.isAdminToken(tok) && contenido != null) {
            contenidos.add(contenido);
        }
    }

    /**
     * Eliminar un contenido de la lista (Sólo administrador).
     * @param contenido El contenido a eliminar.
     * @param tok El token de administrador.
     */
    public void eliminar(final Contenido contenido, final String tok) {
        // Solo el administrador puede usar este metodo
        if (token.isAdminToken(tok)) {
            contenidos.remove(contenido);
        }
    }

}
