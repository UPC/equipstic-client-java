package edu.upc.caminstech.equipstic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SistemaOperatiuTests {

    private SistemaOperatiu so;

    @BeforeEach
    public void setUp() {
        so = new SistemaOperatiu(2L, "Linux", "LINUX", categoriaFixture());
    }

    private Categoria categoriaFixture() {
        return new Categoria(1, "Equip de computació", "EQUIP_COMPUTACIO");
    }

    @Test
    void testSistemaOperatiu() {
        assertNotNull(so);
    }

    @Test
    void testGetIdSistemaOperatiu() {
        assertEquals(2, so.getIdSistemaOperatiu());
    }

    @Test
    void testGetNom() {
        assertEquals("Linux", so.getNom());
    }

    @Test
    void testGetCodi() {
        assertEquals("LINUX", so.getCodi());
    }

    @Test
    void testGetCategoriaInfraestructura() {
        assertEquals(categoriaFixture(), so.getCategoriaInfraestructura());
    }

    @Test
    void testToString() {
        assertTrue(so.toString().startsWith("[SistemaOperatiu "));
    }

    @Test
    void testEqualsObject() {
        SistemaOperatiu other = new SistemaOperatiu(2, "dummy", "DUMMY", categoriaFixture());
        assertEquals(other, so);
    }

    @Test
    void testHashCode() {
        assertEquals(Long.hashCode(2L), so.hashCode());
    }

}
