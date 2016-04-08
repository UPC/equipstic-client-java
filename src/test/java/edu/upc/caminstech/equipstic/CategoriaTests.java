package edu.upc.caminstech.equipstic;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.util.StringUtils;

public class CategoriaTests {

    private Categoria categoria;

    @Before
    public void setUp() throws Exception {
        categoria = new Categoria(1, "Categoria 1", "C1");
    }

    @Test
    public void testCategoria() {
        assertNotNull(categoria);
    }

    @Test
    public void testGetIdCategoria() {
        assertEquals(1, categoria.getIdCategoria());
    }

    @Test
    public void testGetNom() {
        assertEquals("Categoria 1", categoria.getNom());
    }

    @Test
    public void testGetCodi() {
        assertEquals("C1", categoria.getCodi());
    }

    @Test
    public void testToString() {
        assertTrue(StringUtils.substringMatch(categoria.toString(), 0, "[Categoria "));
    }

    @Test
    public void testEquals() {
        Categoria copy = new Categoria(1, "Categoria 1", "C1");
        assertEquals(categoria, copy);
    }

    @Test
    public void testNotEquals() {
        Categoria c2 = new Categoria(2, "Categoria 2", "C2");
        assertNotEquals(categoria, c2);
    }

    @Test
    public void testHashCode() {
        Categoria copy = new Categoria(1, "Categoria 1", "C1");
        assertEquals(copy.hashCode(), categoria.hashCode());
    }

}
