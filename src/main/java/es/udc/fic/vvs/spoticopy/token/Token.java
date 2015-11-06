package es.udc.fic.vvs.spoticopy.token;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

public class Token {

	private static List<String> tokens;
	private static Token instance = null;
	private static String admin_token;
	private static Random r;
	
	private Token(String admin_t) {
		tokens = new ArrayList<String>();
		admin_token = admin_t;
		// Introducir una semilla concreta si se quiere una generación de
		// tokens predecible entre ejecuciones de la aplicación
		r = new Random(Calendar.getInstance().getTimeInMillis());
	}
	
	// Singleton
	public static Token getInstance() {
		if(instance == null) {
			instance = new Token("ADMINISTRADOR");
		}
		return instance;
	}
	
	public String alta() {
		String token = null;
		
		while(token == null) {
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
			if(tokens.contains(token)
			|| token.contentEquals(admin_token)) {
				token = null;
			} else {
				tokens.add(token);
			}
		}
		return token; 
	}

	public void baja(String token) {
		tokens.remove(token);
	}
	
	public boolean isAdminToken(String token) {
		return admin_token.contentEquals(token);
	}
	
	public boolean contains(String token) {
		return tokens.contains(token);
	}
	
}
