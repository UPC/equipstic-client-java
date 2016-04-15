package edu.upc.caminstech.equipstic;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.springframework.util.StringUtils;

public class UsuariTests {

    private static final long ONE_HOUR = 3600 * 1000; // millis
    private static final Date DATA_CREACIO = new Date();
    private static final Date DATA_DARRERA_MODIFICACIO = new Date(DATA_CREACIO.getTime() + ONE_HOUR);

    private Usuari usuari;
    private Usuari copia;
    private Usuari usuariDarreraModificacio;

    @Before
    public void setUp() throws Exception {
        usuariDarreraModificacio = new Usuari(2, "x", "x", "x", "x", "x", "x", "x", "x", "carrec", DATA_CREACIO,
                DATA_DARRERA_MODIFICACIO, "observacions", null);

        usuari = new Usuari(1, "nomUsuari", "nom", "cognom1", "cognom2", "email", "telefon", "mobil", "adreca",
                "carrec", DATA_CREACIO, DATA_DARRERA_MODIFICACIO, "observacions", usuariDarreraModificacio);

        copia = new Usuari(1, "nomUsuari", "nom", "cognom1", "cognom2", "email", "telefon", "mobil", "adreca", "carrec",
                DATA_CREACIO, DATA_DARRERA_MODIFICACIO, "observacions", usuariDarreraModificacio);

    }

    @Test
    public void testUsuari() {
        assertNotNull(usuari);
    }

    @Test
    public void testGetIdUsuari() {
        assertEquals(1, usuari.getIdUsuari());
    }

    @Test
    public void testGetAdreca() {
        assertEquals("adreca", usuari.getAdreca());
    }

    @Test
    public void testGetCarrec() {
        assertEquals("carrec", usuari.getCarrec());
    }

    @Test
    public void testGetCognom1() {
        assertEquals("cognom1", usuari.getCognom1());
    }

    @Test
    public void testGetCognom2() {
        assertEquals("cognom2", usuari.getCognom2());
    }

    @Test
    public void testGetDataCreacio() {
        assertEquals(DATA_CREACIO, usuari.getDataCreacio());
    }

    @Test
    public void testGetDataDarreraModificacio() {
        assertEquals(DATA_DARRERA_MODIFICACIO, usuari.getDataDarreraModificacio());
    }

    @Test
    public void testGetEmail() {
        assertEquals("email", usuari.getEmail());
    }

    @Test
    public void testGetMobil() {
        assertEquals("mobil", usuari.getMobil());
    }

    @Test
    public void testGetNom() {
        assertEquals("nom", usuari.getNom());
    }

    @Test
    public void testGetNomUsuari() {
        assertEquals("nomUsuari", usuari.getNomUsuari());
    }

    @Test
    public void testGetObservacions() {
        assertEquals("observacions", usuari.getObservacions());
    }

    @Test
    public void testGetTelefon() {
        assertEquals("telefon", usuari.getTelefon());
    }

    @Test
    public void testGetUsuariDarreraModificacio() {
        assertEquals(usuariDarreraModificacio, usuari.getUsuariDarreraModificacio());
    }

    @Test
    public void testToString() {
        assertTrue(StringUtils.substringMatch(usuari.toString(), 0, "[Usuari "));
    }

    @Test
    public void testEquals() {
        assertEquals(usuari, copia);
    }

    @Test
    public void assertEqualsFalse() {
        assertNotEquals(usuariDarreraModificacio, usuari);
    }

    @Test
    public void testHashCode() {
        assertEquals(usuari.hashCode(), copia.hashCode());
    }

}
