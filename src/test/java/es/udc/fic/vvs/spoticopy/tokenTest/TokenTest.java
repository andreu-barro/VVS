package es.udc.fic.vvs.spoticopy.tokenTest;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import es.udc.fic.vvs.spoticopy.contenido.Anuncio;
import es.udc.fic.vvs.spoticopy.contenido.Contenido;
import es.udc.fic.vvs.spoticopy.token.Token;
import java.io.UnsupportedEncodingException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TokenTest {

	Token token = new Token("ADMIN");

	@Test
	public void altaTest() {
            String resultado1="";
            Random r = new Random(9999);
            byte[] bytes = new byte[16];
            r.nextBytes(bytes);
            try {
                resultado1 = new String(bytes, "UTF-8");
            } catch (UnsupportedEncodingException ex) {
            }
            
            String resultado2 = token.alta();
            assertTrue(resultado1.contentEquals(resultado2));

        }
	
        @Test
	public void bajaTest() {
            String resultado2 = token.alta();
            token.baja(resultado2);
            assertTrue(!token.contains("ADMIN"));
        }
        
}
