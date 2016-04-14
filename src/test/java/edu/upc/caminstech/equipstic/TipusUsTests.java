package edu.upc.caminstech.equipstic;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.util.StringUtils;

public class TipusUsTests {

    private TipusUs tipus;
    private TipusUs copia;
    private Unitat unitat;

    @Before
    public void setUp() throws Exception {
        unitat = new Unitat(1, "1", "U1", "UNITAT1");
        tipus = new TipusUs(1, "TIPUS_US1", unitat);
        copia = new TipusUs(1, "TIPUS_US1", unitat);
    }

    @Test
    public void testTipusUs() {
        assertNotNull(tipus);
    }

    @Test
    public void testGetIdTipusUs() {
        assertEquals(1, tipus.getIdTipusUs());
    }

    @Test
    public void testGetNom() {
        assertEquals("TIPUS_US1", tipus.getNom());
    }

    @Test
    public void testGetUnitat() {
        assertEquals(unitat, tipus.getUnitat());
    }

    @Test
    public void testToString() {
        assertTrue(StringUtils.substringMatch(tipus.toString(), 0, "[TipusUs "));
    }

    @Test
    public void testEquals() {
        assertEquals(tipus, copia);
    }

    @Test
    public void testEqualsFalse() {
        TipusUs tipus2 = new TipusUs(2, "DUMMY", null);
        assertNotEquals(tipus, tipus2);
    }

    @Test
    public void testHashCode() {
        assertEquals(tipus.hashCode(), copia.hashCode());
    }

}
