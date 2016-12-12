package edu.upc.caminstech.equipstic;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class SistemaOperatiuTests {

    private SistemaOperatiu so;

    @Before
    public void setUp() {
        so = new SistemaOperatiu(2L, "Linux", "LINUX", categoriaFixture());
    }

    private Categoria categoriaFixture() {
        return new Categoria(1, "Equip de computació", "EQUIP_COMPUTACIO");
    }

    @Test
    public void testSistemaOperatiu() {
        assertNotNull(so);
    }

    @Test
    public void testGetIdSistemaOperatiu() {
        assertEquals(2, so.getIdSistemaOperatiu());
    }

    @Test
    public void testGetNom() {
        assertEquals("Linux", so.getNom());
    }

    @Test
    public void testGetCodi() {
        assertEquals("LINUX", so.getCodi());
    }

    @Test
    public void testGetCategoriaInfraestructura() {
        assertEquals(categoriaFixture(), so.getCategoriaInfraestructura());
    }

    @Test
    public void testToString() {
        assertTrue(so.toString().startsWith("[SistemaOperatiu "));
    }

    @Test
    public void testEqualsObject() {
        SistemaOperatiu other = new SistemaOperatiu(2, "dummy", "DUMMY", categoriaFixture());
        assertEquals(other, so);
    }

    @Test
    public void testHashCode() {
        assertEquals(Long.hashCode(2L), so.hashCode());
    }

}
