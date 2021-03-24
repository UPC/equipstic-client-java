package edu.upc.caminstech.equipstic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.util.StringUtils;

class TipusXarxaTests {

    private TipusXarxa tipus;
    private TipusXarxa copia;

    @BeforeEach
    public void setUp() throws Exception {
        tipus = new TipusXarxa(3, "Local", "LOCAL");
        copia = new TipusXarxa(3, "Local", "LOCAL");
    }

    @Test
    void testTipusXarxa() {
        assertNotNull(tipus);
    }

    @Test
    void testGetIdTipusXarxa() {
        assertEquals(3, tipus.getIdTipusXarxa());
    }

    @Test
    void testGetNom() {
        assertEquals("Local", tipus.getNom());
    }

    @Test
    void testGetCodi() {
        assertEquals("LOCAL", tipus.getCodi());
    }

    @Test
    void testToString() {
        assertTrue(StringUtils.substringMatch(tipus.toString(), 0, "[TipusXarxa "));
    }

    @Test
    void testEquals() {
        assertEquals(tipus, copia);
    }

    @Test
    void testEqualsFalse() {
        TipusXarxa tipus2 = new TipusXarxa(0, "Dummy", "DUMMY");
        assertNotEquals(tipus, tipus2);
    }

    @Test
    void testHashCode() {
        assertEquals(tipus.hashCode(), copia.hashCode());
    }

}
