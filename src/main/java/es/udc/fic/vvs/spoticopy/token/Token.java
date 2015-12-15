package es.udc.fic.vvs.spoticopy.token;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

/**
 * Una clase token reune todos los tokens generados por todos los servidores. Un
 * token es una cadena que, de usarse al realizar una busqueda en un servidor,
 * impide la introduccion de anuncios en el resultado, un numero limitado de
 * veces. Puede asignarse una instancia de Token a varios servidores, haciendo
 * que los tokens generados en cualquier servidor sea usable por el resto, pero
 * NO se puede instanciar varios token para que sean exclusivos los unos de los
 * otros.
 */
public class Token {

    /**
     * Numero maximo de usos de un token (salvo el de administracion).
     */
    private static final Long MAX_USES = 10L;

    /**
     * Lista de tokens validos.
     */
    private static List<String> tokens;
    /**
     * Numero de usos restantes a los tokens.
     */
    private static List<Long> usos;
    /**
     * Token de administracion (usos infinitos).
     */
    private static String adminToken;
    /**
     * Semilla de generacion de tokens.
     */
    private static Random r;

    /**
     * Sistema de tokens.
     * @param admin El token con permisos de administracion.
     */
    public Token(final String admin) {
        tokens = new ArrayList<String>();
        usos = new ArrayList<Long>();
        adminToken = admin;
        // Introducir una semilla concreta si se quiere una generación de
        // tokens predecible entre ejecuciones de la aplicación
        r = new Random(Calendar.getInstance().getTimeInMillis());
    }

    /**
     * Genera un nuevo token y lo agrega a su lista.
     * @return El token generado.
     */
    public String alta() {
        String token = null;

        while (token == null) {
            try {
                final int tam = 16;
                byte[] bytes = new byte[tam];
                r.nextBytes(bytes);
                token = new String(bytes, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                return null;
            }
            // Ha generado un token que ya existe
            // o el token de administración
            if (tokens.contains(token)
                    || token.contentEquals(adminToken)) {
                token = null;
            } else {
                tokens.add(token);
                usos.add(MAX_USES);
            }
        }
        return token;
    }

    /**
     * Da de baja directamente un token sin importar sus usos restantes.
     * @param token El token a dar de baja.
     */
    public void baja(final String token) {
        int index = tokens.indexOf(token);
        tokens.remove(index);
        usos.remove(index);
    }

    /**
     * Comprueba si un token es de administracion.
     * @param token El token a comprobar.
     * @return Si el token es de administracion.
     */
    public boolean isAdminToken(final String token) {
        return adminToken.contentEquals(token);
    }

    /**
     * Comprueba si un token pertenece al sistema.
     * @param token El token a buscar.
     * @return Si el token esta presente.
     */
    public boolean contains(final String token) {
        return tokens.contains(token);
    }

    /**
     * Gasta un uso del token.
     * @param token Token a usar.
     */
    public void usarToken(final String token) {
        int index = tokens.indexOf(token);
        Long uso = usos.get(index);
        --uso;
        if (uso == 0) {
            tokens.remove(index);
            usos.remove(index);
        } else {
            usos.set(index, uso);
        }
    }
}
