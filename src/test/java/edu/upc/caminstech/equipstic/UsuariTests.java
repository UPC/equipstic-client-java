package edu.upc.caminstech.equipstic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.util.StringUtils;

class UsuariTests {

    private static final long ONE_HOUR = 3600 * 1000; // millis
    private static final Date DATA_CREACIO = new Date();
    private static final Date DATA_DARRERA_MODIFICACIO = new Date(DATA_CREACIO.getTime() + ONE_HOUR);

    private Usuari usuari;
    private Usuari copia;
    private Usuari usuariDarreraModificacio;

    @BeforeEach
    public void setUp() throws Exception {
        usuariDarreraModificacio = new Usuari(2, "x", "x", "x", "x", "x", "x", "x", "x", "carrec", DATA_CREACIO,
                DATA_DARRERA_MODIFICACIO, "observacions", null);

        usuari = new Usuari(1, "nomUsuari", "nom", "cognom1", "cognom2", "email", "telefon", "mobil", "adreca",
                "carrec", DATA_CREACIO, DATA_DARRERA_MODIFICACIO, "observacions", usuariDarreraModificacio);

        copia = new Usuari(1, "nomUsuari", "nom", "cognom1", "cognom2", "email", "telefon", "mobil", "adreca", "carrec",
                DATA_CREACIO, DATA_DARRERA_MODIFICACIO, "observacions", usuariDarreraModificacio);

    }

    @Test
    void testUsuari() {
        assertNotNull(usuari);
    }

    @Test
    void testGetIdUsuari() {
        assertEquals(1, usuari.getIdUsuari());
    }

    @Test
    void testGetAdreca() {
        assertEquals("adreca", usuari.getAdreca());
    }

    @Test
    void testGetCarrec() {
        assertEquals("carrec", usuari.getCarrec());
    }

    @Test
    void testGetCognom1() {
        assertEquals("cognom1", usuari.getCognom1());
    }

    @Test
    void testGetCognom2() {
        assertEquals("cognom2", usuari.getCognom2());
    }

    @Test
    void testGetDataCreacio() {
        assertEquals(DATA_CREACIO, usuari.getDataCreacio());
    }

    @Test
    void testGetDataDarreraModificacio() {
        assertEquals(DATA_DARRERA_MODIFICACIO, usuari.getDataDarreraModificacio());
    }

    @Test
    void testGetEmail() {
        assertEquals("email", usuari.getEmail());
    }

    @Test
    void testGetMobil() {
        assertEquals("mobil", usuari.getMobil());
    }

    @Test
    void testGetNom() {
        assertEquals("nom", usuari.getNom());
    }

    @Test
    void testGetNomUsuari() {
        assertEquals("nomUsuari", usuari.getNomUsuari());
    }

    @Test
    void testGetObservacions() {
        assertEquals("observacions", usuari.getObservacions());
    }

    @Test
    void testGetTelefon() {
        assertEquals("telefon", usuari.getTelefon());
    }

    @Test
    void testGetUsuariDarreraModificacio() {
        assertEquals(usuariDarreraModificacio, usuari.getUsuariDarreraModificacio());
    }

    @Test
    void testToString() {
        assertTrue(StringUtils.substringMatch(usuari.toString(), 0, "[Usuari "));
    }

    @Test
    void testEquals() {
        assertEquals(usuari, copia);
    }

    @Test
    void assertEqualsFalse() {
        assertNotEquals(usuariDarreraModificacio, usuari);
    }

    @Test
    void testHashCode() {
        assertEquals(usuari.hashCode(), copia.hashCode());
    }

}
