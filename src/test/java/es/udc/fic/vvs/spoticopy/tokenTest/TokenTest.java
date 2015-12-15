package es.udc.fic.vvs.spoticopy.tokenTest;

import static org.junit.Assert.*;

import org.junit.Test;

import es.udc.fic.vvs.spoticopy.token.Token;

public class TokenTest {
	
        @Test
	public void altaTest() {
            Token token = new Token("ADMIN");
            
            String res = token.alta();
            assertTrue(res != null);
            assertNotEquals(res, "ADMIN");
            assertEquals(token.obtenerUsos(res), 10);
            
            // Generamos un segundo token
            String res2 = token.alta();
            assertTrue(res2 != null);
            assertNotEquals(res, res2);
            assertNotEquals(res, "ADMIN");
            assertEquals(token.obtenerUsos(res2), 10);
        }
        
        @Test
        public void bajaTest() {
            Token token = new Token("ADMIN");
            // Recien creado
            String res = token.alta();
            assertTrue(res != null);
            assertEquals(token.obtenerUsos(res), 10);
            // Damos de baja
            token.baja(res);
            assertEquals(token.obtenerUsos(res), 0);
            // Damos de baja un nulo
            token.baja(null);
            // Damos de baja un token que no existe
            token.baja("ADSF");
            // Damos de baja el token de admin
            token.baja("ADMIN");
        }
        
        @Test
	public void adminTokenTest() {
            Token token = new Token("ADMIN");
            
            // Token tal cual
            assertTrue(token.isAdminToken("ADMIN"));
            // Mayusculas y minusculas
            assertFalse(token.isAdminToken("aDmIN"));
            // Token falso
            assertFalse(token.isAdminToken("ASDF"));
            // Token nulo
            assertFalse(token.isAdminToken(null));
            
        }
        
        @Test
        public void containsTest() {
            Token token = new Token("ADMIN");
            
            // El token de admin no es revelado
            assertFalse(token.contains("ADMIN"));
            // Token nulo
            assertFalse(token.contains(null));
            // Token que no contiene
            assertFalse(token.contains("ASDF"));
            
            // Token que si contiene
            String tok = token.alta();
            assertTrue(tok != null);
            assertTrue(token.contains(tok));
        }
        
        @Test
        public void obtenerUsosTest() {
            Token token = new Token("ADMIN");
            
            // Los usos del token de admin son 0
            assertEquals(token.obtenerUsos("ADMIN"), 0);
            // Nulo
            assertEquals(token.obtenerUsos(null), 0);
            // Token que no contiene
            assertEquals(token.obtenerUsos("ASDF"), 0);
            // Token que recien creado
            assertEquals(token.obtenerUsos(token.alta()), 10);
            // Token usado
            String tok = token.alta();
            assertTrue(tok != null);
            token.usarToken(tok);
            token.usarToken(tok);
            assertEquals(token.obtenerUsos(tok), 8);
        }
        
        @Test
        public void usarTokenTest() {
            Token token = new Token("ADMIN");
            
            // Token nulo
            token.usarToken(null);
            assertEquals(token.obtenerUsos(null), 0);
            // Token que no contiene
            token.usarToken("ASDF");
            assertEquals(token.obtenerUsos("ASDF"), 0);
            // Token administrador
            token.usarToken("ADMIN");
            assertEquals(token.obtenerUsos("ADMIN"), 0);
            
            String tok = token.alta();
            assertTrue(tok != null);
            long usosIniciales = token.obtenerUsos(tok);
            for(int i = 0; i < usosIniciales; i++) {
                assertEquals(usosIniciales - i, token.obtenerUsos(tok));
                token.usarToken(tok);
            }
            assertEquals(0, token.obtenerUsos(tok));
        }
}
