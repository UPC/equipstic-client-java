package edu.upc.caminstech.equipstic;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.util.StringUtils;

public class UnitatTests {

    private Unitat unitat;
    private Unitat copia;

    @Before
    public void setUp() throws Exception {
        unitat = new Unitat(1, "171", "UTGAC", "Utg de l'Àmbit de Camins");
        copia = new Unitat(1, "171", "UTGAC", "Utg de l'Àmbit de Camins");
    }

    @Test
    public void testUnitat() {
        assertNotNull(unitat);
    }

    @Test
    public void testGetIdUnitat() {
        assertEquals(1, unitat.getIdUnitat());
    }

    @Test
    public void testGetCodiUnitat() {
        assertEquals("171", unitat.getCodiUnitat());
    }

    @Test
    public void testGetIdentificador() {
        assertEquals("UTGAC", unitat.getIdentificador());
    }

    @Test
    public void testGetNom() {
        assertEquals("Utg de l'Àmbit de Camins", unitat.getNom());
    }

    @Test
    public void testToString() {
        assertTrue(StringUtils.substringMatch(unitat.toString(), 0, "[Unitat "));
    }

    @Test
    public void testEquals() {
        assertEquals(unitat, copia);
    }

    @Test
    public void testEqualsFalse() {
        Unitat unitat2 = new Unitat(2, "codi", "ident", "nom");
        assertNotEquals(unitat, unitat2);
    }

    @Test
    public void testHashCode() {
        assertEquals(unitat.hashCode(), copia.hashCode());
    }

}
