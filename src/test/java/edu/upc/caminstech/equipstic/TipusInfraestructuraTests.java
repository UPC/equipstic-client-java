package edu.upc.caminstech.equipstic;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.util.StringUtils;

public class TipusInfraestructuraTests {

    private TipusInfraestructura tipus;
    private TipusInfraestructura copia;
    private CategoriaInfraestructura cat;

    @Before
    public void setUp() throws Exception {
        cat = new CategoriaInfraestructura(1, "CAT1", "C1");
        tipus = new TipusInfraestructura(1, "TIPUS 1", "T1", true, cat);
        copia = new TipusInfraestructura(1, "TIPUS 1", "T1", true, cat);
    }

    @Test
    public void testTipusInfraestructura() {
        assertNotNull(tipus);
    }

    @Test
    public void testGetCategoriaInfraestructura() {
        assertEquals(cat, tipus.getCategoriaInfraestructura());
    }

    @Test
    public void testGetIdTipus() {
        assertEquals(1, tipus.getIdTipus());
    }

    @Test
    public void testGetNom() {
        assertEquals("TIPUS 1", tipus.getNom());
    }

    @Test
    public void testGetCodi() {
        assertEquals("T1", tipus.getCodi());
    }

    @Test
    public void testIsRequereixCampsExtra() {
        assertTrue(tipus.isRequereixCampsExtra());
    }

    @Test
    public void testToString() {
        assertTrue(StringUtils.substringMatch(tipus.toString(), 0, "[TipusInfraestructura "));
    }

    @Test
    public void testEquals() {
        assertEquals(tipus, copia);
    }

    @Test
    public void testEqualsFalse() {
        TipusInfraestructura tipus2 = new TipusInfraestructura(2, "TIPUS 2", "T2", true, cat);
        assertNotEquals(tipus, tipus2);
    }

    @Test
    public void testHashCode() {
        assertEquals(tipus.hashCode(), copia.hashCode());
    }

}
