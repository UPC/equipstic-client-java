package edu.upc.caminstech.equipstic;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.util.StringUtils;

public class TipusXarxaTests {

    private TipusXarxa tipus;
    private TipusXarxa copia;

    @Before
    public void setUp() throws Exception {
        tipus = new TipusXarxa(3, "Local", "LOCAL");
        copia = new TipusXarxa(3, "Local", "LOCAL");
    }

    @Test
    public void testTipusXarxa() {
        assertNotNull(tipus);
    }

    @Test
    public void testGetIdTipusXarxa() {
        assertEquals(3, tipus.getIdTipusXarxa());
    }

    @Test
    public void testGetNom() {
        assertEquals("Local", tipus.getNom());
    }

    @Test
    public void testGetCodi() {
        assertEquals("LOCAL", tipus.getCodi());
    }

    @Test
    public void testToString() {
        assertTrue(StringUtils.substringMatch(tipus.toString(), 0, "[TipusXarxa "));
    }

    @Test
    public void testEquals() {
        assertEquals(tipus, copia);
    }

    @Test
    public void testEqualsFalse() {
        TipusXarxa tipus2 = new TipusXarxa(0, "Dummy", "DUMMY");
        assertNotEquals(tipus, tipus2);
    }

    @Test
    public void testHashCode() {
        assertEquals(tipus.hashCode(), copia.hashCode());
    }

}
