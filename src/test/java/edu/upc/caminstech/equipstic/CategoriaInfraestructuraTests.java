package edu.upc.caminstech.equipstic;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.util.StringUtils;

public class CategoriaInfraestructuraTests {

    private CategoriaInfraestructura cat;
    private CategoriaInfraestructura copia;

    @Before
    public void setUp() throws Exception {
        cat = new CategoriaInfraestructura(1, "CATEGORIA 1", "CAT1");
        copia = new CategoriaInfraestructura(1, "CATEGORIA 1", "CAT1");
    }

    @Test
    public void testCategoriaInfraestructura() {
        assertNotNull(cat);
    }

    @Test
    public void testGetIdCategoria() {
        assertEquals(1, cat.getIdCategoria());
    }

    @Test
    public void testGetNom() {
        assertEquals("CATEGORIA 1", cat.getNom());
    }

    @Test
    public void testGetCodi() {
        assertEquals("CAT1", cat.getCodi());
    }

    @Test
    public void testToString() {
        assertTrue(StringUtils.substringMatch(cat.toString(), 0, "[CategoriaInfraestructura"));
    }

    @Test
    public void testEquals() {
        assertEquals(cat, copia);
    }

    @Test
    public void testEqualsFalse() {
        CategoriaInfraestructura cat2 = new CategoriaInfraestructura(2, "CATEGORIA 2", "CAT2");
        assertNotEquals(cat, cat2);
    }

    @Test
    public void testHashCode() {
        assertEquals(cat.hashCode(), copia.hashCode());
    }

}
