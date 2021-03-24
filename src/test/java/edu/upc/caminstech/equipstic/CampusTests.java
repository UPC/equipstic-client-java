package edu.upc.caminstech.equipstic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.util.StringUtils;

class CampusTests {

    private Campus campus;

    @BeforeEach
    public void setUp() throws Exception {
        campus = new Campus(1, "Campus Nord", "ND");
    }

    @Test
    void testCampus() {
        assertNotNull(campus);
    }

    @Test
    void testGetIdCampus() {
        assertEquals(1, campus.getIdCampus());
    }

    @Test
    void testGetNom() {
        assertEquals("Campus Nord", campus.getNom());
    }

    @Test
    void testGetCodi() {
        assertEquals("ND", campus.getCodi());
    }

    @Test
    void testToString() {
        assertTrue(StringUtils.substringMatch(campus.toString(), 0, "[Campus "));
    }

    @Test
    void testEqualsObject() {
        Campus copy = new Campus(1, "Campus Nord", "ND");
        assertEquals(campus, copy);
    }

    @Test
    void testHashCode() {
        Campus copy = new Campus(1, "Campus Nord", "ND");
        assertEquals(copy.hashCode(), campus.hashCode());
    }

}
