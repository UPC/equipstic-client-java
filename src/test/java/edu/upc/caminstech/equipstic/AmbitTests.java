package edu.upc.caminstech.equipstic;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.util.StringUtils;

public class AmbitTests {

    private Ambit ambit1;

    @Before
    public void setUp() throws Exception {
        ambit1 = new Ambit(1, "Ambit 1");
    }

    @Test
    public void testAmbit() {
        assertNotNull(ambit1);
    }

    @Test
    public void testGetIdAmbit() {
        assertEquals(1, ambit1.getIdAmbit());
    }

    @Test
    public void testGetNom() {
        assertEquals("Ambit 1", ambit1.getNom());
    }

    @Test
    public void testToString() {
        String s = ambit1.toString();
        assertTrue(StringUtils.substringMatch(s, 0, "[Ambit "));
    }

    @Test
    public void testEquals() {
        Ambit copy = new Ambit(1, "Ambit 1");
        assertEquals(copy, ambit1);
    }

    @Test
    public void testNotEquals() {
        Ambit ambit2 = new Ambit(2, "Ambit 2");
        assertNotEquals(ambit1, ambit2);
    }

    @Test
    public void testHashCode() {
        Ambit copy = new Ambit(1, "Ambit 1");
        assertEquals(ambit1.hashCode(), copy.hashCode());
    }

}
