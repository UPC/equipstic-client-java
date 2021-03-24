package edu.upc.caminstech.equipstic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.util.StringUtils;

class CategoriaTests {

    private Categoria categoria;

    @BeforeEach
    public void setUp() throws Exception {
        categoria = new Categoria(1, "Categoria 1", "C1");
    }

    @Test
    void testCategoria() {
        assertNotNull(categoria);
    }

    @Test
    void testGetIdCategoria() {
        assertEquals(1, categoria.getIdCategoria());
    }

    @Test
    void testGetNom() {
        assertEquals("Categoria 1", categoria.getNom());
    }

    @Test
    void testGetCodi() {
        assertEquals("C1", categoria.getCodi());
    }

    @Test
    void testToString() {
        assertTrue(StringUtils.substringMatch(categoria.toString(), 0, "[Categoria "));
    }

    @Test
    void testEquals() {
        Categoria copy = new Categoria(1, "Categoria 1", "C1");
        assertEquals(categoria, copy);
    }

    @Test
    void testNotEquals() {
        Categoria c2 = new Categoria(2, "Categoria 2", "C2");
        assertNotEquals(categoria, c2);
    }

    @Test
    void testHashCode() {
        Categoria copy = new Categoria(1, "Categoria 1", "C1");
        assertEquals(copy.hashCode(), categoria.hashCode());
    }

}
