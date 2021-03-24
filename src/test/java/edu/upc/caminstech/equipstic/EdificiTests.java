package edu.upc.caminstech.equipstic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.util.StringUtils;

class EdificiTests {

    private Edifici edifici;
    private Campus campus;

    @BeforeEach
    public void setUp() throws Exception {
        campus = new Campus(1, "Campus", "C");
        edifici = edificiFixture();
    }

    @Test
    void testEdifici() {
        assertNotNull(edifici);
    }

    @Test
    void testGetIdEdifici() {
        assertEquals(1, edifici.getIdEdifici());
    }

    @Test
    void testGetNom() {
        assertEquals("EDIFICI1", edifici.getNom());
    }

    @Test
    void testGetCodi() {
        assertEquals("E1", edifici.getCodi());
    }

    @Test
    void testGetAdreca() {
        assertEquals("ADREÇA", edifici.getAdreca());
    }

    @Test
    void testGetCiutat() {
        assertEquals("CIUTAT", edifici.getCiutat());
    }

    @Test
    void testGetCodiPostal() {
        assertEquals("1234", edifici.getCodiPostal());
    }

    @Test
    void testGetCampus() {
        assertEquals(campus, edifici.getCampus());
    }

    @Test
    void testToString() {
        assertTrue(StringUtils.substringMatch(edifici.toString(), 0, "[Edifici "));
    }

    @Test
    void testEquals() {
        Edifici copy = edificiFixture();
        assertEquals(copy, edifici);
    }

    @Test
    void testNotEquals() {
        Edifici e2 = new Edifici(2, "EDIFICI2", "E2", "ADREÇA", "CIUTAT", "1234", campus);
        assertNotEquals(e2, edifici);
    }

    @Test
    void testHashCode() {
        Edifici copy = edificiFixture();
        assertEquals(copy.hashCode(), edifici.hashCode());
    }

    private Edifici edificiFixture() {
        return new Edifici(1, "EDIFICI1", "E1", "ADREÇA", "CIUTAT", "1234", campus);
    }

}
