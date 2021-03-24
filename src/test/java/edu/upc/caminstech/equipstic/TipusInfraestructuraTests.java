package edu.upc.caminstech.equipstic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.util.StringUtils;

class TipusInfraestructuraTests {

    private static final TipusInfraestructura PORTATIL = new TipusInfraestructura(0, "Portatil", "PORTATIL", true,
            new Categoria(0, "Equip treball", "EQUIP_TREBALL"));

    private static final TipusInfraestructura MONITOR = new TipusInfraestructura(0, "Monitor", "MONITOR", true,
            new Categoria(0, "Equip treball", "EQUIP_TREBALL"));

    private TipusInfraestructura tipus;
    private TipusInfraestructura copia;

    private Categoria cat;

    @BeforeEach
    public void setUp() throws Exception {
        cat = new Categoria(1, "CAT1", "C1");
        tipus = new TipusInfraestructura(1, "TIPUS 1", "T1", true, cat);
        copia = new TipusInfraestructura(1, "TIPUS 1", "T1", true, cat);
    }

    @Test
    void testTipusInfraestructura() {
        assertNotNull(tipus);
    }

    @Test
    void testGetCategoriaInfraestructura() {
        assertEquals(cat, tipus.getCategoriaInfraestructura());
    }

    @Test
    void testGetIdTipus() {
        assertEquals(1, tipus.getIdTipus());
    }

    @Test
    void testGetNom() {
        assertEquals("TIPUS 1", tipus.getNom());
    }

    @Test
    void testGetCodi() {
        assertEquals("T1", tipus.getCodi());
    }

    @Test
    void testIsRequereixCampsExtra() {
        assertTrue(tipus.isRequereixCampsExtra());
    }

    @Test
    void testToString() {
        assertTrue(StringUtils.substringMatch(tipus.toString(), 0, "[TipusInfraestructura "));
    }

    @Test
    void testEquals() {
        assertEquals(tipus, copia);
    }

    @Test
    void testEqualsFalse() {
        TipusInfraestructura tipus2 = new TipusInfraestructura(2, "TIPUS 2", "T2", true, cat);
        assertNotEquals(tipus, tipus2);
    }

    @Test
    void testHashCode() {
        assertEquals(tipus.hashCode(), copia.hashCode());
    }

    @Test
    void testCalModelCpuForPortatil() {
        assertTrue(PORTATIL.calModelCpu());
    }

    @Test
    void testCalModelCpuForMonitor() {
        assertFalse(MONITOR.calModelCpu());
    }

}
