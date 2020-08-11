package edu.upc.caminstech.equipstic;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.util.StringUtils;

public class EdificiTests {

    private Edifici edifici;
    private Campus campus;

    @Before
    public void setUp() throws Exception {
        campus = new Campus(1, "Campus", "C");
        edifici = edificiFixture();
    }

    @Test
    public void testEdifici() {
        assertNotNull(edifici);
    }

    @Test
    public void testGetIdEdifici() {
        assertEquals(1, edifici.getIdEdifici());
    }

    @Test
    public void testGetNom() {
        assertEquals("EDIFICI1", edifici.getNom());
    }

    @Test
    public void testGetCodi() {
        assertEquals("E1", edifici.getCodi());
    }

    @Test
    public void testGetAdreca() {
        assertEquals("ADREÇA", edifici.getAdreca());
    }

    @Test
    public void testGetCiutat() {
        assertEquals("CIUTAT", edifici.getCiutat());
    }

    @Test
    public void testGetCodiPostal() {
        assertEquals("1234", edifici.getCodiPostal());
    }

    @Test
    public void testGetCampus() {
        assertEquals(campus, edifici.getCampus());
    }

    @Test
    public void testToString() {
        assertTrue(StringUtils.substringMatch(edifici.toString(), 0, "[Edifici "));
    }

    @Test
    public void testEquals() {
        Edifici copy = edificiFixture();
        assertEquals(copy, edifici);
    }

    @Test
    public void testNotEquals() {
        Edifici e2 = new Edifici(2, "EDIFICI2", "E2", "ADREÇA", "CIUTAT", "1234", campus);
        assertNotEquals(e2, edifici);
    }

    @Test
    public void testHashCode() {
        Edifici copy = edificiFixture();
        assertEquals(copy.hashCode(), edifici.hashCode());
    }

    private Edifici edificiFixture() {
        return new Edifici(1, "EDIFICI1", "E1", "ADREÇA", "CIUTAT", "1234", campus);
    }


}
