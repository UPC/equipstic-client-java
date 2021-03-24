package edu.upc.caminstech.equipstic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.util.StringUtils;

class UnitatTests {

    private Unitat unitat;
    private Unitat copia;

    @BeforeEach
    public void setUp() throws Exception {
        Estat estat = new Estat(11L);
        unitat = new Unitat(1, "171", "UTGAC", "Utg de l'Àmbit de Camins", estat, true);
        copia = new Unitat(1, "171", "UTGAC", "Utg de l'Àmbit de Camins", estat, true);
    }

    @Test
    void testUnitat() {
        assertNotNull(unitat);
    }

    @Test
    void testGetIdUnitat() {
        assertEquals(1, unitat.getIdUnitat());
    }

    @Test
    void testGetCodiUnitat() {
        assertEquals("171", unitat.getCodiUnitat());
    }

    @Test
    void testGetIdentificador() {
        assertEquals("UTGAC", unitat.getIdentificador());
    }

    @Test
    void testGetNom() {
        assertEquals("Utg de l'Àmbit de Camins", unitat.getNom());
    }

    @Test
    void testGetEstat() {
        assertEquals(new Estat(11L), unitat.getEstat());
    }

    @Test
    void testToString() {
        assertTrue(StringUtils.substringMatch(unitat.toString(), 0, "Unitat["));
    }

    @Test
    void testEquals() {
        assertEquals(unitat, copia);
    }

    @Test
    void testEqualsFalse() {
        Unitat unitat2 = new Unitat(2, "codi", "ident", "nom", new Estat(11L), false);
        assertNotEquals(unitat, unitat2);
    }

    @Test
    void testHashCode() {
        assertEquals(unitat.hashCode(), copia.hashCode());
    }

}
