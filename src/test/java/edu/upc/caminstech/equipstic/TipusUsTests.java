package edu.upc.caminstech.equipstic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.util.StringUtils;

class TipusUsTests {

    private TipusUs tipus;
    private TipusUs copia;
    private Unitat unitat;

    @BeforeEach
    public void setUp() throws Exception {
        unitat = new Unitat(1, "1", "U1", "UNITAT1", new Estat(11L), false);
        tipus = new TipusUs(1, "TIPUS_US1", unitat);
        copia = new TipusUs(1, "TIPUS_US1", unitat);
    }

    @Test
    void testTipusUs() {
        assertNotNull(tipus);
    }

    @Test
    void testGetIdTipusUs() {
        assertEquals(1, tipus.getIdTipusUs());
    }

    @Test
    void testGetNom() {
        assertEquals("TIPUS_US1", tipus.getNom());
    }

    @Test
    void testGetUnitat() {
        assertEquals(unitat, tipus.getUnitat());
    }

    @Test
    void testToString() {
        assertTrue(StringUtils.substringMatch(tipus.toString(), 0, "[TipusUs "));
    }

    @Test
    void testEquals() {
        assertEquals(tipus, copia);
    }

    @Test
    void testEqualsFalse() {
        TipusUs tipus2 = new TipusUs(2, "DUMMY", null);
        assertNotEquals(tipus, tipus2);
    }

    @Test
    void testHashCode() {
        assertEquals(tipus.hashCode(), copia.hashCode());
    }

}
