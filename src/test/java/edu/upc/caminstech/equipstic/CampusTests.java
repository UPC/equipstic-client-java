package edu.upc.caminstech.equipstic;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.util.StringUtils;

public class CampusTests {

    private Campus campus;

    @Before
    public void setUp() throws Exception {
        campus = new Campus(1, "Campus Nord", "ND");
    }

    @Test
    public void testCampus() {
        assertNotNull(campus);
    }

    @Test
    public void testGetIdCampus() {
        assertEquals(1, campus.getIdCampus());
    }

    @Test
    public void testGetNom() {
        assertEquals("Campus Nord", campus.getNom());
    }

    @Test
    public void testGetCodi() {
        assertEquals("ND", campus.getCodi());
    }

    @Test
    public void testToString() {
        assertTrue(StringUtils.substringMatch(campus.toString(), 0, "[Campus "));
    }

    @Test
    public void testEqualsObject() {
        Campus copy = new Campus(1, "Campus Nord", "ND");
        assertEquals(campus, copy);
    }

    @Test
    public void testHashCode() {
        Campus copy = new Campus(1, "Campus Nord", "ND");
        assertEquals(copy.hashCode(), campus.hashCode());
    }

}
