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

    private final static Long MAX_USES = 10L;
<<<<<<< HEAD

    private static List<String> tokens;
    private static List<Long> usos;
    private static String adminToken;
    private static Random r;

    public Token(String admin_t) {
        tokens = new ArrayList<String>();
        usos = new ArrayList<Long>();
        adminToken = admin_t;
		// Introducir una semilla concreta si se quiere una generación de
        // tokens predecible entre ejecuciones de la aplicación
        r = new Random(9999);
    }

    public String alta() {
        String token = null;

=======

    private List<String> tokens;
    private List<Long> usos;
    private String adminToken;
    private Random r;

    public Token(String admin_t) {
        tokens = new ArrayList<String>();
        usos = new ArrayList<Long>();
        adminToken = admin_t;
        // Introducir una semilla concreta si se quiere una generación de
        // tokens predecible entre ejecuciones de la aplicación
        r = new Random(Calendar.getInstance().getTimeInMillis());
    }

    public String alta() {
        String token = null;

>>>>>>> e396f952ca81c31055eefce8a40c206730ba918d
        while (token == null) {
            try {
                byte[] bytes = new byte[16];
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

    public void baja(String token) {
        int index = tokens.indexOf(token);
        tokens.remove(index);
        usos.remove(index);
    }

    public boolean isAdminToken(String token) {
        return adminToken.contentEquals(token);
    }

    public boolean contains(String token) {
        return tokens.contains(token);
    }

    public void usarToken(String token) {
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
