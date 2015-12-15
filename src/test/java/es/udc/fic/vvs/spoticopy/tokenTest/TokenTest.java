package es.udc.fic.vvs.spoticopy.tokenTest;

import static org.junit.Assert.*;

import org.junit.Test;

import es.udc.fic.vvs.spoticopy.token.Token;

public class TokenTest {

	Token token = new Token("ADMIN");

	@Test
	public void altaTest() {
            
            String resultado1 = token.alta();
            assertTrue(token.contains(resultado1));

        }
	
        @Test
	public void bajaTest() {
            String resultado2 = token.alta();
            token.baja(resultado2);
            assertTrue(!token.contains("ADMIN"));
        }
        
}
